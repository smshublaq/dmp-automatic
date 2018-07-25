/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcid;

import api.RestfulApiClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import orcid.OrcidSearchResultResponse.OrcidSearchResult;

/**
 *
 * @author User
 */
public class OrcidController {

    private final RestfulApiClient oricdRestfullApiClient;

    public OrcidController(RestfulApiClient oricdRestfulApiClient) {
        this.oricdRestfullApiClient = oricdRestfulApiClient;
    }

    public OrcidSearchResultResponse search(String query) throws IOException {
        JsonParser parser = new JsonParser();

        final JsonObject jsonResponse = parser.parse(oricdRestfullApiClient.search(query).execute().body().string()).getAsJsonObject();

        JsonArray jsonResults = jsonResponse.getAsJsonArray("result");

        List<OrcidSearchResult> results = new ArrayList<>();
        for (int i = 0; i < jsonResults.size(); i++) {
            results.add(getOrcidProfileSearchResult(jsonResults.get(i).getAsJsonObject()));
        }

        return new OrcidSearchResultResponse(results);
    }

    public OrcidResultResponse profile(String orcidId) {

        final JsonObject jsonResponse;
        try {
            jsonResponse = new JsonParser().parse(oricdRestfullApiClient.getDataFromOrcid(orcidId).execute().body().string()).getAsJsonObject();
            return getRowResponse(jsonResponse);
        } catch (IOException ex) {
            Logger.getLogger(OrcidController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static OrcidSearchResult getOrcidProfileSearchResult(JsonObject root) {
        return new OrcidSearchResultResponse.OrcidSearchResult(
                root.getAsJsonObject("orcid-identifier").get("uri").getAsString(),
                root.getAsJsonObject("orcid-identifier").get("path").getAsString(),
                root.getAsJsonObject("orcid-identifier").get("host").getAsString());
    }

    public static OrcidResultResponse getRowResponse(JsonObject root) {
        String givenName = root.getAsJsonObject("person").getAsJsonObject("name").getAsJsonObject("given-names").get("value").getAsString();
        String familyName = root.getAsJsonObject("person").getAsJsonObject("name").getAsJsonObject("family-name").get("value").getAsString();

        String email = "";
        if (root.getAsJsonObject("person").getAsJsonObject("emails").getAsJsonArray("email").size() > 0) {
            email = root.getAsJsonObject("person").getAsJsonObject("emails").getAsJsonArray("email").get(0).getAsJsonObject().get("email").getAsString();
        }

        String title = "";
        String subtitle = "";
        String idType = "";
        String id = "";
        String idUrl = "";
        String publicationYear = "";
        String publicationMonth = "";
        String publicationDay = "";
        if (root.getAsJsonObject("activities-summary").getAsJsonObject("works").getAsJsonArray("group").size() > 0) {
            JsonObject work = root.getAsJsonObject("activities-summary").getAsJsonObject("works").getAsJsonArray("group").get(0).getAsJsonObject().get("work-summary").getAsJsonArray().get(0).getAsJsonObject();
            title = work.getAsJsonObject("title").getAsJsonObject("title").get("value").getAsString();
            subtitle = work.getAsJsonObject("title").getAsJsonObject("subtitle").get("value").getAsString();

            JsonObject externalId = work.getAsJsonObject("external-ids").getAsJsonArray("external-id").get(0).getAsJsonObject();
            idType = externalId.get("external-id-type").getAsString();
            id = externalId.get("external-id-value").getAsString();
            idUrl = externalId.getAsJsonObject("external-id-url").get("value").getAsString();

            publicationYear = work.getAsJsonObject("publication-date").getAsJsonObject("year").getAsJsonObject("value").getAsString();
            publicationMonth = work.getAsJsonObject("publication-date").getAsJsonObject("month").getAsJsonObject("value").getAsString();
            publicationDay = work.getAsJsonObject("publication-date").getAsJsonObject("day").get("value").getAsString();
        }
        return new OrcidResultResponse(
                givenName, familyName, email,
                new OrcidPublicationsResult(
                        title, subtitle, id, idType, idUrl,
                        publicationYear, publicationMonth, publicationDay)
        );

    }

}

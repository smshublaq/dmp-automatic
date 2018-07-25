/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zenodo;

import api.RestfulApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.ZenodoData;
import model.ZenodoData.Metadata;

/**
 *
 * @author User
 */
public class ZenodoController {

    private final RestfulApiClient oaiPMHApi;

    public ZenodoController(RestfulApiClient oaiPMHApi) {
        this.oaiPMHApi = oaiPMHApi;
    }

    public OAIPMHDataResponse getData(
            String identifier
    ) {

        final String zId;

        String pathPart = identifier.split("/")[1];
        String idPart = pathPart.split("\\.")[1];
        zId = String.format("oai:zenodo.org:%s", idPart);

        ZenodoData data = null;

        try {
            
            data = oaiPMHApi.getZenodo().getData(zId).execute().body();


        } catch (IOException e) {

            throw new RuntimeException(e);
        
        }
        return getResponse(data);
    }

    private static OAIPMHDataResponse getResponse(ZenodoData record) {

        Metadata metadata = record.metadata;

        String githubUrl = null;

        if (metadata.relations != null) {
            for (String relation : metadata.relations) {
                if (relation.startsWith("url:https://github.com/")) {
                    githubUrl = relation.substring(4);
                    break;
                }
            }
        }

        return new OAIPMHDataResponse(
                metadata.title, 
                AccessRights.get(metadata.rights),
                metadata.description,
                githubUrl,
                metadata.creators, metadata.identifiers, metadata.types);
    }

    public enum AccessRights {
        closedAccess("info:eu-repo/semantics/closedAccess"),
        embargoedAccess("info:eu-repo/semantics/embargoedAccess"),
        restrictedAccess("info:eu-repo/semantics/restrictedAccess"),
        openAccess("info:eu-repo/semantics/openAccess");

        public final String uri;

        private AccessRights(String uri) {
            this.uri = uri;
        }

        public static AccessRights get(String uri) {
            for (AccessRights accessRights : values()) {
                if (accessRights.uri.equals(uri)) {
                    return accessRights;
                }
            }

            return null;
        }
    }

    public static class OAIPMHDataResponse {

        public final String title;
                        public final String description;

        public final String githubUrl;
        public final AccessRights rights;

        public final List<String> creators;
        public final List<String> identifiers;
        public final List<String> types;

        public OAIPMHDataResponse(
                String title, AccessRights rights,String description,  String githubUrl,
                List<String> creators, List<String> identifiers, List<String> types) {
            this.title = title;
            this.description = description;
            this.rights = rights;
            this.githubUrl = githubUrl;
            this.creators = creators;
            this.identifiers = identifiers;
            this.types = types;
        }
    }
}

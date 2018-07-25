/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcid;

import java.util.List;

/**
 *
 * @author User
 */
public class OrcidSearchResultResponse {

    public final List<OrcidSearchResult> results;

    public OrcidSearchResultResponse(List<OrcidSearchResult> results) {
        this.results = results;
    }

    public static class OrcidSearchResult {

        public final String uri;
        public final String path;
        public final String host;

        public OrcidSearchResult(String uri, String path, String host) {
            this.uri = uri;
            this.path = path;
            this.host = host;
        }
    }

    @Override
    public String toString() {
        return "OrcidSearchResultResponse{" + "results=" + results + '}';
    }
    
}

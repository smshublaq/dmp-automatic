/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcid;

/**
 *
 * @author User
 */
public class OrcidResultResponse {
    public final String givenName;
		public final String familyName;
		public final String emailAddress;
		public final OrcidPublicationsResult publication;

		public OrcidResultResponse(String givenName, String familyName, String emailAddress, OrcidPublicationsResult publication) {
			this.givenName = givenName;
			this.familyName = familyName;
			this.emailAddress = emailAddress;
			this.publication = publication;
		}

    @Override
    public String toString() {
        return "OrcidResultResponse{" + "givenName=" + givenName + ", familyName=" + familyName + ", emailAddress=" + emailAddress + ", project=" + publication + '}';
    }

			
	
}

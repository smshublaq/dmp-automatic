/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import orcid.OrcidResultResponse;
import orcid.OrcidSearchResultResponse.OrcidSearchResult;
import zenodo.ZenodoController.OAIPMHDataResponse;

/**
 *
 * @author User
 */
public class DataManagementPlanCreator {
    private static String input;

	public static String createDmp(String path,OrcidResultResponse orcidResultResponse,OAIPMHDataResponse aIPMHDataResponse) {
		input = String.format(output,
				
				path,
				orcidResultResponse.givenName,
				orcidResultResponse.familyName,
				orcidResultResponse.emailAddress,
				orcidResultResponse.publication.publicationDay,
				orcidResultResponse.publication.publicationMonth,
				orcidResultResponse.publication.publicationYear,
				
				aIPMHDataResponse.githubUrl
			);

		
                          return input;
		
	}

	
	static String output =
			"{\r\n" +
			"  \"@context\": {\r\n" +
			"    \"dmp\": \"http://purl.org/madmps#\",\r\n" +
			"    \"foaf\": \"http://xmlns.com/foaf/0.1/\",\r\n" +
			"    \"dc\": \"http://purl.org/dc/elements/1.1/\",\r\n" +
			"    \"dcterms\": \"http://purl.org/dc/terms/\",\r\n" +
			"    \"premis\": \"http://www.loc.gov/premis/rdf/v1#\"\r\n" +
			"  },\r\n" +
			"  \"@id\": \"http://example.org/dmps/mydmp\",\r\n" +
			"  \"@type\": \"dmp:DataManagementPlan\",\r\n" +
			
			"  \"dc:creator\": [\r\n" +
			"    {\r\n" +
			"      \"@id\": \"%s\",\r\n" +
			"      \"foaf:name\": \"%s %s\",\r\n" +
			"      \"foaf:mbox\": \"%s\"\r\n" +
			"    }\r\n" +
			"  ],\r\n" +
			"  \"dc:date\": \"%s.%s.%s\",\r\n" +
			"  \"dmp:hasDataObject\": [\r\n" +
			"    {\r\n" +
			
			"      \"@type\": \"dmp:SourceCode\",\r\n" +
			"      \"dmp:hasIntelectualPropertyRights\": {\r\n" +
			"        \"dcterms:license\": \"https://opensource.org/licenses/MIT\"\r\n" +
			"      },\r\n" +
			"      \"dmp:hasDataRepository\": \"%s\",\r\n" +
			"      \"dmp:hasPreservation\": \"All files that need preservation, are marked with their respective preservation duration. The files themselves are published to Zenodo via github for long-term archival.\",\r\n" +
			"      \"dmp:hasDataSharing\": \"All code, data and documentation is available on Github and is licensed under the MIT license. To make the experiment citeable, each Github release is published to the Zenodo repository where it also gets assigned a DOI.\",\r\n" +
			"      \"dmp:hasEthicsAndPrivacy\": \" <No information> \",\r\n" +
			"      \"dmp:hasDocumentation\": \"The documentation can be found in all files that are marked as type documentation. These files can be accessed through the project repository on GitHub.\",\r\n" +
			"      \"dmp:hasDataCollection\": \"All files that are collected from external sources are marked as input-files.\",\r\n" +
			"      \"dmp:hasDataObject\": [\r\n";

			private static String file =
			"        {\r\n" +
			"          \"@type\": \"dmp:%s\",\r\n" +
			"          \"dc:title\": \"%s\",\r\n" +
			"%s" +
			"          \"dmp:hasMetadata\": {\r\n" +
			"            \"premis:hasObjectCharacteristics\": {\r\n" +
			"              \"premis:fixity\": {\r\n" +
			"                \"premis:hasMessageDigestAlgorithm\": \"premis:Fixity:SHA\",\r\n" +
			"                \"premis:messageDigest\": \"%s\"\r\n" +
			"              }\r\n" +
			"            },\r\n" +
			"            \"dmp:hasDataVolume\": \"%s bytes\"\r\n" +
			"          }\r\n" +
			"        },";

			private static String lic =
					"          \"dmp:hasIntelectualPropertyRights\": {\r\n" +
					"            \"dcterms:license\": \"https://opensource.org/licenses/MIT\"\r\n" +
					"          },\r\n";

			private static String end =
			"      ]\r\n" +
			"    }\r\n" +
			"  ]\r\n" +
			"}";

}


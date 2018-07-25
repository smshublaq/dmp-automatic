/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.IOException;
import model.ZenodoData;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 *
 * @author User
 */
public class RestfulApiClient {

    public static final String ORCID_URL = "https://pub.orcid.org/v2.1/";
    public static final String ZENODO_URL = "https://zenodo.org/";

    OkHttpClient client = new OkHttpClient();

    public Call search(String query) throws IOException {

        Request request = new Request.Builder()
                .url(ORCID_URL + "search?q=" + query)
                .addHeader("accept", "application/json")
                .get()
                .build();
        return client.newCall(request);
    }

    public Call getDataFromOrcid(String orcid) throws IOException {

        Request request = new Request.Builder()
                .url(ORCID_URL + orcid).addHeader("accept", "application/json")
                .get()
                .build();
        return client.newCall(request);
    }

    public ZenodoInterface getZenodo() {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://zenodo.org/")
                .client(client)
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                new Persister(new AnnotationStrategy())
                        )).build();

        return retrofit.create(ZenodoInterface.class);

    }

    public GitHubClient githubClient() {
        GitHubClient gitHubClient = new GitHubClient();

        if (System.getenv("GITHUB_USERNAME") != null && System.getenv("GITHUB_PASSWORD") != null) {
            gitHubClient.setCredentials(System.getenv("GITHUB_USERNAME"), System.getenv("GITHUB_PASSWORD"));
        }

        return gitHubClient;
    }

    public ContentsService contentsService(GitHubClient gitHubClient) {
        ContentsService contentsService = new ContentsService(gitHubClient);
        return contentsService;
    }

    public RepositoryService repositoryService(GitHubClient gitHubClient) {
        RepositoryService repositoryService = new RepositoryService(gitHubClient);
        return repositoryService;
    }

}

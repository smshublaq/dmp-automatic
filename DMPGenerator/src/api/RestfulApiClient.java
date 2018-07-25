/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import github.RepoResponse;
import java.io.IOException;
import model.ZenodoData;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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
    public static final String GITHUB_URL = "https://api.github.com/repos/";

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
    
    public RepoResponse getRepositoryDataGithub(String owner , String repoName) throws IOException{
        Request request = new Request.Builder()
                .url(GITHUB_URL + owner + "/" + repoName).addHeader("accept", "application/json")
                .get()
                .build();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        return gson.fromJson(client.newCall(request).execute().body().string(),RepoResponse.class);
    }

   

}

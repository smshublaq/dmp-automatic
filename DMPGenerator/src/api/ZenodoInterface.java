/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import model.ZenodoData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author User
 */
public interface ZenodoInterface {
    	@GET("/oai2d?verb=GetRecord&metadataPrefix=oai_dc")
	public Call<ZenodoData> getData(@Query("identifier") String identifier);

}

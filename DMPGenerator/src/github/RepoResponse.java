/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github;

import java.util.List;
import model.RepoModel;

/**
 *
 * @author User
 */
public class RepoResponse {
    public final List<RepoModel> data;
		
		public RepoResponse(List<RepoModel> data) {
			this.data = data;
		}
}

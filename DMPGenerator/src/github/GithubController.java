/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.RepoModel;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

/**
 *
 * @author User
 */
public class GithubController {

    private final ContentsService contentsService;
    private final RepositoryService repositoryService;

    public GithubController(ContentsService contentsService, RepositoryService repositoryService) {
        this.contentsService = contentsService;
        this.repositoryService = repositoryService;
    }

    public RepoResponse repositoryInformation(
            String owner,
            String repository,
            String ref
    ) {

        try {
            Repository repo = repositoryService.getRepository(owner, repository);
            return new RepoResponse(getPath(contentsService, repo, "", ref));
        } catch (IOException e) {

        }
        return null;
    }

    private static List<RepoModel> getPath(
            ContentsService contentsService,
            Repository repository,
            String path,
            String ref) throws IOException {

        List<RepoModel> data = new ArrayList<>();

        for (RepositoryContents c : contentsService.getContents(repository, path, ref)) {
            if (c.getType().equals("dir")) {
                data.addAll(getPath(contentsService, repository, c.getPath(), ref));
            } else {
                data.add(new RepoModel(
                        c.getName(), c.getPath(), c.getType(), c.getSize(), c.getSha()));
            }
        }

        return data;
    }
}

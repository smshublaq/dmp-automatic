/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class RepoModel {

    public final String name;
    public final String path;
    public final String type;
    public final long size;
    public final String sha;

    public RepoModel(String name, String path, String type, long size, String sha) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
        this.sha = sha;
    }
}

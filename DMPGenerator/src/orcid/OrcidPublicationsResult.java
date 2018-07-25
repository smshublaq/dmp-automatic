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
public class OrcidPublicationsResult {

    public final String title;
    public final String subtitle;

    public final String id;
    public final String idType;
    public final String idUrl;

    public final String publicationYear;
    public final String publicationMonth;
    public final String publicationDay;

    public OrcidPublicationsResult(String title, String subtitle, String id, String idType, String idUrl,
            String publicationYear, String publicationMonth, String publicationDay) {
        this.title = title;
        this.subtitle = subtitle;
        this.id = id;
        this.idType = idType;
        this.idUrl = idUrl;
        this.publicationYear = publicationYear;
        this.publicationMonth = publicationMonth;
        this.publicationDay = publicationDay;
    }

    @Override
    public String toString() {
        return "OrcidProjectResult{" + "title=" + title + ", subtitle=" + subtitle + ", id=" + id + ", idType=" + idType + ", idUrl=" + idUrl + ", publicationYear=" + publicationYear + ", publicationMonth=" + publicationMonth + ", publicationDay=" + publicationDay + '}';
    }

}

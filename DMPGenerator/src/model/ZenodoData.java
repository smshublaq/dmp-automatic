/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 *
 * @author User
 */
public class ZenodoData {

    @Element(name = "header")
    @Path("GetRecord/record")
    public Header header;

    @Element(name = "dc")
    @Path("GetRecord/record/metadata")
    public Metadata metadata;

    @Root(strict = false)
    public static class Header {

        @Element(name = "identifier")
        public String identifier;

        @Element(name = "datestamp")
        public String datestamp;

        @Path("setSpec")
        @Text(required = false)
        public String spec;
    }

    @Root(strict = false)
    public static class Metadata {

        @ElementList(entry = "creator", inline = true)
        public List<String> creators;

        @Element(name = "date")
        public String date;

        @Element(name = "description")
        public String description;

        @ElementList(entry = "identifier", inline = true)
        public List<String> identifiers;

        @ElementList(entry = "relation", inline = true)
        public List<String> relations;

        @Element(name = "rights")
        public String rights;

        @Element(name = "title")
        public String title;

        @ElementList(entry = "type", inline = true)
        public List<String> types;
    }
}

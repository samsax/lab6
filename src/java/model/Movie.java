/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;

/**
 *
 * @author davida.florez
 */

@Model
public class Movie {
    
    @Size(min=1, max=20)
    private String name;
    @Size(min=1, max=20)
    private String language;
    private int year;

    public BasicDBObject toDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put("name", name);
        doc.put("year", year);
        doc.put("language", language);
        return doc;
    }
    
    public static Movie fromDBObject(DBObject doc){
        Movie m = new Movie();
        m.name=(String)doc.get("name");
        m.year=(int)doc.get("year");
        m.language=(String)doc.get("language");
        return m;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the languge
     */
    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    
}

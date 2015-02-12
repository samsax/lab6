/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.Stateless;
import javax.inject.Named;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import model.Movie;

/**
 *
 * @author davida.florez
 */
@Stateless
@Named
public class MovieSessionBean {

    @Inject Movie movie;
    DBCollection movieColl;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    private void initDB(){
        Mongo m;
        try {
            m = new Mongo();
            DB db = m.getDB("movieDB");
            movieColl = db.getCollection("movies");
            if (movieColl==null) {
                movieColl=db.createCollection("movies", null);
            }
        } catch (UnknownHostException e) {
            Logger.getLogger(MovieSessionBean.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void createMovie(){
        BasicDBObject doc = movie.toDBObject();
        movieColl.insert(doc);
    }
    
    public List<Movie> getMovies(){
        List<Movie> movies = new ArrayList();
        DBCursor cur = movieColl.find();
        System.out.println("getMovies: Found" + cur.size() + "movie(s)");
        for (DBObject dbo : cur.toArray()) {
            movies.add(Movie.fromDBObject(dbo));
        }
        return movies;
    }
    
    public List<Movie> getMovie(String nombre){
        BasicDBObject query = new BasicDBObject();
        List<Movie> movies = new ArrayList();
        query.put("name", nombre);
        DBCursor db = movieColl.find(query);
        for (DBObject dbo : db.toArray()) {
            movies.add(Movie.fromDBObject(dbo));
        }
        return movies;
    }
      public List<Movie> getMovieYear(int year){
        BasicDBObject query = new BasicDBObject();
        List<Movie> movies = new ArrayList();
        query.put("year", year);
        DBCursor db = movieColl.find(query);
        for (DBObject dbo : db.toArray()) {
            movies.add(Movie.fromDBObject(dbo));
        }
        return movies;
    }
      
          public List<Movie> getMovieLenguage(String lenguage){
        BasicDBObject query = new BasicDBObject();
        List<Movie> movies = new ArrayList();
        query.put("lenguage", lenguage);
        DBCursor db = movieColl.find(query);
        for (DBObject dbo : db.toArray()) {
            movies.add(Movie.fromDBObject(dbo));
        }
        return movies;
    }
    public String deleteAction(Movie m) {
 
		movieColl.remove((DBObject) m);
		return null;
	}
}

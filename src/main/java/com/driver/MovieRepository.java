package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
@Repository
public class MovieRepository {
    HashMap<String,Movie> movieMap;
    HashMap<String,Director> directorMap;
    HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(HashMap<String, Movie> movieMap, HashMap<String, Director> directorMap, HashMap<String, List<String>> directorMovieMapping) {
        this.movieMap = movieMap;
        this.directorMap = directorMap;
        this.directorMovieMapping = directorMovieMapping;
    }

    public String addMovie(Movie movie) {
        String name = movie.getName();
        movieMap.put(name, movie);
        return "Movie Added SuccessFully";
    }
    public String addDirector( Director director){
        String name = director.getName();
        directorMap.put(name,director);
        return "Director Add SuccessFully";
    }

    public String addMovieDirectorPair( String movie,String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            movieMap.put(movie, movieMap.get(movie));
            directorMap.put(director, directorMap.get(director));
            List<String> currentMovies = new ArrayList<String>();
            if(directorMovieMapping.containsKey(director)) currentMovies = directorMovieMapping.get(director);
            currentMovies.add(movie);
            directorMovieMapping.put(director, currentMovies);
        }
        return "Movie and Director Pair added";

    }

    public Movie getMovieByName( String name){

        return movieMap.get(name);
    }
    public Director getDirectorByName( String name){

        return directorMap.get(name);
    }

    public List<String> getMovieByDirectorName(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)) moviesList = directorMovieMapping.get(director);
        return moviesList;

    }

    public List<String> findAllMovies(){
        List<String> list = new ArrayList<>();
        for(String name  : movieMap.keySet()){
            list.add(name);
        }
        return list;
    }

    public String deleteDirectorByName(String director){
        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)){
            movies = directorMovieMapping.get(director);
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            directorMovieMapping.remove(director);
        }

        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
        return "All Directors Removed SuccessFully";
    }
    public String deleteAllDirectors(){
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: directorMovieMapping.keySet()){
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        return "All Directors Removed SuccessFully";
    }
}

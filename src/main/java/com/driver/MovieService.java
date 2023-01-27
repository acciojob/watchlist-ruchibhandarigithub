package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }
    public String addDirector( Director director){
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair( String movieName,String directorName){

       return movieRepository.addMovieDirectorPair(movieName,directorName);
    }

    public Movie getMovieByName( String name){

        return movieRepository.getMovieByName(name);
    }
    public Director getDirectorByName( String name){

        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMovieByDirectorName(String name){

        return movieRepository.getMovieByDirectorName(name);
    }

    public List<String> findAllMovies(){
       return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String director){
        return  movieRepository.deleteDirectorByName(director);
    }
    public String deleteAllDirectors(){
        return movieRepository.deleteAllDirectors();
    }


}

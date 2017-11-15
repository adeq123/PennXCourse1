package collectionAndOODesign;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class MovieDatabase {


    private ArrayList<Movie> movieList;
    private ArrayList<Actor> actorList;

    public MovieDatabase (){
	this.movieList = new ArrayList<Movie>();
	this.actorList = new ArrayList<Actor>();
    }
    /**
     * If the movie is not on the database - adds it to the list and updates the actor list 
     * @param name, movie name to be added
     * @param actors, actors list 
     */

    public void addMovie(String name, String [] actors){

	if(!checkIfInMovieDataBase(name)){
	    ArrayList<Actor> movieActors = generateListOfActors(actors);
	    Movie newMovie = new Movie(name, movieActors);
	    movieList.add(newMovie);
	    addToActorsList(movieActors);
	    addMovieToActors(movieActors, newMovie);

	}
    }
    /**
     * If the movie is not on the database - adds it to the list and updates the actor list.
     * Special method made for input where we have an actor and list of movies associated with him
     * @param name, movie name to be added
     * @param actors, actors list 
     */
    public void addMovies(String [] movies, String actor){
	String [] actorArray = {actor}; 
	for(String movName : movies){
	    if(!checkIfInMovieDataBase(movName))
		addMovie(movName, actorArray);
	}
    }
    /**
     * Adds actors to the list if not on the list
     * @param movieActors
     */
    private void addToActorsList(ArrayList<Actor> movieActors) {
	for(Actor oneActor : movieActors)
	    if(!checkIfInActorsDataBase(oneActor))
		actorList.add(oneActor);

    }
    /**
     * Adds a movie to all actors from the list
     * @param movieActors, actors which palayed in the movie
     * @param newMovie, the Movie to be added
     */
    private void addMovieToActors(ArrayList<Actor> movieActors,
	    Movie newMovie) {
	for(Actor oneActor : movieActors)
	    oneActor.addMovieToTheList(newMovie);

    }

    /**
     * Generates the ArrayList of actors from a given array
     * @param actors
     * @return
     */
    private ArrayList<Actor> generateListOfActors(String[] actors) {
	ArrayList<Actor> movieActors = new ArrayList<Actor>();
	Actor actor;

	for(String actorName : actors){
	    if(checkIfInActorsDataBase(actorName)){
		actor = getActor(actorName);
	    }else{
		actor = new Actor (actorName);
	    }
	    movieActors.add(actor);
	}
	return movieActors;
    }
    /**
     * Checks if the name of the movie is on the list
     * @param name, a String, name of the movie to be checked
     * @return false if the name is not on the list. true if the movie is on the list
     */
    public boolean checkIfInMovieDataBase(String name){
	if(movieList.isEmpty())
	    return false;
	for(Movie mov : movieList)
	    if(mov.getName().equals(name))
		return true;


	return false;

    }

    /**
     * Checks if the name of the actor is on the list
     * @param name, a String, name of the actor to be checked
     * @return false if the name is not on the list. true if the movie is on the list
     */
    public boolean checkIfInActorsDataBase(String name){
	if(actorList.isEmpty())
	    return false;
	for(Actor act : actorList)
	    if(act.getName().equals(name))
		return true;


	return false;

    }

    /**
     * Checks if the name of the actor is on the list
     * @param name, a String, name of the actor to be checked
     * @return false if the name is not on the list. true if the movie is on the list
     */
    public boolean checkIfInActorsDataBase(Actor actor){
	String name = actor.getName();
	if(actorList.isEmpty())
	    return false;
	for(Actor act : actorList)
	    if(act.equals(actor) || act.getName().equals(name))
		return true;


	return false;

    }
    /**
     * Returns actors object based on actors name
     * @param name, String, name of the actor to be return
     * @return Actor with name given if on the actors list, null other wise
     */
    public Actor getActor(String name){
	if(actorList.isEmpty())
	    return null;
	for(Actor act : actorList)
	    if(act.getName().equals(name))
		return act;
	return null;

    }
    
    /**
     * Add a rating to the movie given by the name
     * @param name, String, name of the movie to be rated
     * @param rating, double, rating of the movie
     */
    public void addRating(String name, double rating){
	for(Movie mov : movieList)
	    if(mov.getName().equals(name))
		if(mov.getRating() == 0)
		    mov.setRating(rating);
    }

    /**
     * Updates a rating to the movie given by the name
     * @param name, String, name of the movie to be rated
     * @param rating, double, rating of the movie
     */
    public void updateRating(String name, double rating){
	for(Movie mov : movieList)
	    if(mov.getName().equals(name))
		mov.setRating(rating);
    }

    /**
     * The method finds the actor with the best rating
     * @return, String, a name of the actor with the best actor
     * @throws Exception, throws an exception when list is empty
     */
    public String getBestActor() {
	if(actorList.isEmpty())
	    return null;
	Actor bestActor = actorList.get(0);
	for(Actor act : actorList)
	    if(act.getAverageRating() > bestActor.getAverageRating())
		bestActor = act;
	return bestActor.getName();
    }

    /**
     * The method finds the movie with the best rating
     * @return, String, a name of the movie with the best rating
     * @throws Exception, throws an exception when list is empty
     */
    public String getBestMovie() {
	if(movieList.isEmpty())
	    return null;
	Movie bestMovie = movieList.get(0);
	for(Movie mov : movieList)
	    if(mov.getRating() > bestMovie.getRating())
		bestMovie = mov;
	return bestMovie.getName();
    }

    public ArrayList<Movie> getMovieList() {
	return movieList;
    }

    public ArrayList<Actor> getActorList() {
	return actorList;
    }
    
    public static void main(String args[]) throws Exception{
	String fileName = "movies.txt";
	String ratingName = "ratings.txt";
	File file = new File(fileName);
	File ratingFile = new File(ratingName);
	MovieDatabase mDB = new MovieDatabase();
	String [] lineArray = new String [100];
	String line;
	BufferedReader br = new BufferedReader(new FileReader(file));

	while((line = br.readLine()) != null){
	    lineArray = line.split(", ");
	    mDB.addMovies(Arrays.copyOfRange(lineArray, 1, lineArray.length), lineArray [0]);

	}
	br.close();
	br = new BufferedReader(new FileReader(ratingFile));
	br.readLine();

	while((line = br.readLine()) != null){
	    lineArray = line.split("\t");
	    mDB.addRating(lineArray[0], Double.parseDouble(lineArray[1]));

	}
	br.close();

	System.out.println("Best Movie: " + mDB.getBestMovie());
	System.out.println("Best Actor: " + mDB.getBestActor() + " rating: " + mDB.getActor(mDB.getBestActor()).getAverageRating());

    }
}

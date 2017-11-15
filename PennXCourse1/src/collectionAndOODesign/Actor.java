package collectionAndOODesign;

import java.util.ArrayList;

public class Actor {

    private String name;
    private ArrayList<Movie> movies;
    private double averageRating;

    public Actor(String name, ArrayList<Movie> movies){
	this.name = name;
	this.movies = movies;
	calculateAverageRating();

    }

    public Actor(String name){
	this.name = name;
	this.movies = new ArrayList<Movie> ();
    }

    public Actor(){
	this.name = "";
	this.movies = new ArrayList<Movie> ();
    }

    /** 
     * Adds a movie to actors movie list if not present on the list
     */
    public void addMovieToTheList(Movie newMovie){
	if(!movieOnTheList(newMovie))
	    getMovies().add(newMovie);
	calculateAverageRating();
    }

    /**
     * calculates average rating of the movies associated with actor
     * @return 
     */
    private double calculateAverageRating() {
	if(movies.isEmpty())
	    return 0;
	double sum = 0;
	for(Movie oneMovie : movies)
	    sum += oneMovie.getRating();
	averageRating = sum / movies.size();
	return averageRating;

    }

    /**
     * Checks if the movie is already on actors list
     * @param newMovie, movie to be checked
     * @return true if movie is on the actor list false otherwise
     */
    public boolean movieOnTheList(Movie newMovie) {
	for(Movie oneMovie : movies)
	    if(oneMovie.equals(newMovie) || 
		    oneMovie.getName().equals(newMovie.getName()))
		return true;
	return false;
    }

    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public ArrayList<Movie> getMovies() {
	return movies;
    }
    public void setMovies(ArrayList<Movie> movies) {
	this.movies = movies;
	calculateAverageRating();
    }
    public double getAverageRating(){
	return calculateAverageRating();
    }
}

package collectionAndOODesign;

import java.util.ArrayList;

public class Movie {

    private String name;
    private ArrayList<Actor> actors;
    private double rating;
    
    public Movie(String name, ArrayList<Actor> actors, double rating){
	this.name = name;
	this.actors = actors;
	this.rating = rating;
    }
    
    public Movie(String name, ArrayList<Actor> actors){
	this.name = name;
	this.actors = actors;
	this.rating = 0;
    }

    public Movie(String name){
  	this.name = name;
  	this.actors = new ArrayList<Actor>();
  	this.rating = 0;
      }
    
    public Movie(){
  	this.name = "";
  	this.actors = new ArrayList<Actor>();
  	this.rating = 0;
      }
    /**
     * 
     * @param act
     */
    public void addActor(Actor act){
	if(!checkIfInActorsOnTheList(act)){
	    actors.add(act);
	}
    }
    /**
     * Checks if the actor is on the movie actors list
     * @param actor, Actor, to be checked against the list
     * @return true if actor on list else false
     */
    public boolean checkIfInActorsOnTheList(Actor actor){
	String name = actor.getName();
	if(actors.isEmpty())
	    return false;
	for(Actor act : actors)
	    if(act.equals(actor) || act.getName().equals(name))
		return true;
	return false;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Actor> getActors() {
        return actors;
    }
    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    
}

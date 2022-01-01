package Model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Model.Film;

//This statement means that class "FilmList.java" is the root-element of our example
@XmlRootElement(name = "filmList")

@XmlAccessorType (XmlAccessType.FIELD)

public class FilmList {

	// XmLElementWrapper generates a wrapper element around XML representation
	// @XmlElementWrapper(name = "filmCollection")
	// XmlElement sets the name of the entities
	@XmlElement(name = "film")
	
	private ArrayList<Film> films;
	
	public void setFilmList(ArrayList<Film> films) {
		this.films = films;
	}

	
	public ArrayList<Film> getFilmsList() {
		return films;
	}

	public FilmList() {
		super();

	}
	public FilmList(ArrayList<Film> arrayListFilms) {
		this.films = arrayListFilms;
	}
}
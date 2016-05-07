package bd2.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Esta clase tiene la finalidad de modelar un curso, que consta de un idioma,
 * un nombre, un nivel y una coleccion de lecciones involucradas.
 *
 */

public class Curso {

	private Idioma idioma;
	private Collection<Leccion> lecciones;
	private String nombre;
	private int nivel;
	private long id; 
	
	/**
	 * Método constructor.
	 * 
	 * @param nombre ; un string que hace de nombre para el curso.
	 * @param idioma ; un Idioma que detalla el idioma del curso.
	 * @param nivel ; un int que es el nivel correspondiente al curso.
	 */
	public Curso(){
	}
	
	public Curso(String nombre, Idioma idioma, int nivel){
		setIdioma(idioma);
		setNivel(nivel);
		setNombre(nombre);
		this.lecciones = new ArrayList<Leccion>();
	}
	
	/**
	 * Getters y setters. 
	 */
	public void setLecciones(Collection<Leccion> lecciones){
		this.lecciones = lecciones;
	}
	
	public void setLecciones(){
		this.lecciones = new ArrayList<Leccion>();
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id; 
	}
	
	public void agregarLeccion(Leccion leccion) {
		getLecciones().add(leccion);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	public Collection<Leccion> getLecciones() {
		return lecciones;
	}
	
	
	
}

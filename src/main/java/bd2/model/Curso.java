package bd2.model;

import java.util.ArrayList;
import java.util.Collection;

public class Curso {

	private Idioma idioma;
	private Collection<Leccion> lecciones;
	private String nombre;
	private int nivel;
	
	public Curso(String nombre, Idioma idioma, int nivel){
		setIdioma(idioma);
		setNivel(nivel);
		setNombre(nombre);
		this.lecciones = new ArrayList<Leccion>();
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

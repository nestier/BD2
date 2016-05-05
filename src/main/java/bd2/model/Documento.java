package bd2.model;

import java.util.ArrayList;
import java.util.Collection;

public class Documento {

	private Idioma idioma;
	private Collection<Parrafo> parrafos;
	private String nombre;
	private Integer complejidad;

	public Documento(String nombre, Idioma idioma) {
		setNombre(nombre);
		setIdioma(idioma);
		this.parrafos = new ArrayList<Parrafo>();
	}

	public Parrafo agregarParrafo(String texto) {
		Parrafo parrafo = new Parrafo(texto, this);
		getParrafos().add(parrafo);
		return parrafo;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Collection<Parrafo> getParrafos() {
		return parrafos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

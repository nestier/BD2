package bd2.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Esta clase tiene la finalidad de modelar un documento de un idioma determinado, 
 * con un nombre especifico y una coleccion de parrafos involucrados.
 * El método complejidad se agregó basándose en el uml dado, aunque en el mismo no se
 * detallan los setters y getters para dicho atributo ni se lo menciona en el detalle dado,
 * por ende queda sin uso por el momento.
 *
 */
public class Documento {

	private Idioma idioma;
	private Collection<Parrafo> parrafos;
	private String nombre;
	private Integer complejidad;
	private long id; 

	/**
	 * Método constructor.
	 * 
	 * @param nombre ; es un String que hace de nombre del documento
	 * @param idioma ; es un Idioma que detalla el idioma del documento.
	 */
	public Documento(){
		setParrafos();
	}
	
	public Documento(String nombre, Idioma idioma) {
		setNombre(nombre);
		setIdioma(idioma);
		this.parrafos = new ArrayList<Parrafo>();
	}

	/**
	 * Getters y setters. 
	 */
	
	public void setParrafos(){
		this.parrafos = new ArrayList<Parrafo>();
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id; 
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

	public Integer getComplejidad() {
		return complejidad;
	}

	public void setComplejidad(Integer complejidad) {
		this.complejidad = complejidad;
	}

	public void setParrafos(Collection<Parrafo> parrafos) {
		this.parrafos = parrafos;
	}

}

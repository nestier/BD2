package bd2.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Esta clase tiene la finalidad de modelar un diccionario para un idioma determinado,
 * con una edicion determinada y un map de definiciones involucradas.
 *
 */
public class Diccionario {

	private Idioma idioma;
	private String edicion;
	private Map<String, String> definiciones;
	private Long id; 

	/**
	 * Método constructor.
	 * 
	 * @param idioma ; es un Idioma que detalla el idioma del diccionario.
	 * @param edicion ; es un String que detalla la edicion del diccionario.
	 */
	public Diccionario() {
		
	}
	
	public Diccionario(Idioma idioma, String edicion) {
		this.idioma = idioma;
		setEdicion(edicion);
		this.definiciones = new HashMap<String,String>();
	}
	
	/**
	 * Método constructor que no requiere la edicion.
	 * 
	 * @param idioma ; es un Idioma que detalla el idioma del diccionario.
	 */
	public Diccionario(Idioma idioma){
		this.idioma = idioma;
		this.definiciones = new HashMap<String,String>();
	}

	/**
	 * Getters y setters. 
	 */
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id; 
	}
	
	public void agregarDefinicion(String key, String value) {
		this.definiciones.put(key, value);
	}
	
	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String definicion(String key) {
		return definiciones.get(key);
	}

	public Map<String, String> getDefiniciones() {
		return definiciones;
	}

	public Idioma getIdioma() {
		return idioma;
	}

}

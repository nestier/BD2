package bd2.model;

import java.util.HashMap;
import java.util.Map;

public class Diccionario {

	private Idioma idioma;
	private String edicion;
	private Map<String, String> definiciones;


	public Diccionario(Idioma idioma, String edicion) {
		this.idioma = idioma;
		setEdicion(edicion);
		this.definiciones = new HashMap<String,String>();
	}
	
	public Diccionario(Idioma idioma){
		this.idioma = idioma;
		this.definiciones = new HashMap<String,String>();
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

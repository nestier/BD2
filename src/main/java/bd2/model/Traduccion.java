package bd2.model;

import java.util.Date;

/**
 * 
 * Es una subclase de Tarea que modela una traduccion a realizar por un usuario.
 *
 */
public class Traduccion extends Tarea{

	private Parrafo parrafo;
	private Idioma idioma;
	private String texto;
	
	/**
	 * Método constructor.
	 * 
	 * @param fecha ; un Date que hace de fecha para la tarea.
	 * @param descripcion ; un String que hace de descripcion de la tarea.
	 * @param completa ; un boolean que determina si está o no completada.
	 * @param texto ; un String que hace de texto traducido.
	 * @param parrafo ; un Parrafo involucrado en la traduccion.
	 * @param idioma ; un Idioma involucrado en la traduccion.
	 */
	public Traduccion(){
		
	}
	
	public	Traduccion(Date fecha, String descripcion, Boolean completa, String texto, Parrafo parrafo, Idioma idioma){
		super();
		setFecha(fecha);
		setDescripcion(descripcion);
		if (completa) completar();
		setTexto(texto);
		setParrafo(parrafo);
		setIdioma(idioma);
	}
	 
	/**
	 * Este método retorna el idioma original del parrafo involucrado.
	 * @return un Idioma correspondiente al documetno del parrafo dado.
	 */
	public Idioma getIdiomaOriginal(){
		return getParrafo().getDocumento().getIdioma();
	}
	
	/**
	 * Getters y setters. 
	 */
	
	private void setTexto(String texto) {
		this.texto = texto;
	}

	private void setParrafo(Parrafo parrafo) {
		this.parrafo = parrafo;
	}

	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	public Parrafo getParrafo() {
		return parrafo;
	}

	public String getTexto() {
		return texto;
	}
}

package bd2.model;

import java.util.Date;

public class Traduccion extends Tarea{

	private Parrafo parrafo;
	private Idioma idioma;
	private String texto;
	
	public	Traduccion(Date fecha, String descripcion, Boolean completa, String texto, Parrafo parrafo, Idioma idioma){
		super();
		setFecha(fecha);
		setDescripcion(descripcion);
		if (completa) completar();
		setTexto(texto);
		setParrafo(parrafo);
		setIdioma(idioma);
	}
	
	public Idioma getIdiomaOriginal(){
		return getParrafo().getDocumento().getIdioma();
	}

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

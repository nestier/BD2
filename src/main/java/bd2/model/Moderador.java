package bd2.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * Es una subclase de Usuario, tiene la finalidad de modelar a un moderador que cuenta con
 * una coleccion de idiomas que modera o conoce, y una coleccion de evaluaciones que modera.
 *
 */
public class Moderador extends Usuario {
	private Collection<Idioma> idiomas;
	private Collection<Evaluacion> evaluaciones;

	/**
	 * Método constructor.
	 * 
	 * @param email ; un String que hace de email, atributo heredado.
	 * @param nombre ; un String que hace de nombre, atributo heredado.
	 * @param fechaDeCreacion ; un Date que hace de fecha de creación, atributo heredado.
	 */
	public Moderador() {
	  this.idiomas = new ArrayList<Idioma>();
	  this.evaluaciones = new ArrayList<Evaluacion>();
	}
	
	public Moderador(String email, String nombre, Date fechaDeCreacion) {
		super(email, nombre, fechaDeCreacion);
		this.idiomas = new ArrayList<Idioma>();
		this.evaluaciones = new ArrayList<Evaluacion>();
	}

	/**
	 * Método encargado de obtener una reputación en base a la cantidad de evaluaciones realizadas.
	 * @return int que es igual a la cantidad de evaluaciones realizadas.
	 */
	public int reputacion() {
		return getEvaluaciones().size();
	}

	/**
	 * Método que realiza una evaluación de una Traduccion, si el moderador conoce ambos idiomas de la misma.
	 * 
	 * @param traduccion ; una Traduccion a evaluar.
	 * @param descripcion ; un String que hace de descripcion para la evaluacion.
	 * @param puntaje ; un puntaje de la evaluación.
	 * @throws Exception ; en caso de que el moderador no conozca alguno de los lenguajes de la Traduccion.
	 */
	public void evaluar(Traduccion traduccion, String descripcion, int puntaje) throws Exception {
		if (manejaIdioma(traduccion.getIdioma())
				&& manejaIdioma(traduccion.getIdiomaOriginal())) {
			Calendar cal = Calendar.getInstance();
			Date hoy = cal.getTime();
			Evaluacion evaluacion = new Evaluacion(hoy, descripcion, true,
					traduccion, puntaje);
			this.evaluaciones.add(evaluacion);
		} else
			throw new Exception("No se pueden evaluar traducciones de idiomas que el moderador no maneja.");

	}

	/**
	 * Método que dado un idioma determina si el moderador lo maneja o no.
	 * 
	 * @param idioma ; un Idioma a testear.
	 * @return true si el Idioma es conocido por el modelador, false en caso contrario.
	 */
	public boolean manejaIdioma(Idioma idioma) {
		if (getIdiomas().contains(idioma))
			return true;
		else
			return false;
	}

	/**
	 * Getters y setters. 
	 */
	public void setIdiomas(Collection<Idioma> Idiomas){
		this.idiomas = Idiomas;
	}
	
	public void setIdiomas(){
		this.idiomas = new ArrayList<Idioma>();
	}
	public void setEvaluaciones(Collection<Evaluacion> evaluaciones){
		this.evaluaciones = evaluaciones;
	}
	
	public void setPruebas(){
		this.evaluaciones = new ArrayList<Evaluacion>();
	}
	
	
	public void agregarIdioma(Idioma idioma) {
		this.idiomas.add(idioma);
	}

	public Collection<Idioma> getIdiomas() {
		return idiomas;
	}

	public Collection<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
}

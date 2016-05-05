package bd2.model;

import java.util.Date;

/**
 * 
 * Es una subclase de Tarea que especifica una evaluación relacionada a una traduccion
 * dada y con un puntaje obtenido. 
 * Además cuenta con los atributos heredados descripcion, si está o no completa, y la fecha
 * de realización.
 *
 */
public class Evaluacion extends Tarea{

	private Traduccion traduccion;
	private int puntaje;

	/**
	 * Método constructor.
	 * 
	 * @param fecha ; es un Date que especifica la fecha de realización.
	 * @param descripcion ; es un String que hace de descripción.
	 * @param completa ; es un boolean que especifica si está o no completa.
	 * @param traduccion ; es una Traduccion involucrada en la evaluación.
	 * @param puntaje ; es el puntaje de la evaluación
	 */
	public Evaluacion() {
		
	}
	
	public Evaluacion(Date fecha, String descripcion, Boolean completa, Traduccion traduccion, int puntaje) {
		super();
		setFecha(fecha);
		setDescripcion(descripcion);
		if (completa) this.completar();
		setTraduccion(traduccion);
		setPuntaje(puntaje);
	}

	/**
	 * Getters y setters. 
	 */
	
	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Traduccion getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(Traduccion traduccion) {
		this.traduccion = traduccion;
	}
	
}

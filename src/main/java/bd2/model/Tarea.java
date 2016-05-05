package bd2.model;

import java.util.Date;

/**
 * 
 * Esta es un clase abstracta que modela una tarea dada, que puede ser una Traduccion o una Evaluacion
 * según sea el caso.
 *
 */
public abstract class Tarea {

	protected Date fecha;
	protected String descripcion;
	protected boolean completa;
	private Long id; 
	
	/**
	 * Método constructor que pone por default en false el atributo completa.
	 */
	public Tarea(){
		completa = false;
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
	
	public void completar() {
		this.completa = true;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public boolean getCompleta() {
		return completa;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}

package bd2.model;

import java.util.Date;

public abstract class Tarea {

	private Date fecha;
	private String descripcion;
	private boolean completa;
	
	public Tarea(){
		completa = false;
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
	public void completar() {
		this.completa = true;
	}
	
	
}

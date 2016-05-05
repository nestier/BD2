package bd2.model;

import java.util.Date;

public class Evaluacion extends Tarea{

	private Traduccion traduccion;
	private int puntaje;

	public Evaluacion(Date fecha, String descripcion, Boolean completa, Traduccion traduccion, int puntaje) {
		super();
		setFecha(fecha);
		setDescripcion(descripcion);
		if (completa) this.completar();
		setTraduccion(traduccion);
		setPuntaje(puntaje);
	}

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

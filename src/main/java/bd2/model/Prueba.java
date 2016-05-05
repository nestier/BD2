package bd2.model;

public class Prueba {

	private Leccion leccion;
	private int puntaje;

	public Prueba(Leccion leccion, int puntaje) throws Exception {
		setLeccion(leccion);
		setPuntaje(puntaje);
	}

	public boolean aprobada() {
		return getPuntaje() >= 60;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) throws Exception {
		if (puntaje < 0)
			throw new Exception(
					"No se puede usar valores negativos como puntaje de una prueba.");
		if (puntaje > 100)
			throw new Exception(
					"No se puede usar valores mayores a 100 como puntaje de una prueba.");
		this.puntaje = puntaje;
	}

	public Leccion getLeccion() {
		return leccion;
	}

	public void setLeccion(Leccion leccion) {
		this.leccion = leccion;
	}
}

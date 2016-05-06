package bd2.model;

/**
 * 
 * Esta clase modela una prueba realizada por un usuario sobre una leccion en particular.
 *
 */
public class Prueba {

	private Leccion leccion;
	private int puntaje;
	private long id; 

	/**
	 * Método constructor.
	 * 
	 * @param leccion ; la Leccion involucrada.
	 * @param puntaje ; un int que es el puntaje de la prueba.
	 * @throws Exception ; si el puntaje es menor a 0 o mayor a 100.
	 */
	public Prueba(){
		
	}
	
	public Prueba(Leccion leccion, int puntaje) throws Exception {
		setLeccion(leccion);
		setPuntaje(puntaje);
	}

	/**
	 * Este método calcula si la prueba está aprobada o no.
	 * @return true si el puntaje es mayor o igual a 60, false en caso contrario.
	 */
	public boolean aprobada() {
		return getPuntaje() >= 60;
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

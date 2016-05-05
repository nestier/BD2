package bd2.model;

/**
 * 
 * Esta clase detalla una lección, la cual tiene un nombre específico.
 *
 */
public class Leccion {

	private String nombre;
	private Long id; 

	/**
	 * Método constructor
	 * 
	 * @param nombre ; un String que hace de nombre de la leccion.
	 */
	public Leccion() {
		
	}
	
	public Leccion(String nombre) {
		setNombre(nombre);
	}
	
	/**
	 * Redefinición del método equals
	 */
	@Override
	public boolean equals(Object o){
		if ((o instanceof Leccion) && ((Leccion) o).getNombre() == this.getNombre()){
			return true;
		}else{
			return false;
		}
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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

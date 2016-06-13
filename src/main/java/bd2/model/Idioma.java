package bd2.model;

/**
 * 
 * Esta clase tiene la finalidad de detallar un idioma en particular, para el cual se tiene
 * un nombre dado y un diccionario asociado.
 *
 */
public class Idioma {

	private Diccionario diccionario;
	private String nombre;
	private long id; 
	
	/**
	 * Método constructor.
	 * 
	 * @param nombre ; es un String que hace de nombre del idioma.
	 */
	public Idioma() {
		
	}
	
	public Idioma(String nombre){
		this.nombre = nombre;
		this.diccionario = new Diccionario(this);
	}
	
	/**
	 * Redefinición del método equals
	 */
	@Override
	public boolean equals(Object o){
		if ((o instanceof Idioma) && ((Idioma) o).getNombre().equals(this.getNombre())){
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

	public Diccionario getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(Diccionario diccionario) {
		this.diccionario = diccionario;
	}
}

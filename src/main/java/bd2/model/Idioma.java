package bd2.model;

public class Idioma {

	private Diccionario diccionario;
	private String nombre;
	
	public Idioma(String nombre){
		this.nombre = nombre;
		this.diccionario = new Diccionario(this);
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

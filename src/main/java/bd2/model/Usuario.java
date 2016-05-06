package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * Esta clase modela a un usuario del sitio.
 * El mismo cuenta con un nombre, un email, una fecha de creacion, una colecciones de 
 * traducciones que hizo, y una coleccion de cursadas que realizó
 *
 */
public class Usuario {

	protected String nombre;
	protected String email;
	protected Date fechaDeCreacion;
	protected Collection<Traduccion> traducciones;
	protected Collection<Cursada> cursadasRealizadas;
	private long id; 

	/**
	 * Método constructor.
	 * 
	 * @param email ; un String que hace de email para el usuario.
	 * @param nombre ; un String que hace de nombre para el usuario.
	 * @param fechaDeCreacion ; un Date que especifica la fechad de creacion del usuario.
	 */
	public Usuario(){
		
	}
	
	public Usuario(String email, String nombre, Date fechaDeCreacion) {
		setNombre(nombre);
		setEmail(email);
		setFechaDeCreacion(fechaDeCreacion);
		setCursadasRealizadas(new ArrayList<Cursada>());
		setTraducciones(new ArrayList<Traduccion>());
	}

	/**
	 * Método que busca el nivel más alto para un idioma de las cursadas que el usuario
	 * realizó
	 * @param idioma ; un Idioma para el cual buscar el nivel.
	 * @return un int que refleja el nivel más alto del idioma dado.
	 */
	public int nivel(Idioma idioma) {
		int nivel = 0;
		for (Cursada cursada : cursadasAprobadas(idioma)) {
			if ( cursada.getNivel() > nivel )
				nivel = cursada.getNivel();
		}
		return nivel;
	}

	/**
	 * Método que obtiene, dado un Idioma, una coleccion de cursadas aprobadas para el mismo que el
	 * usuario realizó
	 * 
	 * @param idioma ; un Idioma para el cual buscar las cursadas aprobadas.
	 * @return una coleccion de cursadas que fueron aprobadas.
	 */
	public Collection<Cursada> cursadasAprobadas(Idioma idioma) {
		ArrayList<Cursada> cursadasAprobadas = new ArrayList<Cursada>();
		for (Cursada cursada : getCursadasRealizadas()) {
			if (cursada.getIdioma().equals(idioma) && cursada.finalizada())
				cursadasAprobadas.add(cursada);
		}		
		return cursadasAprobadas;
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
	
	public void agregarCursada(Cursada cursada) {
		this.cursadasRealizadas.add(cursada);
	}

	public void agregarTraduccion(Traduccion traduccion) {
		this.traducciones.add(traduccion);
	}

	public Collection<Traduccion> getTraducciones() {
		return traducciones;
	}

	public Collection<Cursada> getCursadasRealizadas() {
		return cursadasRealizadas;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	private void setCursadasRealizadas(Collection<Cursada> cursadasRealizadas) {
		this.cursadasRealizadas = cursadasRealizadas;
	}

	private void setTraducciones(Collection<Traduccion> traducciones) {
		this.traducciones = traducciones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

}

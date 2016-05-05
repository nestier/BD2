package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Usuario {

	private String nombre;
	private String email;
	private Date fechaDeCreacion;
	private Collection<Traduccion> traducciones;
	private Collection<Cursada> cursadasRealizadas;

	public Usuario(String email, String nombre, Date fechaDeCreacion) {
		setNombre(nombre);
		setEmail(email);
		setFechaDeCreacion(fechaDeCreacion);
		setCursadasRealizadas(new ArrayList<Cursada>());
	}

	public int nivel(Idioma idioma) {
		int nivel = 0;
		for (Cursada cursada : cursadasAprobadas(idioma)) {
			if ( cursada.getNivel() > nivel )
				nivel = cursada.getNivel();
		}
		return nivel;
	}

	public Collection<Cursada> cursadasAprobadas(Idioma idioma) {
		ArrayList<Cursada> cursadasAprobadas = new ArrayList<Cursada>();
		for (Cursada cursada : getCursadasRealizadas()) {
			if (cursada.getIdioma().equals(idioma) && cursada.finalizada())
				cursadasAprobadas.add(cursada);
		}		
		return cursadasAprobadas;
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

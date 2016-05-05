package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Cursada {

	private Curso curso;
	private Collection<Prueba> pruebas;
	private Usuario usuario;
	private Date inicio;

	public Cursada(Curso curso, Date inicio, Usuario usuario) {
		setCurso(curso);
		setInicio(inicio);
		setUsuario(usuario);
		this.pruebas = new ArrayList<Prueba>();
		usuario.agregarCursada(this);
	}
	
	public boolean finalizada(){
		for (Leccion leccion : getCurso().getLecciones()) {
			boolean aprobada = false;
			for (Prueba prueba : getPruebas()) {
				if ( prueba.getLeccion().equals(leccion) && prueba.aprobada())
					aprobada = true;
			}
			if (!aprobada) return false;
		}
		return true;
	}
	
	public Collection<Leccion> leccionesAprobadas(){
		ArrayList<Leccion> leccionesAprobadas = new ArrayList<Leccion>();
		for (Prueba prueba : getPruebas()) {
			if (prueba.aprobada() && !leccionesAprobadas.contains(prueba.getLeccion())){
				leccionesAprobadas.add(prueba.getLeccion());
			}
		}
		return leccionesAprobadas;
	}

	public void agregarPrueba(Prueba prueba) {
		this.pruebas.add(prueba);
	}

	public Idioma getIdioma() {
		return getCurso().getIdioma();
	}

	public int getNivel() {
		return getCurso().getNivel();
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Collection<Prueba> getPruebas() {
		return pruebas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}

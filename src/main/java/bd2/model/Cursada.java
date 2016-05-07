package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * Esta clase tiene la finalidad de modelar una cursada correspondiente a un usuario, la misma 
 * consta de un curso que se dicta, una fecha de inicio, y una coleccion de pruebas asociadas.
 *
 */
public class Cursada {

	private Curso curso;
	private Collection<Prueba> pruebas;
	private Usuario usuario;
	private Date inicio;
	private long id; 

	/**
	 * Método constructor.
	 * 
	 * @param curso ; es el Curso asociado a la cursada.
	 * @param inicio ; es un Date que marca el día de inicio de la cursada.
	 * @param usuario ; es el Usuario que realiza la cursada
	 */
	public Cursada(){

	}
	public Cursada(Curso curso, Date inicio, Usuario usuario) {
		setCurso(curso);
		setInicio(inicio);
		setUsuario(usuario);
		this.pruebas = new ArrayList<Prueba>();
		usuario.agregarCursada(this);
	}
	
	/**
	 * Método que se encarga de informar si la cursada de encuentra aprobada,
	 * es decir, si existe al menos una prueba aprobada para cada lección del curso.
	 * 
	 * @return true en caso de estar finalizada, false en caso contrario.
	 */
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
	
	/**
	 * Método que obtiene las lecciones del curso para las que existe una prueba aprobada.
	 * @return una coleccion de lecciones aprobadas.
	 */

	
	public Collection<Leccion> leccionesAprobadas(){
		ArrayList<Leccion> leccionesAprobadas = new ArrayList<Leccion>();
		for (Prueba prueba : getPruebas()) {
			if (prueba.aprobada() && !leccionesAprobadas.contains(prueba.getLeccion())){
				leccionesAprobadas.add(prueba.getLeccion());
			}
		}
		return leccionesAprobadas;
	}

	/**
	 * Getters y setters. 
	 */
	public void setPruebas(Collection<Prueba> pruebas){
		this.pruebas = pruebas;
	}
	
	public void setPruebas(){
		this.pruebas = new ArrayList<Prueba>();
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

	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id; 
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

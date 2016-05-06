package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 
 * Esta clase modela al sitio completo, el cual cuenta con una coleccion de usuarios,
 * una coleccion de documentos, y una coleccion de cursos.
 *
 */
public class Sitio {
	
	private Collection<Usuario> usuarios;
	private Collection<Documento> documentos;
	private Collection<Curso> cursos;
	private long id; 
	/**
	 * Constructor. 
	 */	
	public Sitio(){
		this.setUsuarios();
		this.setDocumentos();
		this.setCursos();
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
	
	public void registrarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}
	public void setUsuarios(Collection<Usuario> usuarios){
		this.usuarios = usuarios;
	}
	public void setCursos(Collection<Curso> cursos){
		this.cursos = cursos;
	}
	public void setDocumentos(Collection<Documento> documentos){
		this.documentos = documentos;
	}

	public void agregarCurso(Curso curso) {
		this.cursos.add(curso);
	}
	public void agregarDocumento(Documento documento) {
		this.documentos.add(documento);
	}
	
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}
	public Collection<Curso> getCursos() {
		return cursos;
	}
	public Collection<Documento> getDocumentos() {
		return documentos;
	}
	public void setUsuarios() {
		this.usuarios = new ArrayList<Usuario>();
	}
	public void setDocumentos() {
		this.documentos = new ArrayList<Documento>();
	}
	public void setCursos() {
		this.cursos = new ArrayList<Curso>();
	}

}

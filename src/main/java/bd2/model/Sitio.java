package bd2.model;

import java.util.List;

public class Sitio {
	
	private List<Usuario> usuarios;
	private List<Documento> documentos;
	private List<Curso> cursos;
	
	public void registrarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}
	public void agregarCurso(Curso curso) {
		this.cursos.add(curso);
	}
	public void agregarDocumento(Documento documento) {
		this.documentos.add(documento);
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public List<Documento> getDocumentos() {
		return documentos;
	}	

}

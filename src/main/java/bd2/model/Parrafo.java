package bd2.model;

public class Parrafo {

	private Documento documento;
	private String texto;

	public Parrafo(String texto, Documento documento) {
		setTexto(texto);
		setDocumento(documento);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
}

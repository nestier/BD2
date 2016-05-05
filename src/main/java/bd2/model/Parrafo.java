package bd2.model;

/**
 * 
 * Esta clase tiene la finalidad de modelar un parrafo para un documento especifico,
 * contando con un texto en cuestión.
 *
 */
public class Parrafo {

	private Documento documento;
	private String texto;
	private Long id; 

	/**
	 * Método constructor.
	 * 
	 * @param texto ; un String que hace de texto del parrafo.
	 * @param documento ; un Documento al que se asocia el parrafo.
	 */
	public Parrafo() {
		
	}
	
	public Parrafo(String texto, Documento documento) {
		setTexto(texto);
		setDocumento(documento);
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

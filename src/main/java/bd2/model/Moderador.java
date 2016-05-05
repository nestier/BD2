package bd2.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Moderador extends Usuario {
	private Collection<Idioma> idiomas;
	private Collection<Evaluacion> evaluaciones;

	public Moderador(String email, String nombre, Date fechaDeCreacion) {
		super(email, nombre, fechaDeCreacion);
		this.idiomas = new ArrayList<Idioma>();
		this.evaluaciones = new ArrayList<Evaluacion>();
	}

	public int reputacion() {
		return getEvaluaciones().size();
	}

	public void evaluar(Traduccion traduccion, String descripcion, int puntaje) throws Exception {
		if (manejaIdioma(traduccion.getIdioma())
				&& manejaIdioma(traduccion.getIdiomaOriginal())) {
			Calendar cal = Calendar.getInstance();
			Date hoy = cal.getTime();
			Evaluacion evaluacion = new Evaluacion(hoy, descripcion, true,
					traduccion, puntaje);
			this.evaluaciones.add(evaluacion);
		} else
			throw new Exception("No se pueden evaluar traducciones de idiomas que el moderador no maneja.");

	}

	public boolean manejaIdioma(Idioma idioma) {
		if (getIdiomas().contains(idioma))
			return true;
		else
			return false;
	}

	public void agregarIdioma(Idioma idioma) {
		this.idiomas.add(idioma);
	}

	public Collection<Idioma> getIdiomas() {
		return idiomas;
	}

	public Collection<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
}

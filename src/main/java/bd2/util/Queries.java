package bd2.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bd2.model.*;

public class Queries {
	
private static SessionFactory sessions;
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		try {
			ejecutarConsultaA(session);
			ejecutarConsultaB(session);
			ejecutarConsultaC(session);
			Calendar fechaInicio = Calendar.getInstance();
			fechaInicio.set(2015, 7, 1);			
			Calendar fechaFin = Calendar.getInstance();
			fechaFin.set(2015, 12, 31);
			ejecutarConsultaD(session, fechaInicio.getTime(), fechaFin.getTime());
			ejecutarConsultaE(session);
			ejecutarConsultaF(session);
			ejecutarConsultaG(session, "Leuchtturm");
			
		} catch (Exception e) {
					e.printStackTrace();
					session.close();
		}
		finally {
		session.disconnect() ;}
	}

	private static void ejecutarConsultaA(Session session) {
		Transaction tx = null;
		System.out.println("A.	Listar los nombres de todos los documentos ");

		Query query = session.createQuery("select d from "+Documento.class.getName()+" d");

		try {
			tx = session.beginTransaction();
			List<Documento> documentos = query.list();
			tx.commit();
			session.flush();
			for (Documento d: documentos) {
				System.out.println("Documento: "+d.getNombre());
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	private static void ejecutarConsultaB(Session session) {
		Transaction tx = null;
		System.out.println("B.	Listar los emails de los moderadores que hayan evaluado traducciones al inglés.");

		Query query = session.createQuery("select email from "+Moderador.class.getName()+" moderador"
				+ " where exists ( from "+Evaluacion.class.getName()+" evaluacion"
				+ " where evaluacion in elements(moderador.evaluaciones) "
				+ " and evaluacion.traduccion.idioma.nombre = 'Inglés') ");

		try {
			tx = session.beginTransaction();
			List<String> emails = query.list();
			tx.commit();
			session.flush();
			for (String email: emails) {
				System.out.println("Email: "+email);
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	private static void ejecutarConsultaC(Session session) {
		Transaction tx = null;
		System.out.println("C.	Listar los usuarios que hayan iniciado una cursada de Francés de nivel 3 como mínimo.");

		Query query = session.createQuery("select usuario from "+Usuario.class.getName()+" usuario"
				+ " where exists ( from "+Cursada.class.getName()+" cursada"
				+ " where cursada in elements(usuario.cursadasRealizadas) "
				+ " and cursada.curso.idioma.nombre = 'Francés' "
				+ " and cursada.curso.nivel >= 3) ");

		try {
			tx = session.beginTransaction();
			List<Usuario> usuarios = query.list();
			tx.commit();
			session.flush();
			for (Usuario usuario: usuarios) {
				System.out.println("Nombre: "+usuario.getNombre());
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	private static void ejecutarConsultaD(Session session, Date fechaInicio, Date fechaFin) {
		Transaction tx = null;
		System.out.println("D.	Listar moderadores que hayan revisado alguna traducción entre dos fechas pasadas como argumento.");
		System.out.println("Resultado para los parámetros: "+fechaInicio+" hasta "+fechaFin);
        Query query = session.createQuery("from Moderador m "
            + " where exists ( from Evaluacion e"
            + " where e in elements(m.evaluaciones) "
            + "and e.fecha between :fechaInicio and :fechaFin) ")
            .setParameter("fechaInicio", fechaInicio).setParameter("fechaFin", fechaFin);
		try {
			tx = session.beginTransaction();
			List<Moderador> moderadores = query.list();
			tx.commit();
			session.flush();
			for (Moderador moderador: moderadores) {
				System.out.println("Nombre: "+moderador.getNombre());
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	private static void ejecutarConsultaE(Session session) {
		Transaction tx = null;
		System.out.println("E.	Listar traducciones completas del Inglés al Francés.");

        // Query query = session.createQuery("from Tarea t "
        //     + " where t.idioma.nombre = 'Francés' "
        //     + " and t.parrafo.documento.idioma.nombre = 'Inglés' "
        //     + " and t.completa = true");
        Query query = session.createQuery("select descripcion from Tarea traduccion"
            + " where traduccion.idioma.nombre = 'Francés' "
            + " and traduccion.completa = true "
            + " and exists ( from Parrafo parrafo "
            + " where parrafo.documento.idioma.nombre = 'Inglés'"
            + " and parrafo.id = traduccion.parrafo.id) ");


		try {
			tx = session.beginTransaction();
			List<String> descripciones = query.list();
			tx.commit();
			session.flush();
			for (String descripcion: descripciones) {
				System.out.println("Nombre: "+descripcion);
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	private static void ejecutarConsultaF(Session session) {
		Transaction tx = null;
		System.out.println("F.	Obtener los emails de los usuarios con alguna cursada aprobada.");

		Query query = session.createQuery("select usuario from "+Usuario.class.getName()+" usuario "
				+ " join usuario.cursadasRealizadas cursadasRealizadas "
				+ " where cursadasRealizadas.id = any ( "
				+ "	select id from "+Cursada.class.getName()+" cursada " 
				+ " where size(cursada.curso.lecciones) = ( "
				+ "	select count( distinct prueba.leccion) from "+Prueba.class.getName()+" prueba "
						+ " where prueba.puntaje >= 60 "
						+ " and prueba in elements(cursada.pruebas) )  "
				+ ") ");

		try {
			tx = session.beginTransaction();
			List<Usuario> usuarios = query.list();
			tx.commit();
			session.flush();
			for (Usuario usuario: usuarios) {
				System.out.println("Usuario con cursada aprobada: "+usuario.getEmail());
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	private static void ejecutarConsultaG(Session session, String palabra) {
		Transaction tx = null;
		System.out.println("G.	Obtener el nombre del idioma que define la palabra enviada como parámetro en su diccionario.");
		System.out.println("Resultado para el parámetro: "+palabra);
		Query query = session.createQuery("select idioma from "+Idioma.class.getName()+" idioma "
				+ " where exists ( "
				+ "	from "+Diccionario.class.getName()+" diccionario " 
				+ " where :palabra in indices(diccionario.definiciones)"
				+ " and diccionario.idioma.id = idioma.id )").setParameter("palabra", palabra);

		try {
			tx = session.beginTransaction();
			List<Idioma> idiomas = query.list();
			tx.commit();
			session.flush();
			for (Idioma idioma: idiomas) {
				System.out.println("El idioma "+idioma.getNombre()+" define la palabra "+palabra);
			}
			System.out.println();
		
		} catch (HibernateException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();}
		}
	}
	
}
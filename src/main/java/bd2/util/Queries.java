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

/**
 * Clase que implementa todas las consultas solicitadas por la cátedra.
 *
 */
public class Queries {
	
	private static SessionFactory sessions;
	
	/**
	 * Método Main que realiza el llamado a todas las consultas implementadas, pasándole el objeto
	 * 	Session "abierto" con la configuración de hibernate por parámetro y añadiendo parámetros 
	 * 	de prueba para aquellas consultas que así lo requieren.
	 * @param args
	 */
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
			ejecutarConsultaH(session);
			ejecutarConsultaI(session, "Alemán");
			
		} catch (Exception e) {
					e.printStackTrace();
					session.close();
		}
		finally {
		session.disconnect() ;}
	}

	/**
	 * Método que se encarga de obtener y listar los nombres de todos los documentos
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 */
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
	
	/**
	 * Método que se encarga de obtener y listar los emails de los moderadores que hayan 
	 * 	evaluado traducciones al Inglés
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 */
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
	
	/**
	 * Método que se encarga de obtener y listar los usuarios que hayan iniciado una cursada
	 * 	de Francés de nivel 3 como mínimo.
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 */
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
	
	/**
	 * Método que se encarga de obtener y listar los moderadores que hayan revisado alguna
	 * 	traducción entre las dos fechas recibidas por parámetro
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 * @param fechaInicio
	 * 				| Objeto Date que representa la fecha de inicio del intervalo a consultar
	 * @param fechaFin
	 * 				| Objeto Date que representa la fecha de fin del intervalo a consultar
	 */
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
	
	/**
	 * Método que se encarga de obtener y listar las traducciones completas del idioma Inglés al Francés
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 */
	private static void ejecutarConsultaE(Session session) {
		Transaction tx = null;
		System.out.println("E.	Listar traducciones completas del Inglés al Francés.");

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
	
	/**
	 * Método que se encarga de obtener los usuarios con alguna cursada aprobada y 
	 * 	listar sus emails.
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 */
	private static void ejecutarConsultaF(Session session) {
		Transaction tx = null;
		System.out.println("F.	Obtener los emails de los usuarios con alguna cursada aprobada.");

		Query query = session.createQuery(" from Usuario u "
				+ " where exists ( "
				+ " from Cursada cursada"
				+ " where cursada in elements(u.cursadasRealizadas)"
				+ " and not exists ( "
					+ "	from Leccion l "
					+ " where l in elements(cursada.curso.lecciones) "
					+ " and l not in ( "
						+ " from Leccion l2 "
						+ " where exists ( "
							+ " from Prueba p "
							+ " where p.puntaje >= 60 "
							+ " and p.leccion = l2 "
							+ " and p in elements(cursada.pruebas)"
							+ " and p.leccion in elements(cursada.curso.lecciones) )  "
				+ ") ) )");


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
	
	/**
	 * Método que se encarga de obtener el idioma que define la palabra recibida por parámetro
	 * 	en su diccionario e imprimir su nombre.
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 * @param palabra
	 * 				| Un String que es la palabra para realizar la busqueda de su idioma
	 */
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
	
	/**
	 * Método que se encarga de obtener los documentos que no tienen párrafos traducidos en ningún idioma,
	 * 	y lista sus nombres.
	 * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
	 */
    private static void ejecutarConsultaH(Session session) {
       Transaction tx = null;
       System.out.println(" H. Obtener los nombres de los documentos que no tengan ningún párrafo traducido (en ningún idioma) ");
    
       Query query = session.createQuery("from Documento d "
               + " where d not in (select t.parrafo.documento from Traduccion t)");
    
       try {
          tx = session.beginTransaction();
          List<Documento> documentos = query.list();
          tx.commit();
          session.flush();
          for (Documento documento: documentos) {
            System.out.println("El documento " + documento.getNombre() + " no tiene ninguna traducción.");
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

    /**
     * Método que se encarga de obtener los documentos que no tienen párrafos sin traducir al idioma
     * 	recibido por parámetro, y listar sus nombres.
     * @param session
	 * 				| Objeto SessionFactory con la sesion de hibernate abierta
     * @param idioma
     * 				| Un String que es el nombre del idioma para el cual se realiza la búsqueda
     */
    private static void ejecutarConsultaI(Session session, String idioma) {
        Transaction tx = null;
        System.out.println("I.	Obtener los nombres de los documentos que tengan párrafos sin traducir al idioma de nombre enviado como parámetro.");
        System.out.println("Resultados para el parámetro: "+idioma);
        Query query = session.createQuery("select distinct p.documento from Parrafo p"
                + " where p not in ( select t.parrafo from Traduccion t"
                + " where :idioma = t.idioma.nombre)").setParameter("idioma", idioma);

        try {
            tx = session.beginTransaction();
            List<Documento> documentos = query.list();
            tx.commit();
            session.flush();
            for (Documento documento: documentos) {
                System.out.println("El documento "+documento.getNombre()+" no esta totalmente traducido.");
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
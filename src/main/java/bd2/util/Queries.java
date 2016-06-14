package bd2.util;

import java.text.SimpleDateFormat;
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
	

}
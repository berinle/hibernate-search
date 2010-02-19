package net.berinle.hibsearch.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	static{
		try{
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}

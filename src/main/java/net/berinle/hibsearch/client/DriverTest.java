package net.berinle.hibsearch.client;

import org.hibernate.SessionFactory;

import net.berinle.hibsearch.util.HibernateUtil;

public class DriverTest {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.close();
	}
}

package net.berinle.hibsearch.client;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import net.berinle.hibsearch.model.Contact;
import net.berinle.hibsearch.util.HibernateUtil;

public class DataSetIndexBuilder {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		FullTextSession fts = Search.getFullTextSession(session);
		fts.setFlushMode(FlushMode.MANUAL);
		fts.setCacheMode(CacheMode.IGNORE);
		
		Transaction tx = fts.beginTransaction();
		ScrollableResults results = fts.createCriteria(Contact.class)
		.scroll(ScrollMode.FORWARD_ONLY);
		
		int index = 0;
		long start = System.currentTimeMillis();
		while(results.next()){
			index++;
			fts.index(results.get(0));
			if(index % 20 == 0){
				System.out.print(".");
				fts.flushToIndexes();
				fts.clear();
			}
		}
		
		results.close();
		
		long end = System.currentTimeMillis();
		
		System.out.println(" time elapsed: " + (end - start) / 1000);
		
		tx.commit();
		
		session.close();
		factory.close();
	}
}

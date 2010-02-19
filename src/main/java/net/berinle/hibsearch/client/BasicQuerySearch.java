package net.berinle.hibsearch.client;

import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import net.berinle.hibsearch.model.Contact;
import net.berinle.hibsearch.util.HibernateUtil;

public class BasicQuerySearch {
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		FullTextSession fts = Search.getFullTextSession(session);
		fts.getTransaction().begin();
		
		//Term term = new Term("lastName", "barnes" );
		Term term = new Term("lastName", "er*" );
		//Term term = new Term("last_name", "Barnes" );
		//TermQuery query = new TermQuery(term);
		WildcardQuery query = new WildcardQuery(term);
		
		long start = System.currentTimeMillis();
		FullTextQuery ftq = fts.createFullTextQuery(query, Contact.class);
		List<Contact> list = ftq.list();
		
		for(Contact c: list){
			System.out.println(c.getFirstName() + ", " + c.getLastName());
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(" found (" + list.size() + ") matching records.");
		System.out.println(" total time: " + (end - start)/1000 + " ms");
		fts.getTransaction().commit();
		session.close();
		sf.close();
	}
}

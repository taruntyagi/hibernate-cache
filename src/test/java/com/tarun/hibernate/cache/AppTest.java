package com.tarun.hibernate.cache;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.tarun.hibernate.cache.dao.ApplicationAttributes;
import com.tarun.hibernate.cache.util.HibernateWorker;

/**
 * Unit test for simple App.
 */
public class AppTest {
	Logger logger = Logger.getLogger(AppTest.class);

	/**
	 * Rigourous Test :-)
	 */
	public void testSimpleQueryExecution() {
		try {
			Session session = HibernateWorker.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("FROM ApplicationAttributes where attributeId = 1");
			List<?> result = query.list();
			System.out.println(result.toString());

			query = session.createQuery("FROM ApplicationAttributes where attributeId = 1");
			result = query.list();
			System.out.println(result.toString());

			tx.commit();
			session.close();
			Assert.assertTrue(true);
		}finally {
			System.out.println(HibernateWorker.getFetchCount());
		}
	}

	public void testFirstLevelCacheExecution() {
		System.out.println("------Executing first level cache-------");
		Session session = HibernateWorker.openSession();
		Transaction tx = session.beginTransaction();
		ApplicationAttributes appAttr1 = (ApplicationAttributes) session.load(ApplicationAttributes.class, 1);
		System.out.println(appAttr1.toString());
		
		session.evict(appAttr1);
		
		ApplicationAttributes appAttr2 = (ApplicationAttributes) session.load(ApplicationAttributes.class, 1);
		System.out.println(appAttr2.toString());
		
		
		tx.commit();
		session.close();
		
		session = HibernateWorker.openSession();
		tx = session.beginTransaction();
		ApplicationAttributes appAttr3 = (ApplicationAttributes) session.load(ApplicationAttributes.class, 1);
		System.out.println(appAttr3.toString());

		tx.commit();
		session.close();
		Assert.assertTrue(true);
	}
	
	@Test
	public void testSecondLevelCache() {
		Session session = HibernateWorker.openSession();
		Transaction tx = session.beginTransaction();
		ApplicationAttributes appAttr1 = (ApplicationAttributes) session.load(ApplicationAttributes.class, 1);
		System.out.println(appAttr1.toString());
		tx.commit();
		session.close();
		
		session = HibernateWorker.openSession();
		tx = session.beginTransaction();
		
		ApplicationAttributes appAttr2 = (ApplicationAttributes) session.load(ApplicationAttributes.class, 1);
		System.out.println(appAttr2.toString());
		
		
		tx.commit();
		session.close();
		
		session = HibernateWorker.openSession();
		tx = session.beginTransaction();
		
		ApplicationAttributes appAttr3 = (ApplicationAttributes) session.load(ApplicationAttributes.class, 1);
		System.out.println(appAttr3.toString());
		
		tx.commit();
		session.close();
		
		System.out.println("Fetch count : "+HibernateWorker.getFetchCount());
		System.out.println("Cache hit count : "+HibernateWorker.getCacheHitCount());
		Assert.assertTrue(true);
		
	}
}

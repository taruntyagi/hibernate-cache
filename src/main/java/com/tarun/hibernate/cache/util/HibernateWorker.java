/**
 * 
 */
package com.tarun.hibernate.cache.util;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.stat.Statistics;

/**
 * The Class HibernateWorker is to build the {@link SessionFactory} class and
 * provide an instance of {@link Session} class. The {@link SessionFactory}
 * built by mapping all Data Access Object classes to database tables.
 * 
 * Also the responsibility of this class is to create and provide the
 * {@link SessionFactory#getCurrentSession()}
 *
 * @author taruntyagi
 */
public class HibernateWorker {

	/** The logger. */
	private static Logger logger = Logger.getLogger(HibernateWorker.class);

	/** The cfg file uri. */
	private static URL cfgFileUri = HibernateWorker.class.getResource("/hibernate.cfg.xml");

	/** The session factory. */
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Builds the session factory.
	 *
	 * @return the session factory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			logger.debug("Create the SessionFactory from hibernate.cfg.xml");
			File file = new File(cfgFileUri.toURI());
			Configuration configuration = new Configuration().configure(file);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Exception ex) {
			logger.debug("Exception occured while Configuring and building session factory, so converting it into error"
					+ ex);
			throw new ExceptionInInitializerError(ex);
		} catch (Error ex) {
			logger.debug("Exception occured while Configuring and building session factory, so converting it into error"
					+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Returns the session factory instance.
	 *
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Close the session factory instance.
	 */
	public static void closeSessionFactory() {
		sessionFactory.close();
	}

	/**
	 * Return current session instance using which query has to be executed.
	 * 
	 * @return Session current session instance
	 */
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Open session.
	 *
	 * @return the session
	 */
	public static Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Gets the statistics.
	 *
	 * @return the statistics
	 */
	public static Statistics getStatistics() {
		return sessionFactory.getStatistics();
	}

	/**
	 * Gets the fetch count.
	 *
	 * @return the fetch count
	 */
	public static long getFetchCount() {
		return sessionFactory.getStatistics().getEntityFetchCount();
	}

	/**
	 * Gets the cache hit count.
	 *
	 * @return the cache hit count
	 */
	public static long getCacheHitCount() {
		return sessionFactory.getStatistics().getSecondLevelCacheHitCount();
	}

}

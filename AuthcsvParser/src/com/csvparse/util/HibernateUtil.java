package com.csvparse.util;



import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.csvparse.entity.DailyAUATransaction;


public class HibernateUtil {
	private static final Logger _log = Logger.getLogger(HibernateUtil.class);

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	static {
		if (sessionFactory == null) {
			loadSessionFactory();
		}
	}

	private static void loadSessionFactory() {
		try {

			Configuration conf = new AnnotationConfiguration().addAnnotatedClass(DailyAUATransaction.class)
					.configure();
			conf.getProperties().setProperty(
					"hibernate.connection.url",
					Utils.getPropertyValues(Constants.HIBERNATE_CONNECTION_URL)
							.toString());
			conf.getProperties().setProperty(
					"hibernate.connection.username",
					Utils.getPropertyValues(Constants.HIBERNATE_CONNECTION_USERNAME)
							.toString());
			conf.getProperties().setProperty(
					"hibernate.connection.password",
					Utils.getPropertyValues(Constants.HIBERNATE_CONNECTION_PASSWORD)
							.toString());
			sessionFactory = conf.buildSessionFactory();
				} catch (HibernateException ex) {
					_log.error("Initial Session factory creation failed = "
							+ ex.getMessage());
					_log.error("Initial Session factory creation failed. " + ex);
					ex.printStackTrace();
				} catch (Exception e) {
					_log.error("Exception while creating Session factory obj.");
					e.printStackTrace();
				}
	}


	public static Session getSession() {
		return sessionFactory.openSession();
	}
}

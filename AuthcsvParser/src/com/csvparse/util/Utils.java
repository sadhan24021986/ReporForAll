package com.csvparse.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/*import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
*/
import org.apache.log4j.Logger;

public class Utils {
	static Logger log = Logger.getLogger(Utils.class);

	private static Properties properties = null;

	public static String getPropertyValues(String key) {
		try {
			if (properties == null) {
				properties = new Properties();
				/*
				 * process1 and process 2 does not work if we provide "E:\\Learning\\hibernate.application.properties" as file path.it only work
				 * if config.properties is in src path
				 * 
				 * process 1
				 * InputStream in =Utils.class.getClassLoader().getResourceAsStream("config.properties");*/
				/*
				 * process 2*/
				 /* ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream in =classLoader.getResourceAsStream( "config.properties" );*/
				
				FileInputStream in = new FileInputStream(
						"E:\\Learning\\csvParser.application.properties");
				
				properties.load(in);
			}
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

/*	public static DirContext getDirContext() {
		try {
			Hashtable<String, String> ldapEnv = new Hashtable<String, String>(
					11);
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY,
					Utils.getPropertyValues("ldap.contextfactory"));
			ldapEnv.put(Context.PROVIDER_URL,
					Utils.getPropertyValues("ldap.url"));
			ldapEnv.put(Context.SECURITY_AUTHENTICATION,
					Utils.getPropertyValues("ldap.authentication"));
			ldapEnv.put(Context.SECURITY_PRINCIPAL,
					Utils.getPropertyValues("ldap.principle"));
			ldapEnv.put(Context.SECURITY_CREDENTIALS,
					Utils.getPropertyValues("ldap.crendential"));
			return new InitialDirContext(ldapEnv);
		} catch (Exception e) {
			System.out.println(" Search error: " + e);
			e.printStackTrace();
		}
		return null;
	}*/
}

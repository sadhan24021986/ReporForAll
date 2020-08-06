package com.csvparse.dao;



import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.csvparse.entity.DailyAUATransaction;
import com.csvparse.util.HibernateUtil;



public class HibernateDao {

	Logger log=Logger.getLogger(HibernateDao.class);
	SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	
	public boolean insertValuesinDB( DailyAUATransaction auaTrans) throws Exception {
		boolean result=true;
		Session session=null;
		Transaction tx=null;
		try{
		session = sessionFactory.openSession();
		tx=session.beginTransaction();
		session.save(auaTrans);
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			log.error("error in auaTrans save for : "+e);
			throw e;
		}
		finally{
			if(session.isOpen()){
			session.close();
			}
		}
		return result;
		
	}

/*	public boolean addEmployee(Employee emp) throws Exception {
		boolean result=true;
		Session session=null;
		Transaction tx=null;
		try{
		session = sessionFactory.openSession();
		tx=session.beginTransaction();
		session.save(emp);
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			log.error("error in addUser() for : "+e);
			throw e;
		}
		finally{
			if(session.isOpen()){
			session.close();
			}
		}
		return result;
	}*/
}

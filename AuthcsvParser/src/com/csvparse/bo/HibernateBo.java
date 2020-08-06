package com.csvparse.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.csvparse.dao.HibernateDao;
import com.csvparse.entity.DailyAUATransaction;



public class HibernateBo {

	

	Logger log=Logger.getLogger(HibernateBo.class);
	HibernateDao hibernateDao = new HibernateDao(); 

	


	
	public boolean insertValuesinDB( ConcurrentHashMap<String,String> aggregationMap) {
		boolean result=true;
		
		SimpleDateFormat source = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat target = new SimpleDateFormat("dd-MM-yyyy");
		try{
			if(aggregationMap != null ){
	        	for (Entry<String, String> entry : aggregationMap.entrySet()) {
	        		DailyAUATransaction auaTrans = new DailyAUATransaction();
	        		String primaryValues = entry.getKey();
	        		String aggValue = entry.getValue();
	        		String[] values = primaryValues.split("\\|");
	        		String auaCode = values[0];
	        		String asaCode = values[1];
	        		String requestDate = values[2];
	        		/*Date date = formatter.parse(requestDate);
	        		Date aa = formatter.format(date);*/
	        		String dateStr = target.format(source.parse(requestDate));
	        		Date date = target.parse(dateStr);
	        		String aggType = values[3];
	        		auaTrans.setAuaCode(auaCode);
	        		auaTrans.setAsaCode(asaCode);
	        		auaTrans.setRequestDate(date);
	        		auaTrans.setAggType(aggType);
	        		auaTrans.setAggValue(Double.valueOf(aggValue));
	        		auaTrans.setCreateDate(new Date());
	        		auaTrans.setCreatedBy("Admin");
	        		auaTrans.setLastUpdateDate(new Date());
	        		auaTrans.setLastUpdatedBy("Admin");
	        		result=hibernateDao.insertValuesinDB(auaTrans);
	        	}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("error in normalEntry() for : "+e);
		}
		return result;
	}

	
	/*public boolean genericGeneratorExample()
	{
		boolean result=false;
		Employee emp=new Employee();
		emp.setFirstname("firstname");
		emp.setCellphone("9809897898");
		try{
			result=hibernateDao.addEmployee(emp);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("error in normalEntry() for : "+e);
		}
		return result;
	}	*/
}

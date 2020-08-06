package com.csvparse;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.tools.ant.DirectoryScanner;

import com.csvparse.bo.CsvBo;
import com.csvparse.boImpl.CsvBoImpl;
import com.csvparse.util.Utils;


public class MAIN {

	private static final Logger log = Logger.getLogger(MAIN.class);

	static String line="";
	
	
	/**
	 * @param args
	 */
	
	private static String toDate(){

		Calendar cal=Calendar.getInstance();
		System.out.println(cal);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date date = cal.getTime();             
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String date1 = format1.format(date);
		
		return date1;
		
	}
	
	
	private static String fromDate(){

		Calendar cal1=Calendar.getInstance();
		System.out.println(cal1);
		cal1.add(Calendar.DAY_OF_MONTH, -3);
		Date dateData = cal1.getTime();             
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		String date2 = format2.format(dateData);
		
		
		return date2;
		
	}
	
	
	public static void main(String[] args) {
		Long count=new Long(0);
		//File folder = new File("D:\\CSVParser\\TestingFiles\\");
		String toDate=toDate();
		String fromDate=fromDate();
		
		
		DirectoryScanner scanner = new DirectoryScanner();
		//scanner.setIncludes(new String[]{"**/*.java"});
		
		//:/bsrdata/prodbi/fs12/datasets/internal/auth_report_historical_data
		//:/bsrdata/prodbi/fs12/datasets/internal/
		scanner.setBasedir(Utils.getPropertyValues("AUA_TRANSACTION_PATH")+"\\"+"auth_report");
		scanner.setCaseSensitive(false);
		scanner.scan();
		String[] filesInAuthReport = scanner.getIncludedDirectories();
		
		List<String> dateFolderList=new ArrayList<>();
		for(String fileiar :filesInAuthReport){
			
			try{
			if(Integer.parseInt(fileiar) <= Integer.parseInt(toDate)  && Integer.parseInt(fileiar) >= Integer.parseInt(fromDate)){
			
			dateFolderList.add(fileiar);
			}
			}catch(Exception ex){
				
			}
		}
		
		
		List<File> listOfFolderToBeProceed=new ArrayList<>();
		
		for(String folderPath: dateFolderList){
			listOfFolderToBeProceed.add(new File(scanner.getBasedir()+"\\"+folderPath+"\\Transaction"));
		}
		
		/*for(File folder: listOfFolderToBeProceed)
		{
		for(File file: folder.listFiles()){
			try{
			if( file.getPath().contains("_Transaction")  ){
			
			System.out.println(file.getName());
			
			}
			
			}catch(Exception ex){}
			}*/
	
		 ConcurrentHashMap<String,String> aggregationMap = new ConcurrentHashMap<String,String>();
			CsvBo csvbo=new CsvBoImpl();
			
			long l1=System.currentTimeMillis();
			csvbo.readCsvAndProceed(listOfFolderToBeProceed,aggregationMap,count);
         
		
	}


	
}

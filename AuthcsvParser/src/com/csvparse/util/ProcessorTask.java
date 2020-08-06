package com.csvparse.util;

import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.csvparse.runnable.ProcessThread;

public class ProcessorTask implements Runnable{
	
	private static final Logger log = Logger.getLogger(ProcessorTask.class);
	private final BlockingQueue<String> processingQueue;
	private final Future readerTask;
	private ProcessThread processThread;
	//volatile int count=0;
	ConcurrentHashMap<String,String>  map;
	
	public ProcessorTask(BlockingQueue<String> processingQueue, Future readerTask,ConcurrentHashMap<String,String>  map) {
		this.processingQueue = processingQueue;
		this.readerTask = readerTask;
		this.map=map;
		processThread=new ProcessThread();
		//this.path = path;
		/*processor = new Processor();*/
		//processor= getProcessorContext();
	}
	
	@Override
	public void run() {
		while(true){
			
			if(processingQueue.peek()==null && readerTask.isDone()){ // queue is empty 
				break;
			}
			
			
			try {
				if(log.isDebugEnabled()){
					log.debug("taking from processing queue.");
				}
				
				String take =  processingQueue.poll();  //processingQueue.take();
			System.out.println("take input:::"+take);	
				if(take!=null){
					
					/*try {*/
						//Output out = processor.process(take,path);
						
						processLine(take,map);
						
						
						
						if(log.isDebugEnabled()){
							log.debug("process from processing queue.");
						}
					/*
					} catch (Exception e) {
						log.error(e.getMessage(), e);}*/
				} 
			}catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			
		}
		
	//	System.out.println(count);
		if(log.isDebugEnabled())
			log.debug("Finishing processing.");
		
	}
	
	private void processLine(String lineTXT,ConcurrentHashMap<String,String> map){
		//totcount++;
    	
            String[] line = lineTXT.split(",");
           String auaCode = line[0];
        	String auaName = line[1]; 
        	String asaCode = line[4];
        	String asaName = line[5];
        	String requestTime = line[11];
        	String requestDate = requestTime.substring(0,10);  
            String authResult = line[10];
            String authDuration = line[13];
            String authType = line[9];
           // HashObject hashObj = new HashObject(auaCode, auaName, asaCode, asaName, requestDate);
           // int hashKey = hashObj.hashCode();
         //	 String  lineKey = String.valueOf(hashKey);
            //Total Txn Count
           String lineKey = "";
           
           	//Total Txn Count
           	lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"TotalTransactions");
            processThread.countTransactions(lineKey,map);
           
            //Total Success Count
             lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"TotalSuccessTransaction");
            processThread.countSuccess(lineKey,authResult,map);
            
            //Total Auth duration
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"TotalAuthDuration");
             processThread.countAuthDuration(lineKey,authDuration, map);
            
            //Total Auth count gt1
             lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"TotalAuthCountGt1");
             processThread.countAuthDurationGt1(lineKey,authDuration, map);
            
            //Total Bio txn count
             lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"BioTxnCount");
             processThread.countBioTxn(lineKey,authType, map);
            
            //Total Bio Success txn count
             lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"BioSuccessTxnCount");
             processThread.countBioSuccessTxn(lineKey,authType, authResult, map);
            
            //Total FP txn count
             lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"FPTxnCount");
            processThread.countFPTxn(lineKey,authType, map);
            
           //Total FP Success txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"FPSuccessTxnCount");
            processThread.countFPSuccessTxn(lineKey,authType, authResult, map);
            
          //Total Iris txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"IrisTxnCount");
            processThread.countIrisTxn(lineKey,authType, map);
            
           //Total Iris Success txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"IrisSuccessTxnCount");
            processThread.countIrisSuccessTxn(lineKey,authType, authResult, map);
            
            //Total OTP txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"OTPTxnCount");
            processThread.countOTPTxn(lineKey,authType, map);
            
            //Total OTP Success txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"OTPSuccessTxnCount");
            processThread.countOTPSuccessTxn(lineKey,authType,authResult, map);
            
            //Total Demo txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"DemoTxnCount");
            processThread.countDemoTxn(lineKey,authType, map);
            
            //Total Demo Success txn count
            lineKey = getPrimaryKey(auaCode,asaCode,requestDate,"DemoSuccessTxnCount");
            processThread.countDemoSuccessTxn(lineKey,authType,authResult, map);
           
            //log.info("FileName : "+csvFile+", Lines : "+linecount);
    
	}
	public static String getPrimaryKey(String auaCode, String asaCode, String requestDate, String aggType){
		String pkey = "";
		if(auaCode != null && asaCode != null && requestDate != null && aggType != null && !"".equalsIgnoreCase(auaCode)
				&& !"".equalsIgnoreCase(asaCode) && !"".equalsIgnoreCase(requestDate) && !"".equalsIgnoreCase(aggType)){
			pkey = auaCode+"|"+asaCode+"|"+requestDate+"|"+aggType;
		}
		return pkey;
	}
}

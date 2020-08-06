package com.csvparse.boImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.csvparse.MAIN;
import com.csvparse.bo.CsvBo;
import com.csvparse.bo.HibernateBo;
import com.csvparse.util.ProcessorTask;
import com.csvparse.util.ReaderTask;

public class CsvBoImpl implements CsvBo{
	private static final Logger log = Logger.getLogger(MAIN.class);
	ConcurrentHashMap<String,String> map;
	ConcurrentHashMap<String,String> localMap;
	
	public void readCsvAndProceed(List<File> folders, ConcurrentHashMap<String,String>  map,Long count) {
		this.map=map;
		 BufferedReader br;
		 String line;
		 List<String> lines=null;
		 int n = 100;
		 int first=1;
		 //int i=0;
			try {
				
				//br = new BufferedReader(new FileReader(csvPath));
				long l1=System.currentTimeMillis();
				// lines = readNLines(br, n,first);
				Integer threadcount=10;
				final int capacity = threadcount * 20;// 20 times the * threadcount
				final BlockingQueue<String> processingQueue = new ArrayBlockingQueue<String>(
						capacity);
				final ExecutorService readerPool = Executors.newSingleThreadExecutor();
				
				ThreadPoolExecutor processorPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
				//final ExecutorService processorPool = Executors.newFixedThreadPool(threadcount);
				//File[] listOfFiles = folder.listFiles();
				
				for(File folder: folders)
				{
				for(File file: folder.listFiles()){
					
					try{
					if(  file.getPath().contains("_Transaction")  ){
					Future<?> readerTask = readerPool.submit(new ReaderTask(file, processingQueue,count,processorPool));
					List<Future> processPollFutures = new ArrayList<>();
					for (int i = 0; i < threadcount; i++) {
						Future<?> ppf = processorPool.submit(new ProcessorTask(processingQueue, readerTask,map));
						processPollFutures.add(ppf);
					}
					for (Future future : processPollFutures) {
						future.get();
					}
					
				System.out.println("--------------");	
				localMap=map;
			    HibernateBo hibernateBo = new HibernateBo();
			    hibernateBo.insertValuesinDB(map);
			    map= new ConcurrentHashMap<String,String>();
			    
			    
				}
					}catch(NullPointerException npe){
						System.out.println("invalid file");	
					}
			    
				}
			}	
				readerPool.shutdown();
				boolean terminated = false;
				while(!terminated){
					try {
						terminated = readerPool.awaitTermination(10L, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						log.error("interrupt in closing reader pool. ", e);
					}
				}
				processorPool.shutdown(); // stop the processor
				terminated = false;
				while(!terminated){
					try {
						terminated = processorPool.awaitTermination(5L, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						log.error("interrupt in closing proessor pool. ", e);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}

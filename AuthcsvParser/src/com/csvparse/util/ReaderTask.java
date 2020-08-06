package com.csvparse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;


public class ReaderTask implements Runnable{
	private static final Logger log = Logger.getLogger(ReaderTask.class);
	private BufferedReader reader;
	private final BlockingQueue<String> processingQueue;
	File file ;
	Long count=null;
	ThreadPoolExecutor processorPool;
	public ReaderTask(File file , BlockingQueue<String> processingQueue,Long count,ThreadPoolExecutor processorPool) {
		this.file = file;
		this.processingQueue = processingQueue;
		this.count=count;
		this.processorPool=processorPool;
	}
	@Override
	public void run() {
		String input = null;
		try {
					reader = new BufferedReader(new FileReader(file.getPath()));
					reader.readLine();
					while ((input = reader.readLine()) != null) {
						try {
							count ++;
							processingQueue.put(input);
							//System.out.println(input);
						} catch (InterruptedException e) {
							log.error("Unable to fetch processing queue.", e);
						}
					}
		  System.out.println("Total count::"+count);	
		  log.info("Total count::"+count);
		}catch(Exception e){
			
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Finishing reading.");
		}

	}
}

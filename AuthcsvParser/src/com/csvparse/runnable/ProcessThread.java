package com.csvparse.runnable;


import java.util.concurrent.ConcurrentHashMap;


public class ProcessThread  {
	void ProcessThread()
	{
		
	}
	
	public ConcurrentHashMap<String, String> countTransactions(String pkey,
			ConcurrentHashMap<String, String> aggregationMap) {
		/*HashObject newHashObj = new HashObject(hashObj.getAuaCode(), hashObj.getAsaCode(), hashObj.getRequestTime(),
				"TotalTransactions");*/
		
		synchronized (aggregationMap) {
	
		if(aggregationMap != null && !aggregationMap.isEmpty()){
			if(aggregationMap.containsKey(pkey)){
				String value = aggregationMap.get(pkey);
				value = String.valueOf(Integer.parseInt(value) + 1);
				aggregationMap.put(pkey, value);
			}else{
				aggregationMap.put(pkey, "1");
			}
		}else{
			aggregationMap.put(pkey, "1");
		}
		
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countSuccess(String pkey, String authResult, 
			ConcurrentHashMap<String, String> aggregationMap) {
		
		
		synchronized (aggregationMap) {
			
		
		 if(authResult != null && !"".equalsIgnoreCase(authResult)){
			 if(authResult.equalsIgnoreCase("y")){
				/*HashObject newHashObj = new HashObject(hashObj.getAuaCode(), hashObj.getAsaCode(), hashObj.getRequestTime(),
							"TotalSuccessTransaction");*/
				 if(aggregationMap != null && !aggregationMap.isEmpty()){
					 	if(aggregationMap.containsKey(pkey)){
					 			String value = aggregationMap.get(pkey);
					 			value = String.valueOf(Integer.parseInt(value) + 1);
					 			aggregationMap.put(pkey, value);
					 	}else{
					 		aggregationMap.put(pkey, "1");
					 	}
					}else{
						aggregationMap.put(pkey, "1");
					}
            	}
		 }
		}
		 return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countAuthDuration(String pkey,
			String authDuration, ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {	
		if(authDuration != null && !"".equalsIgnoreCase(authDuration)){
			//hashObj.setAggType("TotalAuthDuration");
			if(aggregationMap != null && !aggregationMap.isEmpty()){
				if(aggregationMap.containsKey(pkey)){
					String value = aggregationMap.get(pkey);
					String sumValue = String.valueOf(Integer.parseInt(value)+Integer.parseInt(authDuration));
					aggregationMap.put(pkey, sumValue);
				}else{
					aggregationMap.put(pkey, authDuration);
				}
			}else{
				aggregationMap.put(pkey, authDuration);
			}
		}
		}
		
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countAuthDurationGt1(String pkey,
			String authDuration,
			ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {	
		if(authDuration != null && !"".equalsIgnoreCase(authDuration)){
			Integer authDurationValue = Integer.parseInt(authDuration);
			if(authDurationValue > 1000){
				//hashObj.setAggType("TotalAuthCountGt1");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}	
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countBioTxn(String pkey,
			String authType, ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {	
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("F") || authType.contains("I")){
				//hashObj.setAggType("BioTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countBioSuccessTxn(String pkey,
			String authType, String authResult, ConcurrentHashMap<String, String> aggregationMap) {
		
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && (authType.contains("F") || authType.contains("I"))){
				//hashObj.setAggType("BioSuccessTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countFPTxn(String pkey, String authType,
			ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("F")){
				//hashObj.setAggType("FPTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countFPSuccessTxn(String pkey,
			String authType, String authResult,
			ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("F")){
				//hashObj.setAggType("FPSuccessTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countIrisTxn(String pkey,
			String authType, ConcurrentHashMap<String, String> aggregationMap) {
		
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("I")){
				//hashObj.setAggType("IrisTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countIrisSuccessTxn(String pkey,
			String authType, String authResult,
			ConcurrentHashMap<String, String> aggregationMap) {
		
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("I")){
			//	hashObj.setAggType("IrisSuccessTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countOTPTxn(String pkey,
			String authType, ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("I")){
				//hashObj.setAggType("OTPTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countOTPSuccessTxn(String pkey,
			String authType, String authResult,
			ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("O")){
				//hashObj.setAggType("OTPSuccessTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countDemoTxn(String pkey,
			String authType, ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("D")){
				//hashObj.setAggType("DemoTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}

	public ConcurrentHashMap<String, String> countDemoSuccessTxn(String pkey,
			String authType, String authResult,
			ConcurrentHashMap<String, String> aggregationMap) {
		synchronized (aggregationMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("D")){
				//hashObj.setAggType("DemoSuccessTxnCount");
				if(aggregationMap != null && !aggregationMap.isEmpty()){
					if(aggregationMap.containsKey(pkey)){
						String value = aggregationMap.get(pkey);
						value = String.valueOf((Integer.parseInt(value) + 1));
						aggregationMap.put(pkey, value);
					}else{
						aggregationMap.put(pkey, "1");
					}
				}else{
					aggregationMap.put(pkey, "1");
				}
			}
		}
		}
		return aggregationMap;
	}
	
	
	
	/*public ConcurrentHashMap<String,Integer> countSuccess(String lineKey, String authResult, ConcurrentHashMap<String,Integer> countSuccessMap) {
		 if(authResult != null && !"".equalsIgnoreCase(authResult)){
			 if(authResult.equalsIgnoreCase("y")){
//				 if(countSuccessMap != null && !countSuccessMap.isEmpty()){
					 	
				 
				 
				 synchronized (countSuccessMap) {
				 		if(countSuccessMap.containsKey(lineKey)){
					 			Integer value = countSuccessMap.get(lineKey);
					 			countSuccessMap.put(lineKey, value+1);
					 	}else{
					 		countSuccessMap.put(lineKey, 1);
					 	}
				 }
					 	
					 	
					 	
					 	
//					}else{
//						countSuccessMap.put(lineKey, 1);
//					}
           	}
		 }
		 return countSuccessMap;
	}

	public ConcurrentHashMap<String, Integer> countTransactions(String lineKey,
			ConcurrentHashMap<String, Integer> countTransactionsMap) {
		if(countTransactionsMap != null && !countTransactionsMap.isEmpty()){
		
		 synchronized (countTransactionsMap) {
			if(countTransactionsMap.containsKey(lineKey)){
				Integer value = countTransactionsMap.get(lineKey);
				countTransactionsMap.put(lineKey, value+1);
			}else{
				countTransactionsMap.put(lineKey, 1);
			}
		 }
			
		}else{
			countTransactionsMap.put(lineKey, 1);
		}
		return countTransactionsMap;
	}

	public ConcurrentHashMap<String, Integer> countAuthDuration(String lineKey,
			String authDuration, ConcurrentHashMap<String, Integer> countAuthDurationMap) {
		
		if(authDuration != null && !"".equalsIgnoreCase(authDuration)){
			
			
			if(countAuthDurationMap != null && !countAuthDurationMap.isEmpty()){
			
			 synchronized (countAuthDurationMap) {
			if(countAuthDurationMap.containsKey(lineKey)){
					Integer value = countAuthDurationMap.get(lineKey);
					Integer sumValue = value+Integer.parseInt(authDuration);
					countAuthDurationMap.put(lineKey, sumValue);
				}else{
					Integer value = Integer.parseInt(authDuration);
					countAuthDurationMap.put(lineKey, value);
				}
			}else{
				Integer value = Integer.parseInt(authDuration);
				countAuthDurationMap.put(lineKey, value);
			}
			 
		}
		
		
		return countAuthDurationMap;
	}

	public ConcurrentHashMap<String, Integer> countAuthDurationGt1(String lineKey,
			String authDuration,
			ConcurrentHashMap<String, Integer> countAuthDurationGt1Map) {
		
		if(authDuration != null && !"".equalsIgnoreCase(authDuration)){
			
			Integer authDurationValue = Integer.parseInt(authDuration);
			if(authDurationValue > 1000){
				if(countAuthDurationGt1Map != null && !countAuthDurationGt1Map.isEmpty()){
				 synchronized (countAuthDurationGt1Map) {
					if(countAuthDurationGt1Map.containsKey(lineKey)){
						Integer value = countAuthDurationGt1Map.get(lineKey);
						countAuthDurationGt1Map.put(lineKey, value+1);
					}else{
						countAuthDurationGt1Map.put(lineKey, 1);
					}
				}else{
					countAuthDurationGt1Map.put(lineKey, 1);
				}
			}
		}
		
		return countAuthDurationGt1Map;
	}

	public ConcurrentHashMap<String, Integer> countBioTxn(String lineKey,
			String authType, ConcurrentHashMap<String, Integer> countBioTxnMap) {
		
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("F") || authType.contains("I")){
				if(countBioTxnMap != null && !countBioTxnMap.isEmpty()){
				 synchronized (countBioTxnMap) {
					if(countBioTxnMap.containsKey(lineKey)){
						Integer value = countBioTxnMap.get(lineKey);
						countBioTxnMap.put(lineKey, value+1);
					}else{
						countBioTxnMap.put(lineKey, 1);
					}
				}else{
					countBioTxnMap.put(lineKey, 1);
				}
			}
		}
		return countBioTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countBioSuccessTxn(String lineKey,
			String authType,String authResult, ConcurrentHashMap<String, Integer> countBioSuccessTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && (authType.contains("F") || authType.contains("I"))){
				if(countBioSuccessTxnMap != null && !countBioSuccessTxnMap.isEmpty()){
				synchronized (countBioSuccessTxnMap) {
					if(countBioSuccessTxnMap.containsKey(lineKey)){
						Integer value = countBioSuccessTxnMap.get(lineKey);
						countBioSuccessTxnMap.put(lineKey, value+1);
					}else{
						countBioSuccessTxnMap.put(lineKey, 1);
					}
				}else{
					countBioSuccessTxnMap.put(lineKey, 1);
				}
			}
		}
		return countBioSuccessTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countFPTxn(String lineKey, String authType,
			ConcurrentHashMap<String, Integer> countFPTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("F")){
				if(countFPTxnMap != null && !countFPTxnMap.isEmpty()){
				synchronized (countFPTxnMap) {
					if(countFPTxnMap.containsKey(lineKey)){
						Integer value = countFPTxnMap.get(lineKey);
						countFPTxnMap.put(lineKey, value+1);
					}else{
						countFPTxnMap.put(lineKey, 1);
					}
				}else{
					countFPTxnMap.put(lineKey, 1);
				}
			}
		}
		return countFPTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countFPSuccessTxn(String lineKey,
			String authType, String authResult,
			ConcurrentHashMap<String, Integer> countFPSuccessTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("F")){
				if(countFPSuccessTxnMap != null && !countFPSuccessTxnMap.isEmpty()){
				synchronized (countFPSuccessTxnMap) {
					if(countFPSuccessTxnMap.containsKey(lineKey)){
						Integer value = countFPSuccessTxnMap.get(lineKey);
						countFPSuccessTxnMap.put(lineKey, value+1);
					}else{
						countFPSuccessTxnMap.put(lineKey, 1);
					}
				}else{
					countFPSuccessTxnMap.put(lineKey, 1);
				}
			}
		}
		return countFPSuccessTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countIrisTxn(String lineKey,
			String authType, ConcurrentHashMap<String, Integer> countIrisTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("I")){
				if(countIrisTxnMap != null && !countIrisTxnMap.isEmpty()){
				synchronized (countIrisTxnMap) {
					if(countIrisTxnMap.containsKey(lineKey)){
						Integer value = countIrisTxnMap.get(lineKey);
						countIrisTxnMap.put(lineKey, value+1);
					}else{
						countIrisTxnMap.put(lineKey, 1);
					}
				}else{
					countIrisTxnMap.put(lineKey, 1);
				}
			}
		}
		return countIrisTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countIrisSuccessTxn(String lineKey,
			String authType, String authResult,
			ConcurrentHashMap<String, Integer> countIrisSuccessTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("I")){
				if(countIrisSuccessTxnMap != null && !countIrisSuccessTxnMap.isEmpty()){
				synchronized (countIrisSuccessTxnMap) {
					if(countIrisSuccessTxnMap.containsKey(lineKey)){
						Integer value = countIrisSuccessTxnMap.get(lineKey);
						countIrisSuccessTxnMap.put(lineKey, value+1);
					}else{
						countIrisSuccessTxnMap.put(lineKey, 1);
					}
				}else{
					countIrisSuccessTxnMap.put(lineKey, 1);
				}
			}
		}
		return countIrisSuccessTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countOTPTxn(String lineKey,
			String authType, ConcurrentHashMap<String, Integer> countOTPTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("I")){
				if(countOTPTxnMap != null && !countOTPTxnMap.isEmpty()){
				synchronized (countOTPTxnMap) {
					if(countOTPTxnMap.containsKey(lineKey)){
						Integer value = countOTPTxnMap.get(lineKey);
						countOTPTxnMap.put(lineKey, value+1);
					}else{
						countOTPTxnMap.put(lineKey, 1);
					}
				}else{
					countOTPTxnMap.put(lineKey, 1);
				}
			}
		}
		return countOTPTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countOTPSuccessTxn(String lineKey,
			String authType, String authResult,
			ConcurrentHashMap<String, Integer> countOTPSuccessTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("O")){
				if(countOTPSuccessTxnMap != null && !countOTPSuccessTxnMap.isEmpty()){
				synchronized (countOTPSuccessTxnMap) {
					if(countOTPSuccessTxnMap.containsKey(lineKey)){
						Integer value = countOTPSuccessTxnMap.get(lineKey);
						countOTPSuccessTxnMap.put(lineKey, value+1);
					}else{
						countOTPSuccessTxnMap.put(lineKey, 1);
					}
				}else{
					countOTPSuccessTxnMap.put(lineKey, 1);
				}
			}
		}
		return countOTPSuccessTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countDemoTxn(String lineKey,
			String authType, ConcurrentHashMap<String, Integer> countDemoTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType)){
			if(authType.contains("D")){
				if(countDemoTxnMap != null && !countDemoTxnMap.isEmpty()){
				synchronized (countDemoTxnMap) {
					if(countDemoTxnMap.containsKey(lineKey)){
						Integer value = countDemoTxnMap.get(lineKey);
						countDemoTxnMap.put(lineKey, value+1);
					}else{
						countDemoTxnMap.put(lineKey, 1);
					}
				}else{
					countDemoTxnMap.put(lineKey, 1);
				}
			}
		}
		return countDemoTxnMap;
	}

	public ConcurrentHashMap<String, Integer> countDemoSuccessTxn(String lineKey,
			String authType, String authResult,
			ConcurrentHashMap<String, Integer> countDemoSuccessTxnMap) {
		if(authType != null && !"".equalsIgnoreCase(authType) && authResult != null && !"".equalsIgnoreCase(authResult)){
			if(authResult.equalsIgnoreCase("Y") && authType.contains("D")){
				if(countDemoSuccessTxnMap != null && !countDemoSuccessTxnMap.isEmpty()){
				synchronized (countDemoSuccessTxnMap) {
					if(countDemoSuccessTxnMap.containsKey(lineKey)){
						Integer value = countDemoSuccessTxnMap.get(lineKey);
						countDemoSuccessTxnMap.put(lineKey, value+1);
					}else{
						countDemoSuccessTxnMap.put(lineKey, 1);
					}
				}else{
					countDemoSuccessTxnMap.put(lineKey, 1);
				}
			}
		}
		return countDemoSuccessTxnMap;
	}*/
}

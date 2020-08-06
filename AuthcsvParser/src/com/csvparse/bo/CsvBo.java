package com.csvparse.bo;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface CsvBo {

	public void readCsvAndProceed(List<File> folder, ConcurrentHashMap<String,String>  map,Long count);
}

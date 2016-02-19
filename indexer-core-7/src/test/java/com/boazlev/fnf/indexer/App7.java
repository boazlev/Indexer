package com.boazlev.fnf.indexer;

import java.util.Map;

/**
 *
 */
public class App7 {
	public static void main(String[] args) throws Exception {
		String pdfFileName = "c:/temp/pdf/metron_1220.pdf";
		String idsFileName = "./lines.txt";
		Indexer indexer = new Indexer();
		Map<String, Integer> map = indexer.index(pdfFileName, idsFileName);
		for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
			String key = mapEntry.getKey();
//			Integer value = mapEntry.getValue();
			System.out.println(key);
		}
	}
}
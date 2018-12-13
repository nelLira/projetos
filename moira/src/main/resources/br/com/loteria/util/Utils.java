package br.com.loteria.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

public class Utils {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public static void gerarCSV(List<String> resultCSV, String nomArq) {
		try { 
			String arquivo = "c:\\temp\\" + nomArq +".csv";
			File file = new File(arquivo); 

	        FileWriter outputfile = new FileWriter(file); 

	        CSVWriter writer = new CSVWriter(outputfile, ';'); 

	        List<String[]> data = new ArrayList<String[]>(); 
	        
		for (String string : resultCSV) {
			 String row = string; //sc.nextLine(); 
             String[] rowdata = row.split("#"); 
             data.add(rowdata); 
		}
		  writer.writeAll(data); 
 
		  writer.close();
		  java.awt.Desktop.getDesktop().open( new File(arquivo) );

	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    }
	}
}

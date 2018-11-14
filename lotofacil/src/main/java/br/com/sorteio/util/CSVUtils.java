package br.com.sorteio.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVUtils {
    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, List values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
    
    private static void gerarCSV(List<String> resultCSV, String nomArq) {
		try { 
			String arquivo = "c:\\temp\\" + nomArq +".csv";
			File file = new File(arquivo); 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 

	        // create CSVWriter with ';' as separator 
	        CSVWriter writer = new CSVWriter(outputfile, ';'); 

	        // create a List which contains Data 
	        List<String[]> data = new ArrayList<String[]>(); 
	        
		for (String string : resultCSV) {
			 String row = string; //sc.nextLine(); 
             String[] rowdata = row.split("#"); 
             data.add(rowdata); 
		}
		  writer.writeAll(data); 

	        // closing writer connection 
		  writer.close();
		  java.awt.Desktop.getDesktop().open( new File(arquivo) );

	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    }
	}
}
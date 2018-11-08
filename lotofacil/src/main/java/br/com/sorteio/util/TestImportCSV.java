package br.com.sorteio.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

import br.com.sorteio.numeros.Jogo;

public class TestImportCSV {

	private static final String CSV_FILE_PATH 
    = "./result.csv"; 
public static void main(String[] args) 
{ 
    addDataToCSV(CSV_FILE_PATH); 
} 
public static void addDataToCSV(String output) 
{ 
    // first create file object for file placed at location 
    // specified by filepath 
    File file = new File("c:\\temp\\export.csv"); 
    Scanner sc = new Scanner(System.in); 
    try { 
        // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter(file); 

        // create CSVWriter with ';' as separator 
        CSVWriter writer = new CSVWriter(outputfile, ';', 
                CSVWriter.NO_QUOTE_CHARACTER, 
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                CSVWriter.DEFAULT_LINE_END); 


        // create a List which contains Data 
        List<String[]> data = new ArrayList<String[]>(); 

        List<String> jogos = new ArrayList<String>();
        jogos.add("welington#lira");
        

        for (String jogo : jogos) {
        	 String row = jogo; //sc.nextLine(); 
             String[] rowdata = row.split("#"); 
             data.add(rowdata);
		}
        	 
		

        writer.writeAll(data); 

        // closing writer connection 
        writer.close(); 
    } 
    catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
    } 
} 

}

package keyords.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Service {

	static Scanner sc = new Scanner(System.in);

	private static String readFile(String loc) throws IOException {

		loc = loc.replace("/", "\\");
		boolean b = loc.contains(".java");
		String code = "";
		if (b == true) {

			FileReader fr = new FileReader(loc);
			int read = fr.read();
			while (read != -1) {
				code = code + (char) read;
				read = fr.read();
			}
			//System.out.println("original code = {" + code + "}");

		}
		return code;
	}

	public static List<String> getKeys() throws IOException {

		String keywords = readFile("K:\\propfiles\\keywords.java");
		//System.out.println("keywords string = " + keywords);
		String[] split = keywords.split(" ");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < split.length; i++) {
			//System.out.println("keywords = " + split[i]);
			list.add(split[i]);
		}

		return list;

	}

	private static String pickRandom() throws IOException {

		List<String> keys = getKeys();
		List<String> list = new ArrayList<String>(keys);
		Collections.shuffle(list);
		String keyword = list.get(0);
		return keyword;
	}

	public static void replaceCode(int numberOfKeywords, String loc,int noOfVersions) throws IOException {

		String code = readFile(loc);
		String newCode = code;
		Set<String> noOfKeys = new HashSet<String>();
		while(noOfKeys.size()<=numberOfKeywords-1) {
			String pickRandomkeyword = pickRandom();
			System.out.println("random keyword = " + pickRandomkeyword);
			noOfKeys.add(pickRandomkeyword);
		}
        List<String> list = new ArrayList<String>(noOfKeys);

		       for (int i = 0; i < list.size(); i++) {
		    	   //System.out.println("to be replaced = "+list.get(i));
		    	   newCode = newCode.replaceAll(list.get(i), " ? ");
		    	   //System.out.println("new Code = {" + newCode + "}");
			} 
		       System.out.println("new Code = {" + newCode + "}");
		       int no = 0;
		while(noOfVersions!=0) {
			createNewFile(no, newCode);
			noOfVersions--;no++;
		}
				
		      
	}
	private static void createNewFile(int no,String newCode) throws IOException {
		
		FileWriter fw = new FileWriter("K:\\propfiles\\newCode"+no);
		fw.write(newCode);
		fw.flush();
		
	}

}

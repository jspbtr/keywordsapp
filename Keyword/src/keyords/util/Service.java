package keyords.util;

import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
		   // System.out.println("original code = {" + code + "}");
			fr.close();
		}
		return code;
	}

	public static List<String> getKeys() throws IOException {

		String keywords = readFile("K:\\propfiles\\keywordsfile.java");
		//System.out.println("keywords string = " + keywords);
		String[] split = keywords.split(" ");
		List<String> listOfKeyWordsFromOracle = new ArrayList<String>();
		for (int i = 0; i < split.length; i++) {
			//System.out.println("keywords = " + split[i]);
			listOfKeyWordsFromOracle.add(split[i]);
		}
		List<String> codeToWords = codeToWords();
		ArrayList<String> listOfKeywords = new ArrayList<String>();
		for (int i = 0; i < listOfKeyWordsFromOracle.size(); i++) {
			for (int j = 0; j < codeToWords.size(); j++) {
				if (codeToWords.get(j).equals(listOfKeyWordsFromOracle.get(i))) {
					listOfKeywords.add(codeToWords.get(j));
				}

			}

		}
        System.out.println("list Of unique code Keywords : "+listOfKeywords);
		return listOfKeywords;

	}

	private static String pickRandom() throws IOException {

		List<String> keys = getKeys();
		List<String> list = new ArrayList<String>(keys);
		Collections.shuffle(list);
		String keyword = list.get(0);
		return keyword;
	}

	private static String replaceCode(int numberOfKeywords, String loc) throws IOException {

		String code = readFile(loc);
		System.out.println("original code = {" + code + "}");
		String newCode = code;
		Set<String> noOfKeys = new HashSet<String>();
		while (noOfKeys.size() <= numberOfKeywords - 1) {
			String pickRandomkeyword = pickRandom();
			System.out.println("random keyword = " + pickRandomkeyword);
			noOfKeys.add(pickRandomkeyword);
		}
		List<String> list = new ArrayList<String>(noOfKeys);

		for (int i = 0; i < list.size(); i++) {
			// System.out.println("to be replaced = "+list.get(i));
			newCode = newCode.replaceAll(list.get(i), "?");
			 System.out.println("new Code = {" +newCode + "}"); 
		}
		return newCode;

	}

	public static void createNewFile(int noOfVersions,int numberOfKeywords, String loc) throws IOException {
        
		int i = 1;
	    while(i<=noOfVersions) {
	    String newCode = replaceCode(numberOfKeywords, loc);
		FileWriter fw = new FileWriter("K:\\propfiles\\newCode" + i + ".txt");
		fw.write(newCode);
		fw.flush();
		fw.close();
		i++;
	    }
	}

	private static List<String> codeToWords() throws IOException {

		String orgcode = readFile("K:\\propfiles\\code.java");
		String s = orgcode + " ";
		String s2 = "";
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {

				s2 += c;
			} else {
				set.add(s2);
				s2 = "";
			}
			set.remove("");
		}
		//System.out.println("code to words : "+set);

		List<String> listCodeWords = new ArrayList<String>(set);
		return listCodeWords;

	}

}

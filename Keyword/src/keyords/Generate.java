package keyords;

import java.io.IOException;

import keyords.util.Service;

public class Generate {

	public static void main(String[] args) {
	
         
		
		try {
			Service.replaceCode(2, "K:\\propfiles\\code.java",3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

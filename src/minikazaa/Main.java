package minikazaa;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.xml.namespace.NamespaceContext;
import javax.xml.XMLConstants;

/**
 * 
 * @author giovine
 */
public class Main {
        private static String CFG_FILE = "minikazaacfg.xml";
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Loading Mini-kazaa v 0.01");
		System.out.println("Loading config...");
		FileReader fr = null;
		Configuration main_cfg = null;
		//Open configuration file.
		try {
			File cfg = new File(CFG_FILE);
			boolean success = cfg.createNewFile();
			if(success){
				//File didn't exist and was created
				main_cfg = configProgram();
			}
			else{
				fr = new FileReader(CFG_FILE);
				main_cfg = loadCfg(fr);
			}
		} catch (IOException io_exc) {
			System.err.println(io_exc + "\nException was caught while opening" +
					"mkazaa.cfg");
		}

	}
	
	public static Configuration loadCfg(FileReader fr){
            return new Configuration();
	}
	
	public static Configuration configProgram(){
            //Start interface to configure program
            return new Configuration();
	}
}



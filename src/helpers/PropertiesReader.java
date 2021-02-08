package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	Properties p = new Properties();
	InputStream fis;
	String propertyfile = "src/helpers/config.properties"; 
	
	public PropertiesReader(){
		try {
			fis = new FileInputStream(propertyfile);
			p.load(fis);
			
			if(fis != null) {
				p.load(fis);
			}else {
				throw new FileNotFoundException("property file '"+propertyfile+"' ");
			}			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getSQL(String result) {
		return  p.getProperty(result);
	}
	
}
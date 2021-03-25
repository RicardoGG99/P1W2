package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import helpers.PropertiesReader;

public class ArchivoManager {
	
	private static UserManager um = new UserManager();
	private static PropertiesReader pr = new PropertiesReader();
	
	
	//Crear directorios
	public boolean createDirs(String cedula, Part part) {
		boolean result = false;
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		InputStream fileIn;
		String path = pr.getSQL("path");
		OutputStream fileOut;
		
		try {
			//Path donde se crearan las carpetas
			File newFolder = new File(path + cedula);
			
			if(newFolder.mkdirs()) {
				System.out.println("Carpetas del usuario con la cedula " + cedula + " creadas correctamente");
				result = true;
			}else {
				System.out.println("Las carpetas ya han sido creadas");
			}
			
			fileOut = new FileOutputStream(new File(newFolder.getAbsolutePath() + "/" + fileName));
			fileIn = part.getInputStream();
			
			
			 int read = 0;
		        final byte[] bytes = new byte[1024];
		        try{
		            while ((read = fileIn.read(bytes)) != -1) {
		                fileOut.write(bytes, 0, read);
		            }
		            result = true;
		        }catch(Exception e){
		        	result = false;
		        }
			
		        result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
    
    //Eliminar Foto de Perfil
    
    public String deleteFdp(Part part, String cedula, HttpServletRequest request, HttpServletResponse response) {
    	
    	String message = "";
    	String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
    	
    	File folder = new File(pr.getSQL("path") + cedula);
		File file = new File(folder.getAbsolutePath() + "/" + fileName);
		
    	
    	if(file.delete()) {
    		message = "{\"message\": \"Foto de perfil eliminada exitosamente\", "
  				 	 + "\"status\": 200 }";
    	}else {
    		message = "{\"message\": \"No se pudo eliminar la foto de perfil\", "
				 	 + "\"status\": 503 }";
    	}
    	
    	if(folder.delete()) {
    		message = "{\"message\": \"Foto de perfil eliminada exitosamente\", "
  				 	 + "\"status\": 200 }";
    		
    		boolean r = um.closeSession(request, response);
			
			if(r == false) {
				message = "{\"message\": \"No se pudo eliminar la foto de perfil\", "
	   				 	 + "\"status\": 503 }";
			}
			
    	}else {
    		message = "{\"message\": \"No se pudo eliminar la foto de perfil\", "
				 	 + "\"status\": 503 }";
    	}
    	
    	
    	return message;
    }
    

    
    
    
}

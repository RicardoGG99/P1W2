package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import helpers.PropertiesReader;

public class ArchivoManager {
	
	private static UserManager um = new UserManager();
	private static PropertiesReader pr = new PropertiesReader();
	
	
	//Crear Foto de perfil
    
    public boolean createFdp(String cedula, Part part) {
    	boolean result = false;
    	
    	try {
			
    		InputStream fileIn;
    		OutputStream fileOut;
    		
    		//Path donde se crearan las carpetas
			File newFolder = new File(pr.getSQL("path") + cedula);
			
    		if(newFolder.mkdirs()) {
    			System.out.println("Carpetas del usuario con la cedula " + cedula + " creadas correctamente");
    		}else {
    			System.out.println("Las carpetas ya han sido creadas");
    		}
    		
    		//Se crea el archivo vacio dentro de la carpeta del usuario
    		fileOut = new FileOutputStream(new File(newFolder.getAbsolutePath() + "/" + part.getName()));
    		System.out.println("Archivo Creado");
    		
    		fileIn = part.getInputStream();
    		
    		 	//Se llena el archivo vacio
    			int a = 0;
    	        final byte[] bytes = new byte[1024];
    	        try{
    	            while ((a = fileIn.read(bytes)) != -1) {
    	                fileOut.write(bytes, 0, a);
    	            }
    	            System.out.println("Archivo actualizado");
    	            fileOut.close();
    	            fileIn.close();
    	            
    	            result = true;
    	        }catch(Exception e){
    	            System.out.println(e);
    	            result = false;
    	        }
    		  		
		} catch (Exception e) {
			result = false;
			System.out.println("Error en la creacion del archivo");
		}
    	
    	return result;
    	
    	
    }
    
    //Eliminar Foto de Perfil
    
    public String deleteFdp(String cedula, HttpServletRequest request, HttpServletResponse response) {
    	
    	String message = "";
    	
    	File folder = new File(pr.getSQL("path") + cedula);
		File file = new File(folder.getAbsolutePath() + "/fdp.png");
		
    	
    	if(file.delete()) {
    		message = "{\"message\": \"Delete Exitoso\", "
  				 	 + "\"status\": 200 }";
    	}else {
    		message = "{\"message\": \"Delete Fallido\", "
				 	 + "\"status\": 503 }";
    	}
    	
    	if(folder.delete()) {
    		message = "{\"message\": \"Delete Exitoso\", "
  				 	 + "\"status\": 200 }";
    		
    		boolean r = um.closeSession(request, response);
			
			if(r == false) {
				message = "{\"message\": \"Delete Fallido\", "
	   				 	 + "\"status\": 200 }";
			}
			
    	}else {
    		message = "{\"message\": \"Delete Fallido\", "
				 	 + "\"status\": 503 }";
    	}
    	
    	
    	return message;
    }
    
}

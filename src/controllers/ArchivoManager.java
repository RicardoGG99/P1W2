package controllers;

import java.io.File;



import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		Path filePath;
//		OutputStream fileOut;
		
		try {
			//Path donde se crearan las carpetas
			File newFolder = new File(path + cedula);
			
			if(newFolder.mkdirs()) {
				System.out.println("Carpetas del usuario con la cedula " + cedula + " creadas correctamente");
				result = true;
			}else {
				System.out.println("Las carpetas ya han sido creadas");
			}
			
//			fileOut = new FileOutputStream(new File(newFolder.getAbsolutePath() + "/" + fileName));
			filePath = Paths.get(newFolder.getAbsolutePath() + "/" + fileName);
			fileIn = part.getInputStream();
			
			Files.copy(fileIn, filePath, StandardCopyOption.REPLACE_EXISTING);
			
			
//			 int read = 0;
//		        final byte[] bytes = new byte[1024];
//		        try{
//		            while ((read = fileIn.read(bytes)) != -1) {
//		                fileOut.write(bytes, 0, read);
//		            }
//		            result = true;
//		        }catch(Exception e){
//		        	result = false;
//		        }
			
		        result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean createFdp(HttpServletRequest request, String cedula){
		boolean result = false;
		
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		try {
			String path = pr.getSQL("path");
			List<FileItem> file = sfu.parseRequest(request);
			
			for(FileItem item: file) {
				item.write(new File(path + item.getName()));
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

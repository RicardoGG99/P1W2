package controllers;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helpers.Conexion;
import helpers.PasswordHashing;


public class UserManager {
    static Conexion conn = new Conexion();
    Connection connection = conn.getConnection();
    static PasswordHashing ph = new PasswordHashing();
    
    //Register
    public String register(String cedula, String nombre, String apellido, String fdn, String password, String email) {
    	
		String newPassword = ph.hashPassword(password);
		String[] obj = {cedula, nombre, apellido, fdn, newPassword, email};
		String message = "";
		
		try {
			boolean result = conn.psRegistro(connection, obj);
			
			if(result == true) {
				message = "{\"message\": \"Registro Exitoso\", \"status\": 200 }";
			}else {
				message = "{\"message\": \"El Registro fue fallido\", \"status\": 503 }";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
		
		
	}
    
    //Login
    public String login(String cedula, String password, HttpServletResponse response) {
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
		String message = "";
    	
		try {
			boolean result = conn.psLogin(obj);
			
			if(result == true) {
				
				String[] obj2 = Conexion.psDatos(cedula, ph.hashPassword(password));
		    	
		    		Cookie cookie = new Cookie("cedula", obj2[0]);
		    		response.addCookie(cookie);
		    		
		    		Cookie cookie1 = new Cookie("nombre", obj2[1]);
		    		response.addCookie(cookie1);
		    		
		    		Cookie cookie2 = new Cookie("apellido", obj2[2]);
		    		response.addCookie(cookie2);
		    		
		    		Cookie cookie3 = new Cookie("fdn", obj2[3]);
		    		response.addCookie(cookie3);
		    		
		    		Cookie cookie4 = new Cookie("password", obj2[4]);
		    		response.addCookie(cookie4);
		    		
		    		Cookie cookie5 = new Cookie("email", obj2[5]);
		    		response.addCookie(cookie5);
		    		
				
				message = "{\"message\": \"Login Exitoso\", "
					 	 + "\"status\": 200 }";
			}else {
				message = "{\"message\": \"El Login fue fallido\", "
						 + "\"status\": 503 }";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	
    	return message;
    }
    
    //Datos de la Sesion
    
    
    public static String showCredentials(HttpServletRequest request) {
    	
    	Cookie cookies[] = request.getCookies();
    	String cedula = "";
    	String nombre = "";
    	String apellido = "";
    	String fdn = "";
    	String password = "";
    	String email = "";
    	
    	for(Cookie c : cookies) {
    		if(c.getName().equals("cedula")) {
    			cedula = c.getValue();
    		}
    		
    		if(c.getName().equals("nombre")) {
    			nombre = c.getValue();
    		}
    		
    		if(c.getName().equals("apellido")) {
    			apellido = c.getValue();
    		}
    		
    		if(c.getName().equals("fdn")) {
    			fdn = c.getValue();
    		}
    		
    		if(c.getName().equals("password")) {
    			password = c.getValue();
    		}
    		
    		if(c.getName().equals("email")) {
    			email = c.getValue();
    		}
    	}
    	
    	
    	
    	String message = "{\"cedula\":   \"" + cedula + "\""
    			       + "\" nombre\": \""   + nombre + "\","
    			       + "\" apellido\": \"" + apellido + "\","
    			       + "\" fdn\": \""      + fdn + "\","
    			       + "\" password\": \"" + password + "\","
    			       + "\" email\": \""    + email + "\"}";
    	
    	
    	return message;
    }
    
    
    
  
    }

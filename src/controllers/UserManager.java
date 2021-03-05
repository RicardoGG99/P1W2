package controllers;

import java.sql.Connection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String login(String cedula, String password, HttpServletRequest request) {
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
		String message = "";
    	
		try {
			boolean result = conn.psLogin(obj);
			
			if(result == true) {
				
				String[] obj2 = Conexion.psDatos(cedula, ph.hashPassword(password));
		    	
		    	HttpSession session = request.getSession();
		    	session.setAttribute("cedula",   obj2[0]);
		    	session.setAttribute("nombre",   obj2[1]);
		    	session.setAttribute("apellido", obj2[2]);
		    	session.setAttribute("fdn",      obj2[3]);
		    	session.setAttribute("password", obj2[4]);
		    	session.setAttribute("email",    obj2[5]);
				
				session.setAttribute("cedula",   cedula);
				session.setAttribute("password",   newPassword);
				
				message = "{\"message\": \"Login Exitoso\", "
					 	 + "\"status\": 200 }";
			}else {
				HttpSession session = request.getSession();
				session.invalidate();
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
    	
    	HttpSession session = request.getSession();
    	String message = "{\"cedula\": \""    + session.getAttribute("cedula") + "\","
    			        + "\"nombre\": \""    + session.getAttribute("nombre") + "\"," 
    			        + "\"apellido\": \""  + session.getAttribute("apellido") + "\","
    			        + "\"fdn\": \""       + session.getAttribute("fdn") + "\","
    			        + "\"password\": \""  + session.getAttribute("password") + "\","
    			        + "\"email\": \""     + session.getAttribute("email") + "\"\"}";
    	return message;
    }
    
    
    
  
    }

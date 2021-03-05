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
				HttpSession session = request.getSession();
				String[] obj2 = UserManager.llamarObject(cedula, password);
				
				session.setAttribute("cedula",   obj2[0]);
				session.setAttribute("nombre",   obj2[1]);
				session.setAttribute("apellido", obj2[2]);
				session.setAttribute("fdn",      obj2[3]);
				session.setAttribute("password", obj2[4]);
				session.setAttribute("email",    obj2[5]);
				
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
    
    public static String[] llamarObject(String cedula, String password) {
		
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
    	
    	String[] obj2 = conn.devolverObjeto(obj);
    	
    	
    	return obj2;
    	
    }
    
    public static String sesionMensaje(HttpServletRequest request) {
		
    	HttpSession session = request.getSession();
		
		String cedula = (String) session.getAttribute("cedula");
		String nombre = (String) session.getAttribute("nombre");
		String apellido = (String) session.getAttribute("apellido");
		String fdn = (String) session.getAttribute("fdn");
		String password = (String) session.getAttribute("password");
		String email = (String) session.getAttribute("email");
		
		String res = "{\"cedula\": \""   + cedula + "\", "
				    + "\"nombre\": \""   + nombre + "\", "
				    + "\"apellido\": \"" + apellido + "\", "
				    + "\"fdn\": \""      + fdn + "\", "
				    + "\"password\": \"" + password + "\", "
			 	    + "\"email\": \""    + email + "\" }";
    	
    	return res;
    }
    
    
    
  
    }

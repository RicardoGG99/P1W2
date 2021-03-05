package controllers;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import helpers.Conexion;
import helpers.PasswordHashing;


public class UserManager {
    Conexion conn = new Conexion();
    Connection connection = conn.getConnection();
    PasswordHashing ph = new PasswordHashing();
    
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
    public String login(String cedula, String password, HttpSession session) {
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
		String message = "";
    	
		try {
			boolean result = conn.psLogin(obj);
			
			if(result == true) {
				
				message = "{\"message\": \"Login Exitoso\", "
						 + "\"username\": \"" + cedula + "\", "
					 	 + "\"status\": 200 }";
			}else {
				 
				session.invalidate();
				message = "{\"message\": \"El Login fue fallido\", "
						 + "\"status\": 503 }";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	
    	return message;
    }
    
    public String[] llamarObject(String cedula, String password) {
		
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
    	
    	String[] obj2 = conn.devolverObjeto(obj);
    	
    	
    	return obj2;
    	
    }
    
    
    
  
    }

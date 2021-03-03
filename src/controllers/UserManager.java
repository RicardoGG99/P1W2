package controllers;

import java.sql.Connection;

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
    public String login(String cedula, String password) {
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
		String message = "";
    	
		try {
			boolean result = conn.psLogin(connection, obj);
			
			if(result == true) {
				message = "{\"message\": \"Login Exitoso\", \"status\": 200 }";
			}else {
				message = "{\"message\": \"El Login fue fallido\", \"status\": 503 }";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	
    	return message;
    }
    
    
    
    
  
    }
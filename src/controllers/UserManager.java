package controllers;

import java.sql.Connection;
import helpers.Conexion;
import helpers.PasswordHashing;


public class UserManager {
    Conexion conn = new Conexion();
    Connection connection = conn.getConnection();
    PasswordHashing ph = new PasswordHashing();
    
    
    public String register(String cedula, String nombre, String apellido, String fdn, String password, String email) {
    	
		String newPassword = ph.hashPassword(password);
		String[] obj = {cedula, nombre, apellido, fdn, newPassword, email};
		String message = "";
		
		try {
			boolean result = conn.preparedStatement(connection, obj);
			
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
  
    }
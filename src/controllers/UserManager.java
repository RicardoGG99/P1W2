package controllers;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    //Cookies (Datos de la Sesion)
    
    public static void Cookies(HttpServletResponse response, String cedula, String password) {
    	
    	
    	String[] obj = Conexion.psCookies(cedula, ph.hashPassword(password));
    	
    	
    	Cookie cookie =  new Cookie("cedula", obj[0]);
    	response.addCookie(cookie);
    	
    	Cookie cookie1 = new Cookie("nombre", obj[1]);
    	response.addCookie(cookie1);
    	
    	Cookie cookie2 = new Cookie("apellido", obj[2]);
    	response.addCookie(cookie2);
    	
    	Cookie cookie3 = new Cookie("fdn", obj[3]);
    	response.addCookie(cookie3);
    	
    	Cookie cookie4 = new Cookie("password", obj[4]);
    	response.addCookie(cookie4);
    	
    	Cookie cookie5 = new Cookie("email", obj[5]);
    	response.addCookie(cookie5);
    	 
    	System.out.println(cookie + "\n" + cookie1 + "\n" + cookie2 + "\n" + cookie3 + "\n" + cookie4 + "\n" + cookie5);
    }
    
    
    public static String obtenerCookies(HttpServletRequest request) {
    	
    	Cookie[] cookies = request.getCookies();
    	
    	String message = "{\"cedula\": \""   + cookies[0] + "\", "
    			        + "\"nombre\": \""   + cookies[1] + "\", "
    			        + "\"apellido\": \"" + cookies[2] + "\", " 
    			        + "\"fdn\": \""      + cookies[3] + "\", " 
    			        + "\"password\": \"" + cookies[4] + "\", " 
    					+ "\"email\": \""    + cookies[5] + "\"}";
    	
    	return message;
    	
    }
    
    
    
  
    }

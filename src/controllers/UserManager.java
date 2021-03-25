package controllers;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import helpers.Conexion;
import helpers.PasswordHashing;


public class UserManager {
	
	private static Conexion conn = new Conexion();
	private static Connection connection = conn.getConnection();
	private static PasswordHashing ph = new PasswordHashing();
	private static ArchivoManager am = new ArchivoManager();
	
    
    //Register
    
    public String register(String cedula, String nombre, String apellido, String fdn, String password, String email, Part part, HttpServletRequest request) {
    	
		String newPassword = ph.hashPassword(password);
		String[] obj = {cedula, nombre, apellido, fdn, newPassword, email};
		String message = "";
		
		
		try {
			
			boolean result = conn.psRegistro(connection, obj);
			boolean res = am.createFdp(request, cedula);
			
			if(result == true && res == true) {
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
    
    public String login(String cedula, String password, HttpServletResponse response, HttpServletRequest request) {
    	String newPassword = ph.hashPassword(password);
    	String[] obj = {cedula, newPassword};
		String message = "";
		
		try {
			boolean result = conn.psLogin(obj, connection);
			
			if(result == true) {
				
				HttpSession session = request.getSession();
				session.setAttribute("cedula", cedula);
				
				String[] obj2 = Conexion.psDatos(cedula, ph.hashPassword(password));
		    	
		    		Cookie cookie = new Cookie("cedula", obj2[0]);
		    		response.addCookie(cookie);
		    		
		    		Cookie cookie1 = new Cookie("nombre", obj2[1]);
		    		response.addCookie(cookie1);
		    		
		    		Cookie cookie2 = new Cookie("apellido", obj2[2]);
		    		response.addCookie(cookie2);
		    		
		    		Cookie cookie3 = new Cookie("fdn", obj2[3]);
		    		response.addCookie(cookie3);
		    		
		    		Cookie cookie4 = new Cookie("password", password);
		    		response.addCookie(cookie4);
		    		
		    		Cookie cookie5 = new Cookie("email", obj2[5]);
		    		response.addCookie(cookie5);
		    		
		    		Cookie cookie6 = new Cookie("segundoNombre", obj2[6]);
		    		response.addCookie(cookie6);
		    		
		    		Cookie cookie7 = new Cookie("segundoApellido", obj2[7]);
		    		response.addCookie(cookie7);
		    		
		    		Cookie cookie8 = new Cookie("telf", obj2[8]);
		    		response.addCookie(cookie8);
		    		
		    		Cookie cookie9 = new Cookie("fdp", "../../private/" + obj2[0] + "/fdp.png");
		    		response.addCookie(cookie9);
		    		
				
				message = "{\"message\": \"Login Exitoso\", "
					 	 + "\"status\": 200 }";
			}else {
				message = "{\"message\": \"El Login fue fallido\", "
						 + "\"status\": 503 }";
			}
			
		} catch (Exception e) {
			message = "{\"message\": \"El Login fue fallido\", "
					 + "\"status\": 503 }";
			e.printStackTrace();
		}
    	
    	
    	
    	
    	return message;
    }
    
    
    
    //Update
    
    public static String update(HttpServletRequest request, String[] obj) {
    	Cookie cookies[] = request.getCookies();
    	String cedula = "";
    	String message = "";
    	int result = 0;
    	obj[4] = ph.hashPassword(obj[4]);
    	
    	for(Cookie c: cookies) {
    		if(c.getName().equals("cedula")) {
    			cedula = c.getValue();
    		}
    	}
    	

    	
    	try {
			result = Conexion.psUpdate(obj, cedula, connection);
			if(result == 1) {
				message = "{\"message\": \"Update Exitoso\", "
					 	 + "\"status\": 200 }";

			}else {
				message = "{\"message\": \"Update Fallido\", "
					 	 + "\"status\": 503 }";
			}
			
		} catch (Exception e) {
			
		}
    	
    	return message;
    }

    
    //Delete
    
    public static String delete(HttpServletRequest request, HttpServletResponse response) {
    	String message = "";
    	boolean result = false;
    	Cookie cookies[] = request.getCookies();
    	String cedula = "";
    	String password = "";
    	
    	
    	
    	for(Cookie c: cookies) {
    		if(c.getName().equals("cedula")) {
    			cedula = c.getValue();
    		}
    		
    		if(c.getName().equals("password")) {
    			password = c.getValue();
    		}
    		
    		
    	}
    	try {
    		String newPassword = ph.hashPassword(password);
    		
    		result = Conexion.psDelete(connection, cedula, newPassword);
    		
    		if(result == true) {
    			
    			try {
    				
//    				am.deleteFdp(cedula, request, response);
    				message = "{\"message\": \"Delete Exitoso\", "
						 	 + "\"status\": 200 }";
        	    	
        			
				} catch (Exception e) {
					message = "{\"message\": \"Delete Fallido\", "
						 	 + "\"status\": 503 }";
				}
        	}
    		
		} catch (Exception e) {
			message = "{\"message\": \"Delete Fallido\", "
				 	 + "\"status\": 503 }";
		}
    	
    	return message;
    }
    
    
    //Datos de la Sesion
    
    public static String showCredentials(HttpServletRequest request) {
    	
    	Cookie cookies[] = request.getCookies();
    	String message = "";
    	String cedula = "";
    	String nombre = "";
    	String apellido = "";
    	String fdn = "";
    	String password = "";
    	String email = "";
    	String segundoNombre = "";
    	String segundoApellido = "";
    	String telf = "";
    	String fdp =  "";
    	
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
    		
    		if(c.getName().equals("segundoNombre")) {
    			segundoNombre = c.getValue();
    		}
    		
    		if(c.getName().equals("segundoApellido")) {
    			segundoApellido = c.getValue();
    		}
    		
    		if(c.getName().equals("telf")) {
    			telf = c.getValue();
    		}
    		
    		if(c.getName().equals("fdp")) {
    			fdp = c.getValue();
    		}
    	}
    	
    	
    	System.out.println(cedula + "\n" + nombre + "\n" + apellido + "\n" + fdn + "\n" + password + "\n" + email);
    	message = "{\"cedula\":   \""  +  cedula +  "\", \"nombre\": \"" + nombre + "\", \"apellido\": \"" + apellido + "\", \"fdn\": \"" + fdn + "\", \"password\": \"" + password + "\", \"email\": \"" + email + "\", \"segundoNombre\": \"" + segundoNombre + "\", \"segundoApellido\": \"" + segundoApellido + "\", \"telf\": \"" + telf + "\", \"fdp\": \"" + fdp + "\", \"status\": \"503\"}";
    	
    	return message;
    }
    
    
    //close session and cookies
    
    public boolean closeSession(HttpServletRequest request, HttpServletResponse response) {
    	boolean result = false;
    	
    	try {
    		
    		HttpSession session = request.getSession();
    		session.invalidate();
    		
    		Cookie[] cookies = request.getCookies();
    	    if (cookies != null)
    	        for (Cookie cookie : cookies) {
    	            cookie.setValue("");
    	            cookie.setPath("/");
    	            cookie.setMaxAge(0);
    	            response.addCookie(cookie);
    	        }
    		
    	result = true;
			
		} catch (Exception e) {
			result = false;
		}
    	
    	return result;
    }
    
    
    
    
  
    }
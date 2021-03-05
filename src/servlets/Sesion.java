package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.UserManager;


@MultipartConfig()
@WebServlet("/Sesion")
public class Sesion extends HttpServlet {
	private static final long serialVersionUID =  1L;
	
	UserManager um = new UserManager();
	
	
    public Sesion() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
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
		
		out.println(res);
		
		
	}


	
}

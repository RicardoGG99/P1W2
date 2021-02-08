package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.UserManager;


@MultipartConfig()
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID =  1L;
	
	UserManager um = new UserManager();
	
	
    public Register() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fdn = request.getParameter("fdn");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		String res = um.register(cedula, nombre, apellido, fdn, password, email);
		out.println(res);
		
	}


	
}
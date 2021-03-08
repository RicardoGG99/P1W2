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
@WebServlet("/Sesion")
public class Sesion extends HttpServlet {
	private static final long serialVersionUID =  1L;
	
	
	
    public Sesion() {
        super();
    }
    
    //Mostrar Credenciales al usuario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String res = UserManager.showCredentials(request);
		out.println(res);
	}
	
	//Update
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fdn = request.getParameter("fdn");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String segundoNombre = request.getParameter("segundoNombre");
		String segundoApellido = request.getParameter("segundoApellido");
		String telf = request.getParameter("telf");
		
		String[] obj = {cedula, nombre, apellido, fdn, password, email, segundoNombre, segundoApellido, telf};
		
		String res = UserManager.update(request, obj);
		out.println(res);
	}
	
	//Delete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		UserManager um = new UserManager();
		PrintWriter out = response.getWriter();
		
		String res = um.delete(request);
		
		if(res == "{\"message\": \"Delete Exitoso\", "
   				 	 + "\"status\": 200 }") {
			boolean r = um.closeSession(request, response);
			
			if(r == false) {
				res = "{\"message\": \"Delete Fallido\", "
	   				 	 + "\"status\": 200 }";
			}
		}
		
		out.println(res);
	}

	
}

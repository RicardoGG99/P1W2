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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String res = UserManager.showCredentials(request);
		out.println(res);
	}
	
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
		String segundoApellido = request.getParameter("segundoNombre");
		String telf = request.getParameter("telf");
		
		String[] obj = {cedula, nombre, apellido, fdn, password, email, segundoNombre, segundoApellido, telf};
		
		String res = UserManager.update(request, obj);
		out.println(res);
	}

//	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		
//		String res = "";
//		out.println(res);
//	}

	
}

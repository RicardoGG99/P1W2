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
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID =  1L;
	
	UserManager um = new UserManager();
	
	
    public Login() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		String cedula = request.getParameter("cedula");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		String[] obj = um.llamarObject(cedula, password);
		
		session.setAttribute("cedula",   obj[0]);
		session.setAttribute("nombre",   obj[1]);
		session.setAttribute("apellido", obj[2]);
		session.setAttribute("fdn",      obj[3]);
		session.setAttribute("password", obj[4]);
		session.setAttribute("email",    obj[5]);
		
		String res = um.login(cedula, password, session);
		out.println(res);
		
	}
	
	//protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//}

		
		//protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//}



	
}
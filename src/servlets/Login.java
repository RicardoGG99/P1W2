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
		
		String res = um.login(cedula, password);
		out.println(res);
		
	}
	
	//protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//}

		
		//protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//}



	
}

	
	


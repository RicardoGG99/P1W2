package helpers;


import java.sql.Connection;




import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	
	
	PropertiesReader pr = new PropertiesReader();
	private static Connection conn = null;
	
	
	//Methods
	public Connection getConnection() {
		try {
			if(conn == null) {
				
				String DBdriver = pr.getSQL("DBdriver");
				String DBurl = pr.getSQL("DBurl");
				String DBuser = pr.getSQL("DBuser");
				String DBpassword = pr.getSQL("DBpassword");
				
			Class.forName(DBdriver);
			conn = DriverManager.getConnection(DBurl, DBuser, DBpassword);
			System.out.println("Conexion Exitosa");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Registro
	public boolean psRegistro(Connection connection, String[] obj) {
		
		boolean result = false;
		
		try {
			String sentence = pr.getSQL("registro");
			
			PreparedStatement ps = connection.prepareStatement(sentence);
			ps = connection.prepareStatement(sentence);
			ps.setString(1,  obj[0]);
			ps.setString(2, obj[1]);
			ps.setString(3, obj[2]);
			ps.setString(4,  obj[3]);
			ps.setString(5, obj[4]);
			ps.setString(6, obj[5]);
			ps.executeUpdate();
			ps.close();
			result = true;
		
		} catch (SQLException e) {
			result = false;
		}
		
		return result;
		
	}
	
	//Login
		public boolean psLogin(String[] obj) {
			boolean result = false;
			
			try {
				String sentence = pr.getSQL("login");
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement(sentence);
				ps.setString(1, obj[0]);
				ps.setString(2, obj[1]);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					result = true;
				}
				
			
			} catch (SQLException e) {
				result = false;
			}
			
			
			
			return result;
		}
		
		public String[] devolverObjeto(String[] obj) {
			
			try {
				String sentence = pr.getSQL("login");
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement(sentence);
				ps.setString(1, obj[0]);
				ps.setString(2, obj[1]);
				
				rs = ps.executeQuery();
				
				String[] obj2 = {"", "", "", "", "", ""};
				
				if(rs.next()) {
					obj2[0] = rs.getString("cedula");
					obj2[1] = rs.getString("nombre");
					obj2[2] = rs.getString("apellido");
					obj2[3] = rs.getString("fdn");
					obj2[4] = rs.getString("pass");
					obj2[5] = rs.getString("email");
				}
				
			
			} catch (SQLException e) {
				System.out.println(e);
			}
			
			
			
			
			return obj;
			
		}
		
		//Delete
		
		//Update
	
}
package helpers;


import java.sql.Connection;





import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	
	
	static PropertiesReader pr = new PropertiesReader();
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
		
	//Obtener todos los datos de la fila
		public static String[] psDatos(String cedula, String password) {
			
			String[] obj = {"", "", "", "", "", ""};
			
			try {
				String sentence = pr.getSQL("login");
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement(sentence);
				ps.setString(1, cedula);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					for(int x = 0; x <= 5; x++) {
						obj[x] = rs.getString(x+1);
					}
					
				}
				
			
			} catch (SQLException e) {
				System.out.println(e);
			}
			
			
			
			
			return obj;
		}
		
		
		
		
		
		//Delete
		
		//Update
	
}
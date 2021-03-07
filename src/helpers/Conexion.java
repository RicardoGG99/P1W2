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
		public boolean psLogin(String[] obj, Connection connection) {
			boolean result = false;
			
			try {
				String sentence = pr.getSQL("login");
				PreparedStatement ps;
				ResultSet rs;
				
				ps = connection.prepareStatement(sentence);
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
			
			String[] obj = {"", "", "", "", "", "", "", "", ""};
			
			try {
				String sentence = pr.getSQL("login");
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement(sentence);
				ps.setString(1, cedula);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				
				
				while(rs.next()) {
						for(int x = 0; x < 9; x++) {
						obj[x] = rs.getString(x+1);
						System.out.println(obj[x]);
						}
				}
				
			
			} catch (SQLException e) {
				System.out.println(e);
			}
			
			
			
			
			return obj;
		}
		
		
		
		
		
		//Delete
		
		
		
		
		//Update
		
		public static int psUpdate(String[] obj, String cedula, Connection connection) {
			int result = 0;
			String sentence = pr.getSQL("update");
			
			try {
				PreparedStatement pst = null;
				pst = connection.prepareStatement(sentence);
				pst.setString(1,  obj[0]);
				pst.setString(2, obj[1]);
				pst.setString(3, obj[2]);
				pst.setString(4,  obj[3]);
				pst.setString(5, obj[4]);
				pst.setString(6, obj[5]);
				pst.setString(7, obj[6]);
				pst.setString(8, obj[7]);
				pst.setString(9, obj[8]);
				pst.setString(10, cedula);
				System.out.println(pst);
				result = pst.executeUpdate();
				pst.close();
				
			} catch (SQLException e) {
				result = 0;
				e.printStackTrace();
			}
			return result;
		}
		
//		public static void main(String[] args) {
//			Conexion c = new Conexion();
//			Connection conn = c.getConnection();
//			String[] obj = {"27637837", "Ricardo", "Graziano", "19-06-1999", "ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f", "ricardo@graziano", "Jose", "Gutierrez", "0412-9992233"};
//			String cedula = "27637837";
//			
//			System.out.println(c.psUpdate(obj, cedula, conn));
//			
//		}
		
		
	
}
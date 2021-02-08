package helpers;


import java.sql.Connection;




import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	
	
	PropertiesReader pr = new PropertiesReader();
	private static Connection conn = null;
	
	
	//Methods
	public Connection getConnection() {
		try {
			if(conn == null) {
			Class.forName(pr.getSQL("DBdriver"));
			conn = DriverManager.getConnection(pr.getSQL("DBurl"), pr.getSQL("DBuser"), pr.getSQL("DBpassword") );
			System.out.println("Conexion Exitosa");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean preparedStatement(Connection connection, String[] obj) {
		
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
	
	public static void main(String[] args) {
		PropertiesReader pr = new PropertiesReader();
		System.out.println(pr.getSQL("DBdriver" ) + "\n" + pr.getSQL("DBurl") + "\n" + pr.getSQL("DBuser") + "\n" + pr.getSQL("DBpassword") + "\n" + pr.getSQL("registro"));
	}
	
}
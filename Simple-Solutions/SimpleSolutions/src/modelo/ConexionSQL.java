package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConexionSQL {
	Statement stm;
	Connection con;
	
	
	public Connection getConexionSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/analisisqa","root","");
			//Statement stm = con.createStatement();
		}
		catch(ClassNotFoundException exc) {
			exc.printStackTrace();
			JOptionPane.showMessageDialog(null, "No Conectado");
		}
		catch (SQLException ex) {
			Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "No Conectado");
		}
		return con;
		
	}
}

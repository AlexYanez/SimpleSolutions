package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ProyectoDAO {
ConexionSQL conectar = new ConexionSQL();
Connection con;
PreparedStatement ps;
ResultSet rs;
	
	public int insertar(Proyecto p) {
		String sql = "INSERT INTO proyecto (code, name, status, language, duration, advance, effec) VALUES (?,?,?,?,?,?,?)";
		try {
			
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			ps.setInt(1, p.getCod());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getStatus());
			ps.setString(4, "PHP");
			ps.setInt(5, p.getDuracion());
			ps.setInt(6, p.getAvance());
			ps.setDouble(7, 0.0);
			ps.executeUpdate();
			
			return 1;
	
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int inicial() {
		String sql = "SELECT COUNT(*) FROM proyecto";
		try {
			con = conectar.getConexionSQL();
			Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    rs.next();
		    int count = rs.getInt(1);
			
			return count+1;
	
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;		
	}
	
	
	public Proyecto buscar(int bus) {
		String sql = "SELECT * FROM proyecto WHERE code = '"+ bus +"' ";
		
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			Proyecto p = new Proyecto();
			boolean consulta = false;
			while (rs.next()) {
				p.setCod(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setStatus( rs.getInt(3));
				p.setLenguaje(rs.getString(4));
				p.setDuracion(rs.getInt(5));
				p.setAvance(rs.getInt(6));
				p.setEfectividad(rs.getDouble(7));

				consulta = true;
			}
			if (consulta == false) {
				JOptionPane.showMessageDialog(null, "No se encuentran datos en la consulta");
			}			
			return p;		
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
		
	
	public int actualizar(Proyecto p, int pos) {
		String sql = "UPDATE proyecto SET code=?, name=?, status=?, language=?, duration=?, advance=? where code='"+ pos +"' " ;
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			ps.setInt(1, p.getCod());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getStatus());
			ps.setString(4, "PHP");
			ps.setInt(5, p.getDuracion());
			ps.setInt(6, p.getAvance());
			//ps.setDouble(7, p.getEfectividad());
			ps.executeUpdate();
			
			return 1;	
		} catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}

	
}

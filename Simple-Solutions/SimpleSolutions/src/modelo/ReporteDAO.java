package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReporteDAO {
	ConexionSQL conectar = new ConexionSQL();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String changeVersion = "";
	int idProject = 0;
	int idVersion = 0;
	
	public Version cualVersion(String pos) {
		String sql = "SELECT * FROM version WHERE nameVersion ='"+ pos +"' ";
		Version ver = new Version();
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				ver.setIdversion(rs.getInt(1));
				ver.setNameVersion(rs.getString(2));
				ver.setEfiVersion(rs.getDouble(3));
				ver.setAcumCasos(rs.getInt(4));
				ver.setAcumFail(rs.getDouble(5));
				ver.setAcumTime(rs.getDouble(6));
				ver.setContPruebas(rs.getInt(7));
				idProject = rs.getInt(8);
			}					
			return ver;
			
		}catch (Exception e) {
				e.printStackTrace();
				return null;
		}

	}
	
	public Proyecto cualProyecto(int pos) {
		String sql = "SELECT * FROM proyecto WHERE code ='"+ pos +"' ";
		Proyecto p = new Proyecto();
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				p.setCod(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setStatus(rs.getInt(3));
				p.setLenguaje(rs.getString(4));
				p.setDuracion(rs.getInt(5));
				p.setAvance(rs.getInt(6));
				p.setEfectividad(rs.getDouble(7));
			}					
			return p;
			
		}catch (Exception e) {
				e.printStackTrace();
				return null;
		}
	}
	
	public int pruebasPorProyecto(int codProject) {	
		String sql = "SELECT SUM(contPruebas) FROM version WHERE codeProject ="+ codProject + ";";
	
		try {
			con = conectar.getConexionSQL();
			Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    rs.next();
		    int count = rs.getInt(1);
			
			return count;
	
		} catch(Exception e){
			e.printStackTrace();
			return 1;
		}	
	}

}

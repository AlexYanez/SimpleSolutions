package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VersionDAO {
	ConexionSQL conectar = new ConexionSQL();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String changeVersion = "";
	
	
	public int insertarVer(Version ver, Proyecto pro) {
		String sql = "INSERT INTO version (idVersion, nameVersion, efficVersion, acumCases,	acumFail, acumTime,	contPruebas, codeProject) VALUES (?,?,?,?,?,?,?,(SELECT code FROM proyecto WHERE code = ?))";
		try { 
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			ps.setInt(1, ver.getIdversion());
			ps.setString(2, ver.getNameVersion());
			ps.setDouble(3, 0.0);
			ps.setInt(4, ver.getAcumCasos());
			ps.setDouble(5, 0.0);
			ps.setDouble(6, 0.0);
			ps.setInt(7, ver.getContPruebas());
			ps.setInt(8, pro.getCod());
			ps.executeUpdate();	
			return 1;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int actualizarVer(Version ver, Proyecto pro) {		
		try {
			if (changeVersion.equals(ver.getNameVersion()) ) {
				return 1;
			}
			else {
				String sql = "INSERT INTO version (idVersion, nameVersion, efficVersion, acumCases,	acumFail, acumTime,	contPruebas, codeProject) VALUES (?,?,?,?,?,?,?,(SELECT code FROM proyecto WHERE code = ?))";
				con = conectar.getConexionSQL();
				ps=con.prepareStatement(sql);
				
				ps.setInt(1, ver.getIdversion());
				ps.setString(2, ver.getNameVersion());
				ps.setDouble(3, 0.0);
				ps.setInt(4, ver.getAcumCasos());
				ps.setDouble(5, 0.0);
				ps.setDouble(6, 0.0);
				ps.setInt(7, ver.getContPruebas());
				ps.setInt(8, pro.getCod());
				ps.executeUpdate();
				return 2;
			}
				
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
		
	public Version buscarVer(int bus, Proyecto p) {
		String sql = "SELECT idVersion, nameVersion FROM version WHERE codeProject = '"+ bus +"' ORDER BY idVersion DESC LIMIT 1";
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			Version ver = new Version();
			while (rs.next()) {
				ver.setIdversion(rs.getInt(1));
				ver.setNameVersion(rs.getString(2));
				changeVersion = rs.getString(2);
			}		
			return ver;
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	

}

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PruebaDAO {

	ConexionSQL conectar = new ConexionSQL();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	int idProject = 0;
	int idVersion = 0;
	
	public int inicialPrueba() {
		String sql = "SELECT COUNT(*) FROM prueba";
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
	
	public int insertarPrueba(Prueba pru, Version ver) {
		String sql = "INSERT INTO prueba (codeTest, description, typeTest, countCases, casesFail, timeResponse, speed, efficTest, id_version) values (?,?,?,?,?,?,?,?,(SELECT idVersion FROM version WHERE idVersion = ?))";
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, pru.getCodPrueba());
			ps.setString(2, pru.getDescripcion());
			ps.setInt(3, pru.getTipoprueba());
			ps.setInt(4, pru.getCantCasosPrueba());
			ps.setDouble(5, pru.getCasosFallidos());
			ps.setDouble(6, pru.getTimeResponse());
			ps.setDouble(7, pru.getRapidez());
			ps.setDouble(8, pru.getEfiPrueba());
			ps.setInt(9, ver.getIdversion());
			ps.executeUpdate();
			return 1;
			
			
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}

	}
	
	public Prueba buscarPrueba(int bus) {
		String sql = "SELECT * FROM prueba WHERE codeTest LIKE '%"+bus+"%'";		
		
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			boolean consulta = false;
			Prueba pru = new Prueba();
			while (rs.next()) {
				
				pru.setCodPrueba(rs.getInt(1));
				pru.setDescripcion(rs.getString(2));
				pru.setTipoprueba(rs.getInt(3));
				pru.setCantCasosPrueba(rs.getInt(4));
				pru.setCasosFallidos(rs.getDouble(5));
				pru.setTimeResponse(rs.getDouble(6));
				pru.setRapidez(rs.getDouble(7));
				pru.setEfiPrueba(rs.getDouble(8));
				idVersion = rs.getInt(9);
				
				consulta = true;
			}
			if (consulta == false) {
				JOptionPane.showMessageDialog(null, "No se encuentran datos en la consulta");
			}			
			return pru;			
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	public int actualizarPrueba(Prueba pru, int pos) {
		String sql = "UPDATE prueba SET codeTest=?, description=?, typeTest=?, countCases=?, casesFail=?, timeResponse=?, speed=?, efficTest=?, id_version=? WHERE codeTest='"+ pos +"' " ;
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, pru.getCodPrueba());
			ps.setString(2, pru.getDescripcion());
			ps.setInt(3, pru.getTipoprueba());
			ps.setInt(4, pru.getCantCasosPrueba());
			ps.setDouble(5, pru.getCasosFallidos());
			ps.setDouble(6, pru.getTimeResponse());
			ps.setDouble(7, pru.getRapidez());
			ps.setDouble(8, pru.getEfiPrueba());
			ps.setInt(9, idVersion);
			ps.executeUpdate();
			
			return 1;
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public int actualizarVersion(Version ver, Proyecto p, int pos) {
		String sql = "UPDATE version SET idVersion=?, nameVersion=?, efficVersion=?, acumCases=?, acumFail=?, acumTime=?, contPruebas=?, codeProject=? where idVersion='"+ pos +"' ";
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);

			ps.setInt(1, ver.getIdversion());
			ps.setString(2, ver.getNameVersion());
			ps.setDouble(3, ver.getEfiVersion());
			ps.setInt(4, ver.getAcumCasos());
			ps.setDouble(5, ver.getAcumFail());
			ps.setDouble(6, ver.getAcumTime());
			ps.setInt(7, ver.getContPruebas());
			ps.setInt(8, p.getCod());
			ps.executeUpdate();
			
			return 1;	
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/*
	public int actualizarAcum(int pos) {
		String sql = "UPDATE version SET idVersion=?, nameVersion=?, efficVersion=?, acumCases=?, acumFail=?, acumTime=?, contPruebas=?, codeProject=? where idVersion='"+ pos +"' ";
		String sql = "SELECT SUM(CASE WHEN id_Version= 2 THEN countCases ELSE 0 END) FROM prueba";
		
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);

			
			return 1;	
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return 0;
	}
	
	*/
	
	public int actualizarProyecto(Proyecto p, int pos) {
		String sql = "UPDATE proyecto SET code=?, name=?, status=?, language=?, duration=?, advance=?, effec=? where code='"+ pos +"' " ;
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			ps.setInt(1, p.getCod());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getStatus());
			ps.setString(4, "PHP");
			ps.setInt(5, p.getDuracion());
			ps.setInt(6, p.getAvance());
			ps.setDouble(7, p.getEfectividad());
			ps.executeUpdate();
			
			return 1;	
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public Version traerVersion(int proyecto, String version) {
		String sql = "SELECT * FROM version WHERE nameVersion ='"+ version +"' AND codeProject = '"+ proyecto +"' ";
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
	
	public Proyecto traerProyecto() {
		String sql = "SELECT * FROM proyecto WHERE code ='"+ idProject +"' ";
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


	public ArrayList<String> comboProyecto(){
		ArrayList<String> lista = new ArrayList<String>();
		String sql = "SELECT * FROM proyecto";	
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				lista.add(rs.getString(2));
			}
			return lista;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public ArrayList<String> comboVersion(int idProyecto){
		ArrayList<String> lista = new ArrayList<String>();
		String sql = "SELECT * FROM version WHERE codeProject=" + idProyecto;
		
		try {
			con = conectar.getConexionSQL();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				lista.add(rs.getString(2));
			}
			return lista;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}

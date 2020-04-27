package ssolutions;

import controlador.CPrueba;
import controlador.Cprincipal;
import controlador.Cproyecto;
import modelo.ConexionSQL;
import modelo.Proyecto;
import modelo.ProyectoDAO;
import modelo.Prueba;
import modelo.PruebaDAO;
import modelo.Version;
import modelo.VersionDAO;

public class Main {

	public static void main(String[] args) {
		
		ConexionSQL con = new ConexionSQL();
		// TODO Auto-generated method stub
		Proyecto p = new Proyecto();
		Prueba pru = new Prueba();
		Version ver = new Version();
		
		CPrueba cpru = new CPrueba(pru, ver, p);
		Cproyecto cp = new Cproyecto(p);
		
		ProyectoDAO pdao = new ProyectoDAO();
		PruebaDAO prudao = new PruebaDAO();
		VersionDAO verdao = new VersionDAO();
	
		Cprincipal cprincipal = new Cprincipal(p, pru, ver, cpru, cp, con, pdao, prudao, verdao);
		cprincipal.initPrincipal();

	}
	
			
}
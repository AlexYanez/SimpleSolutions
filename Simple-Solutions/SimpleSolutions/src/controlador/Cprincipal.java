package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.ConexionSQL;
import modelo.Proyecto;
import modelo.ProyectoDAO;
import modelo.Prueba;
import modelo.PruebaDAO;
import modelo.Version;
import modelo.VersionDAO;
import vista.Vprincipal;
import vista.Vproyecto;
import vista.Vprueba;
import vista.Vreporte;

public class Cprincipal {
	Vproyecto vp = new Vproyecto();
	Vprueba vqa = new Vprueba();
	Vprincipal vprincipal = new Vprincipal();
	Proyecto p = new Proyecto();
	ProyectoDAO pdao = new ProyectoDAO();
	PruebaDAO prudao = new PruebaDAO();
	Prueba pru = new Prueba();
	Version ver = new Version();
	VersionDAO verdao = new VersionDAO();
	CPrueba cpru = new CPrueba(pru, ver, p);
	Cproyecto cp = new Cproyecto(p);
	

	public Cprincipal(Proyecto p, Prueba pru, Version ver, CPrueba cpru, Cproyecto cp, ConexionSQL con, ProyectoDAO pdao, PruebaDAO prudao, VersionDAO verdao) {
		vprincipal.addRegistrarListener(new registrarProyecto()); 
		vprincipal.addActualizarPruListener(new actualizar());
		vprincipal.addAnalisisQAListener(new analisisQA()); 
		vprincipal.addConsultarListener(new consultar());
		vprincipal.addReporteEspecificoListener(new reporteEspecifico());
	}

	public void initPrincipal() {
		vprincipal.setVisible(true);
	}
	

	class registrarProyecto implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cp.initProyecto();
			}
	}
	
	class actualizar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cp.initActualizar();	
		}
	}
	
	class analisisQA implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cpru.initPrueba();
		}
	}
	
	class consultar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cpru.initConsultar();
		}
	}
	
	class reporteEspecifico implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Vreporte vr = new Vreporte();
			vr.setVisible(true);
		}
	}

}

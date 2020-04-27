package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JOptionPane;

import modelo.ConexionSQL;
import modelo.Proyecto;
import modelo.ProyectoDAO;
import modelo.ReporteDAO;
import modelo.Version;
import modelo.VersionDAO;
import vista.Vproyecto;

public class Cproyecto extends ConexionSQL {

	Proyecto p = new Proyecto();
	Vproyecto vp = new Vproyecto();
	Version ver = new Version();
	Connection con = getConexionSQL();
	ProyectoDAO pdao = new ProyectoDAO();
	VersionDAO vdao = new VersionDAO();
	ReporteDAO rdao = new ReporteDAO();
	private java.text.DecimalFormat formato = new java.text.DecimalFormat("0.00"); 
	private java.text.DecimalFormat formateador = new java.text.DecimalFormat("000000");
	
	public Cproyecto(Proyecto p) {
		super();
		this.vp.addRegistrarListener(new guardarValor()); 
		this.vp.addActualizarListener(new actualizarValor());
		this.vp.addBuscarListener(new buscarValor());
	}
	
	public void initProyecto() {
		vp.setVisible(true);
		vp.limpiar();
		vp.textID.setText(formateador.format(pdao.inicial())); //Esto debe aparecer al abrir Registrar
		
		//Format(Me.TxtFolio, "00000")
		
		vp.btnBuscar.setVisible(false);
		vp.btnRegistrar.setEnabled(true);
		vp.btnActualizar.setEnabled(false);
		vp.textNombre.setEditable(true);
		vp.textAvance.setText("00");
		vp.textAvance.setEditable(true);
		vp.textEfec.setText("0.00");
		vp.comboEstado.setEditable(false);
		vp.comboDuracion.setEditable(true);
		vp.btnLimpiar.setVisible(true);
		vp.textVersion.setText("v1.0");
		vp.textID.setEditable(false);
		vp.lblVersion.setText("Versión");	
	}
	
	public void initActualizar() {
		vp.setVisible(true);		
		vp.limpiar();
		
		vp.btnBuscar.setVisible(true);
		vp.btnRegistrar.setEnabled(false);
		vp.btnActualizar.setEnabled(true);
		vp.btnLimpiar.setVisible(true);
		vp.textNombre.setEditable(true);
		vp.textAvance.setEditable(true);
		vp.comboEstado.setEditable(true);
		vp.comboDuracion.setEditable(true);
		vp.textID.setEditable(true);
		vp.lblVersion.setText("Ult. Versión");
	}
	
				
	// Registrar un valor en el vector
		class guardarValor implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					//Verificamos si hay campos vacios para poder guardar
					if (vp.textID.getText().isEmpty() || vp.textNombre.getText().isEmpty() || vp.textVersion.getText().isEmpty() || vp.textAvance.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
					else {
						//Se almacenan los datos ingresados a memoria
						p.setCod(Integer.valueOf(vp.textID.getText()));
						p.setNombre(vp.textNombre.getText());
						p.setStatus(vp.comboEstado.getSelectedIndex());
						//p.setLenguaje(lenguaje); No implementado
						p.setDuracion(vp.comboDuracion.getSelectedIndex());
						p.setAvance(Integer.valueOf(vp.textAvance.getText()));

						ver.setNameVersion(vp.textVersion.getText());
						ver.setIdversion(0);
						int rp = pdao.insertar(p);
						int rv = vdao.insertarVer(ver,p);
						
						if (rp==1 && rv==1) {
							JOptionPane.showMessageDialog(null, "Proyecto registrado con éxito");
							vp.limpiar();
							vp.textID.setText(formateador.format(pdao.inicial()));
						}
						else
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
							
						} //end else
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
					error.printStackTrace();
					vp.limpiar();
				}
			} //end void
		} //end class
		
		class buscarValor implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					if (vp.textID.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Debe indicar que desea buscar");
					else {
						ProyectoDAO pdao = new ProyectoDAO();
						int dato = Integer.valueOf(vp.textID.getText());
						Proyecto p = pdao.buscar(dato);
						Version ver = vdao.buscarVer(dato, p);
						int valor = rdao.pruebasPorProyecto(p.getCod());

						vp.textNombre.setText(p.getNombre());
						vp.comboEstado.setSelectedIndex((p.getStatus()));
						vp.comboDuracion.setSelectedIndex(p.getDuracion());
						vp.textAvance.setText(String.valueOf(p.getAvance()));
						vp.textEfec.setText(formato.format(p.getEfectividad()));
						vp.textVersion.setText(ver.getNameVersion());
						
						if (valor != 0)
							vp.textEfec.setText(formato.format(p.getEfectividad()/valor));
						else
							vp.textEfec.setText(formato.format(0.0));
							
					}
					
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
					error.printStackTrace();
					vp.limpiar();
				}
			} //end void
		} //end cl
		
		class actualizarValor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				try {
					//Verificamos si hay campos vacios para poder guardar
					if (vp.textNombre.getText().isEmpty() || vp.textVersion.getText().isEmpty() || vp.textAvance.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
					else {

						//Se almacenan los datos ingresados a memoria
						p.setCod(Integer.valueOf(vp.textID.getText()));
						int pos=Integer.valueOf(vp.textID.getText());
						p.setNombre(vp.textNombre.getText());
						p.setStatus(vp.comboEstado.getSelectedIndex());
						//p.setLenguaje(i, lenguaje); No implementado
						p.setDuracion(vp.comboDuracion.getSelectedIndex());
						p.setAvance(Integer.valueOf(vp.textAvance.getText()));
						//p.setEfectividad(Double.valueOf(vp.textEfec.getText()));
		
						ver.setNameVersion(vp.textVersion.getText());
						int rp = pdao.actualizar(p, pos);
						int rv = vdao.actualizarVer(ver,p);
						
						if (rp==1 && rv==1) {
							JOptionPane.showMessageDialog(null, "Proyecto actualizado con éxito");
							vp.limpiar();
						}
						else if (rp==1 && rv==2) {
							JOptionPane.showMessageDialog(null, "Proyecto y versión actualizada con éxito");
							vp.limpiar();
						}
						else
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
						} //end else
					
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
					error.printStackTrace();
					vp.limpiar();
				}
			} //end void 
		} //end class
			

	
		/*
		// Metodo auxiliar de conversión de lenguajes para guardarlos en memoria
		void guardarLenguajes(int y) {
			int i = 0;
			if (vp.chAngular.isSelected() == true) {
				p[y].setLenguaje(i, vp.chAngular.getText());
				i++;
			}
			if (vp.chJava.isSelected() == true) {
				p[y].setLenguaje(i, vp.chJava.getText());
				i++;
			}
			if (vp.chReact.isSelected() == true) {
				p[y].setLenguaje(i, vp.chReact.getText());
				i++;
			}
			if (vp.chVue.isSelected() == true) {
				p[y].setLenguaje(i, vp.chVue.getText());
				i++;
			}
			if (vp.chPHP.isSelected() == true) {
				p[y].setLenguaje(i, vp.chPHP.getText());
				i++;
			}
		}
		
		// Metodo auxiliar de conversión de lenguajes para cargarlos en pantalla
		void mostrarLenguajes(int y) {
			for (int i=0; i<6; i++) {
				if (p[y].getLenguaje(i) == vp.chAngular.getText()) {
					vp.chAngular.setSelected(true);
				}
				if (p[y].getLenguaje(i) == vp.chJava.getText()) {
					vp.chJava.setSelected(true);
				}
				if (p[y].getLenguaje(i) == vp.chReact.getText()) {
					vp.chReact.setSelected(true);
				}
				if (p[y].getLenguaje(i) == vp.chVue.getText()) {
					vp.chVue.setSelected(true);
				}
				if (p[y].getLenguaje(i) == vp.chPHP.getText()) {
					vp.chPHP.setSelected(true);
				}
			}
		}
		*/
	
}

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Proyecto;
import modelo.Prueba;
import modelo.PruebaDAO;
import modelo.Version;
import vista.Vprueba;

public class CPrueba{

	Prueba pru = new Prueba();
	Vprueba vpru = new Vprueba();
	PruebaDAO prudao = new PruebaDAO();
	String x;
	private java.text.DecimalFormat formato = new java.text.DecimalFormat("0.00");
	private java.text.DecimalFormat formateador = new java.text.DecimalFormat("000000");
	

	public CPrueba(Prueba pru, Version ver, Proyecto p) {
		super();
		vpru = new Vprueba();
		this.vpru.addRegistrarPruListener(new registrarPrueba()); 
		this.vpru.addGuardarPruListener(new actualizarPrueba());
		this.vpru.addBuscarPruListener(new buscarValor());
		this.vpru.addCalcularValoresListener(new calcularValores());
		this.vpru.addActualizarComboListener(new actualizarCombo());
	}
	
	public void initPrueba()
	{
		vpru.setVisible(true);
		vpru.limpiar();
		rellenarComboProyecto();

		vpru.textIDPrueba.setText(formateador.format(prudao.inicialPrueba()));
		vpru.btnConsultar.setVisible(false);
		vpru.btnRegistrar.setEnabled(true);
		vpru.btnGuardar.setEnabled(false);
		vpru.textIDPrueba.setEditable(false);
		vpru.textCasosP.setEditable(true);
		vpru.textDescripcion.setEditable(true);
		vpru.textFallidos.setEditable(true);
		vpru.textDescripcion.setEditable(true);
		vpru.comboProyecto.setVisible(true);
		vpru.comboVersion.setVisible(true);
		vpru.comboTipo.setEnabled(true);
		vpru.lblProyecto.setVisible(true);
		vpru.lblVersion.setVisible(true);
		vpru.lblAcumulada.setVisible(true);
		vpru.lblFVE.setVisible(true);
		vpru.textEVersion.setVisible(true);
	}
	
	public void initConsultar() {
		vpru.setVisible(true);
		vpru.limpiar();
		rellenarComboProyecto();

		vpru.btnConsultar.setVisible(true);
		vpru.btnRegistrar.setEnabled(false);
		vpru.btnGuardar.setEnabled(true);
		vpru.textIDPrueba.setEditable(true);
		vpru.textCasosP.setEditable(true);
		vpru.textDescripcion.setEditable(true);
		vpru.textFallidos.setEditable(true);
		vpru.textDescripcion.setEditable(true);;
		vpru.comboProyecto.setVisible(false);
		vpru.comboVersion.setVisible(false);
		vpru.comboTipo.setEnabled(true);
		vpru.lblProyecto.setVisible(false);
		vpru.lblVersion.setVisible(false);
		vpru.lblAcumulada.setVisible(false);
		vpru.lblFVE.setVisible(false);
		vpru.textEVersion.setVisible(false);
		
	}

				
	// Registrar un valor en el vector
			class registrarPrueba implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					try {
						//Verificamos si hay campos vacios para poder guardar
						if (vpru.textDescripcion.getText().isEmpty() || vpru.textCasosP.getText().isEmpty() || vpru.textTimeResponse.getText().isEmpty() || vpru.textFallidos.getText().isEmpty() || vpru.textRapidez.getText().isEmpty() || vpru.textETest.getText().isEmpty() || vpru.textEVersion.getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
						else {
									int y = vpru.comboProyecto.getSelectedIndex(); // Esta es la ubicación en memoria del proyecto que se evalúa
									String z = (String) vpru.comboVersion.getSelectedItem(); // Esta es la ubicación en memoria de la versión que se evalúa
									Version ver = prudao.traerVersion(y, z);
									Proyecto p = prudao.traerProyecto();
									
									//Se almacenan los datos propios ingresados a memoria
									pru.setDescripcion(vpru.textDescripcion.getText());
									pru.setTipoprueba(vpru.comboTipo.getSelectedIndex());
									pru.setCantCasosPrueba(Integer.valueOf(vpru.textCasosP.getText()));
									pru.setCasosFallidos(Double.valueOf(vpru.textFallidos.getText()));
									pru.setTimeResponse(Double.valueOf(vpru.textTimeResponse.getText()));
									pru.setRapidez(Double.valueOf(vpru.textRapidez.getText()));
									pru.setEfiPrueba(Double.valueOf(vpru.textETest.getText()));
									
									//Se actualizan acumuladores
									int tcasos = Integer.valueOf(vpru.textCasosP.getText());
									int casos = ver.getAcumCasos();
									ver.setAcumCasos(casos + tcasos);
									
									Double tfail = Double.valueOf(vpru.textFallidos.getText());
									Double fail = ver.getAcumFail();
									ver.setAcumFail(tfail + fail);
									
									Double ttime = Double.valueOf(vpru.textTimeResponse.getText());
									Double time = ver.getAcumTime();
									ver.setAcumTime(time + ttime);
									
									int pruebas = ver.getContPruebas();
									ver.setContPruebas(pruebas+1);
									
									Double aeft = Double.valueOf(vpru.textETest.getText());
									Double aefv = ver.getEfiVersion();
									ver.setEfiVersion(aeft + aefv);
									
									Double aefp = p.getEfectividad();
									p.setEfectividad(aeft + aefp);
	
									
									//Se insertan valores en la base de datos
									int pos = ver.getIdversion();
									int ubi = p.getCod();
					
									int rv = prudao.actualizarVersion(ver, p, pos);
									int rp = prudao.actualizarProyecto(p, ubi);
									int rpru = prudao.insertarPrueba(pru, ver);
									
									
								
									if (rpru==1 && rv==1 && rp ==1) {
										JOptionPane.showMessageDialog(null, "Análisis registrado con éxito");
										vpru.limpiar();
										vpru.textIDPrueba.setText(formateador.format(prudao.inicialPrueba()));
									}	
							} //end else
					} catch (Exception error) {
						error.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
					}
				} //end void
			} //end class
			
			// Clase para calcular valores númericos que se mostrarán en la tabla
			class calcularValores implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					int y = vpru.comboProyecto.getSelectedIndex(); // Esta es la ubicación en memoria del proyecto que se evalúa
					String z = String.valueOf(vpru.comboVersion.getSelectedItem()); // Esta es la ubicación en memoria de la versión que se evalúa
					Version ver = prudao.traerVersion(y, z);
					Double efiVer = 0.0;
					Double efiTest = 0.0;
					
					//Variables auxiliares
					int casos = Integer.valueOf(vpru.textCasosP.getText()); // Los casos de prueba evaluados colocados en pantalla
					Double time = Double.valueOf(vpru.textTimeResponse.getText()); // El tiempo de respuesta evaluado colocado en pantalla
					Double fail = Double.valueOf(vpru.textFallidos.getText()); // El tiempo de respuesta evaluado colocado en pantalla
					
					//Variables de calculo
					Double irapidez = pru.calcularIndrapidez(casos, time); // Calculamos el indice de rápidez
					efiTest = pru.calcularEfiPrueba(irapidez, fail); // Calculamos la eficiencia del Test
					
					if (ver.getContPruebas() != 0) {
						efiVer = ver.calcularEfVersion(ver.getEfiVersion(), ver.getContPruebas());
					}
					else
						efiVer = 0.0;		
					//Mostramos en pantalla
					vpru.textRapidez.setText(formato.format(irapidez));
					vpru.textETest.setText(formato.format(efiTest));
					vpru.textEVersion.setText(formato.format(efiVer));
				}
			}
			
			class buscarValor implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					try {
							
						int dato = Integer.valueOf(vpru.textIDPrueba.getText());
						Prueba pru = prudao.buscarPrueba(dato);
						Version ver = new Version();
								vpru.textDescripcion.setText(pru.getDescripcion());
								vpru.comboTipo.setSelectedIndex(pru.getTipoprueba());
								vpru.textCasosP.setText(String.valueOf(pru.getCantCasosPrueba()));
								vpru.textFallidos.setText(String.valueOf(pru.getCasosFallidos()));
								vpru.textTimeResponse.setText(String.valueOf(pru.getTimeResponse()));
								vpru.textRapidez.setText(String.valueOf(pru.getRapidez()));
								vpru.textETest.setText(String.valueOf(pru.getEfiPrueba()));
								vpru.textEVersion.setText(String.valueOf(ver.getEfiVersion()));
							
					} catch (Exception error) {
						error.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
					}
				}
			}
			
			class actualizarPrueba implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					try {
						//Verificamos si hay campos vacios para poder guardar
						if (vpru.textDescripcion.getText().isEmpty() || vpru.textCasosP.getText().isEmpty() || vpru.textTimeResponse.getText().isEmpty() || vpru.textFallidos.getText().isEmpty() || vpru.textRapidez.getText().isEmpty() || vpru.textETest.getText().isEmpty() || vpru.textEVersion.getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
						else {	
								pru.setCodPrueba(Integer.valueOf(vpru.textIDPrueba.getText()));
								pru.setDescripcion(vpru.textDescripcion.getText());
								pru.setTipoprueba(vpru.comboTipo.getSelectedIndex());
								pru.setCantCasosPrueba(Integer.valueOf(vpru.textCasosP.getText()));
								pru.setCasosFallidos(Double.valueOf(vpru.textFallidos.getText()));
								pru.setTimeResponse(Double.valueOf(vpru.textTimeResponse.getText()));
								pru.setRapidez(Double.valueOf(vpru.textRapidez.getText()));
								pru.setEfiPrueba(Double.valueOf(vpru.textETest.getText()));
								
								int ubi = pru.getCodPrueba();
								int rp = prudao.actualizarPrueba(pru, ubi);
								
								//Version ver = prudao.cualVersion();
								//Proyecto p = prudao.cualProyecto();
								
								if (rp ==1) {
									vpru.limpiar(); 
									JOptionPane.showMessageDialog(null, "Análisis QA actualizado con éxito");
								}	

								
							} //end else
					} catch (Exception error) {
						error.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error, consulte a Soporte Técnico");
					}
				}
			}
			
					
			class actualizarCombo implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					rellenarComboVersion();
				}
			}
				
			// Se cargan al abrir la ventana
			public void rellenarComboProyecto() {

				vpru.comboProyecto.removeAllItems();
				ArrayList<String> lista = new ArrayList<String>();
				lista = prudao.comboProyecto();
				
				vpru.comboProyecto.addItem("Seleccione");
				for (int i = 0; i< lista.size(); i++) {
					x = lista.get(i);
					vpru.comboProyecto.addItem(x);
				}
			}
			
			// Se cargan al seleccionar una opción en el combo Proyecto
			public void rellenarComboVersion() {
				int item = vpru.comboProyecto.getSelectedIndex();
				vpru.comboVersion.removeAllItems();
				
				ArrayList<String> lista = new ArrayList<String>();
				
				lista = prudao.comboVersion(item);
				
				vpru.comboVersion.addItem("Seleccione");
				for (int i = 0; i< lista.size(); i++) {
					x = lista.get(i);
					vpru.comboVersion.addItem(x);
				}
			}


}

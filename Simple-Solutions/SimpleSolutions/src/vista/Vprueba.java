package vista;

	import java.awt.BorderLayout;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

import controlador.CPrueba;
import modelo.PruebaDAO;

import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;
	import javax.swing.KeyStroke;
	import javax.swing.JSeparator;
	import javax.swing.JComboBox;
	import java.awt.event.ActionListener;
	import java.awt.event.InputEvent;
	import java.awt.event.KeyEvent;
	import java.awt.event.WindowAdapter;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;
	import java.net.URI;
	import java.awt.event.ActionEvent;
	import java.awt.Color;
	import java.awt.Desktop;
	import java.awt.EventQueue;

	import javax.swing.DefaultComboBoxModel;
	import java.awt.event.KeyAdapter;
	import javax.swing.JMenuBar;
	import javax.swing.JMenu;
	import javax.swing.JMenuItem;

	public class Vprueba extends JFrame { //aqui

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		public JTextField textIDPrueba;
		public JTextField textDescripcion;
		public JTextField textFallidos;
		public JTextField textTimeResponse;
		public JTextField textRapidez;
		public JTextField textCasosP;
		public JButton btnConsultar;
		public JComboBox<String> comboProyecto;
		public JComboBox<String> comboTipo;
		public JComboBox<String> comboVersion;
		public JButton btnGuardar;
		public JLabel lblStockDisp;
		public String model = "";
		public String mark = "";
		public String prov= "";
		private JMenuItem mntmAnalisisQA;
		private JMenuItem mntmConsultar;
		public JButton btnRegistrar;
		public JTextField textEVersion;
		public JTextField textETest;
		public JButton btnCalcular;
		public JLabel lblProyecto;
		public JLabel lblVersion;
		private java.text.DecimalFormat formateador = new java.text.DecimalFormat("000000");
		public JLabel lblFVE;
		public JLabel lblAcumulada;

		/**
		 * Launch the application.
		 */
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Vprueba frame = new Vprueba();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		

		/**
		 * Create the dialog.
		 */
		public Vprueba() {
			
			//Instrucciones para la construcción de la vista
			setTitle("An\u00E1lisis QA");
			setBounds(100, 100, 738, 461);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			
			btnCalcular = new JButton("Calcular");
			btnCalcular.setBounds(573, 206, 102, 48);
			contentPanel.add(btnCalcular);
			
			JLabel lblNewLabel = new JLabel("ID Prueba:");
			lblNewLabel.setBounds(36, 44, 82, 14);
			contentPanel.add(lblNewLabel);
			
			textIDPrueba = new JTextField();
			textIDPrueba.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoNumerico(e);
				}
			});
			textIDPrueba.setEditable(false);
			textIDPrueba.setBounds(150, 41, 55, 20);
			contentPanel.add(textIDPrueba);
			textIDPrueba.setColumns(10);
			
			btnConsultar = new JButton("Buscar");
			btnConsultar.setBounds(210, 41, 75, 20);
			contentPanel.add(btnConsultar);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(36, 95, 639, 14);
			contentPanel.add(separator);
			
			comboProyecto = new JComboBox<>();
			comboProyecto.setEditable(true);
			comboProyecto.setBounds(450, 16, 129, 22);
			contentPanel.add(comboProyecto);
			
			lblProyecto = new JLabel("Proyecto:");
			lblProyecto.setBounds(325, 20, 86, 14);
			contentPanel.add(lblProyecto);
			
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n: ");
			lblNewLabel_2.setBounds(36, 125, 82, 14);
			contentPanel.add(lblNewLabel_2);
			
			textDescripcion = new JTextField();
			textDescripcion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoTexto(e);
				}
			});
			textDescripcion.setColumns(10);
			textDescripcion.setBounds(150, 122, 140, 20);
			contentPanel.add(textDescripcion);
			
			JLabel lblNewLabel_3 = new JLabel("Casos de pruebas:");
			lblNewLabel_3.setBounds(36, 176, 114, 14);
			contentPanel.add(lblNewLabel_3);
			
			textFallidos = new JTextField();
			textFallidos.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoNumerico(e);
				}
			});
			textFallidos.setBounds(450, 173, 55, 20);
			contentPanel.add(textFallidos);
			textFallidos.setColumns(10);
			
			lblStockDisp = new JLabel("Tiempo respuesta:");
			lblStockDisp.setBounds(36, 223, 114, 14);
			contentPanel.add(lblStockDisp);
			
			textTimeResponse = new JTextField();
			textTimeResponse.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoNumerico(e);
				}
			});
			textTimeResponse.setBounds(150, 220, 55, 20);
			contentPanel.add(textTimeResponse);
			textTimeResponse.setColumns(10);
			
			lblVersion = new JLabel("Versi\u00F3n:");
			lblVersion.setBounds(325, 65, 69, 14);
			contentPanel.add(lblVersion);
			
			comboVersion = new JComboBox<>();
			comboVersion.setEditable(true);
			comboVersion.setBounds(450, 61, 129, 22);
			contentPanel.add(comboVersion);
			
			JLabel lblNewLabel_6 = new JLabel("Tipo:");
			lblNewLabel_6.setBounds(325, 125, 46, 14);
			contentPanel.add(lblNewLabel_6);
			
			comboTipo = new JComboBox<>();
			comboTipo.setEditable(true);
			comboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"0- Funcionales", "1- No funcionales", "2- Estructurales", "3- Regresi\u00F3n"}));
			comboTipo.setBounds(450, 121, 129, 22);
			contentPanel.add(comboTipo);
			
			JLabel lblNewLabel_7 = new JLabel("Test Speed Index (TSI):");
			lblNewLabel_7.setBounds(307, 223, 144, 14);
			contentPanel.add(lblNewLabel_7);
			
			textRapidez = new JTextField();
			textRapidez.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoNumerico(e);
				}
			});
			
			textRapidez.setEditable(false);
			textRapidez.setBounds(450, 220, 55, 20);
			contentPanel.add(textRapidez);
			textRapidez.setColumns(10);
			
			JLabel lblNewLabel_8 = new JLabel("Fallidos (%)");
			lblNewLabel_8.setBounds(325, 176, 94, 14);
			contentPanel.add(lblNewLabel_8);
			
			textCasosP = new JTextField();
			textCasosP.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoNumerico(e);
				}
			});
			textCasosP.setColumns(10);
			textCasosP.setBounds(150, 173, 55, 20);
			contentPanel.add(textCasosP);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(36, 316, 639, 2);
			contentPanel.add(separator_1);
			
			btnGuardar = new JButton("Guardar");
			btnGuardar.setBounds(290, 356, 104, 29);
			contentPanel.add(btnGuardar);
			
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setBounds(469, 356, 104, 29);
			contentPanel.add(btnLimpiar);
			
			JLabel lblNewLabel_9 = new JLabel("Datos de Testing");
			lblNewLabel_9.setForeground(Color.BLUE);
			lblNewLabel_9.setBounds(36, 20, 249, 14);
			contentPanel.add(lblNewLabel_9);
			
			JLabel lblNewLabel_10 = new JLabel("Datos de evaluaci\u00F3n:");
			lblNewLabel_10.setForeground(Color.BLUE);
			lblNewLabel_10.setBounds(36, 100, 196, 14);
			contentPanel.add(lblNewLabel_10);
			
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.setBounds(106, 356, 104, 29);
			contentPanel.add(btnRegistrar);
			
			JLabel lblNewLabel_4 = new JLabel("Full Test Efficiency (FTE):");
			lblNewLabel_4.setBounds(10, 276, 140, 14);
			contentPanel.add(lblNewLabel_4);
			
			textETest = new JTextField();
			textETest.setEditable(false);
			textETest.setBounds(150, 273, 55, 20);
			contentPanel.add(textETest);
			textETest.setColumns(10);
			
			textEVersion = new JTextField();
			textEVersion.setEditable(false);
			textEVersion.setBounds(450, 273, 55, 20);
			contentPanel.add(textEVersion);
			textEVersion.setColumns(10);
			
			lblFVE = new JLabel("Full Version Effiency (FVE):");
			lblFVE.setBounds(296, 276, 155, 14);
			contentPanel.add(lblFVE);
			
			lblAcumulada = new JLabel("Acumulada");
			lblAcumulada.setForeground(Color.BLUE);
			lblAcumulada.setBounds(325, 291, 75, 14);
			contentPanel.add(lblAcumulada);
			
			
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnNewMenu = new JMenu("Archivo");
			menuBar.add(mnNewMenu);
			
			JMenuItem mntmSalir = new JMenuItem("Cerrar todo");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mnNewMenu.add(mntmSalir);
			
			JMenu mnNewMenu_1 = new JMenu("Herramientas");
			menuBar.add(mnNewMenu_1);
			
			//Metodo auxiliar para hacer conversión de método en comboBox
			mntmAnalisisQA = new JMenuItem("An\u00E1lisis QA");
			mntmAnalisisQA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiar();
					PruebaDAO prudao = new PruebaDAO();

					textIDPrueba.setText(formateador.format(prudao.inicialPrueba()));
					btnConsultar.setVisible(false);
					btnRegistrar.setEnabled(true);
					btnGuardar.setEnabled(false);
					textIDPrueba.setEditable(false);
					textCasosP.setEditable(true);
					textDescripcion.setEditable(true);
					textFallidos.setEditable(true);
					textDescripcion.setEditable(true);
					comboProyecto.setVisible(true);
					comboVersion.setVisible(true);
					comboTipo.setEnabled(true);
					lblProyecto.setVisible(true);
					lblVersion.setVisible(true);
					lblAcumulada.setVisible(true);
					lblFVE.setVisible(true);
					textEVersion.setVisible(true);
				}
			});
			mnNewMenu_1.add(mntmAnalisisQA);
			
			//Metodos que manipula los botones de la vista de acuerdo a condiciones
			mntmConsultar = new JMenuItem("Consultar");
			mntmConsultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiar();
					btnConsultar.setVisible(true);
					btnRegistrar.setEnabled(false);
					btnGuardar.setEnabled(true);
					textIDPrueba.setEditable(true);
					textCasosP.setEditable(true);
					textDescripcion.setEditable(true);
					textFallidos.setEditable(true);
					textDescripcion.setEditable(true);;
					comboProyecto.setVisible(false);
					comboVersion.setVisible(false);
					comboTipo.setEnabled(true);
					lblProyecto.setVisible(false);
					lblVersion.setVisible(false);
					lblAcumulada.setVisible(false);
					lblFVE.setVisible(false);
					textEVersion.setVisible(false);
				}
			});
			mnNewMenu_1.add(mntmConsultar);
			
			JMenu mnNewMenu_2 = new JMenu("Ayuda");
			menuBar.add(mnNewMenu_2);
			
			JMenuItem mntmEmpresa = new JMenuItem("Empresa");
			mntmEmpresa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Desktop.getDesktop().browse(new URI ("https://simplesolutions.com.co/"));
						}
						catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "No se puede ejecutar");
						}
				}
			});
			mnNewMenu_2.add(mntmEmpresa);
			
			JMenuItem mntmSistema = new JMenuItem("Sistema");
			mntmSistema.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "En construcción");
				}
			});
					
			mnNewMenu_2.add(mntmSistema);
			
			//Atajos de teclado
			mntmAnalisisQA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
			mntmConsultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
			
			addWindowListener((WindowListener) new WindowAdapter(){
	            public void windowClosing(WindowEvent evt){
	                int x = JOptionPane.showConfirmDialog(null, 
	                    "¿Estás seguro de salir?", "Confirmar !",
	                    JOptionPane.YES_NO_OPTION);

	                if(x == JOptionPane.YES_OPTION) {
	                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                }else{
	                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	                }
	            }
	        });
		}
		
		//Metodos listener de botones para el Controlador
		
		public void addGuardarPruListener(ActionListener gl){
			btnGuardar.addActionListener(gl);
		}
		public void addBuscarPruListener(ActionListener bl) {
			btnConsultar.addActionListener(bl);
		}
		public void addRegistrarPruListener(ActionListener rl) {
			btnRegistrar.addActionListener(rl);
		}
		public void addActualizarComboListener(ActionListener ac) {
			comboProyecto.addActionListener(ac);
		}
		public void addCalcularValoresListener(ActionListener cv) {
			btnCalcular.addActionListener(cv);
		}
		

			//Utilidades
			public void validarCampoNumerico(KeyEvent k) {
			char validar = k.getKeyChar();
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					k.consume();
				}
		}
			public void validarCampoTexto(KeyEvent k) {
			char validar = k.getKeyChar();
				if (Character.isDigit(validar)) {
					getToolkit().beep();
					k.consume();
				}
		}
		
		
		public void limpiar() {
			textIDPrueba.setText("");
			textDescripcion.setText("");
			textFallidos.setText("");
			textTimeResponse.setText("");
			textRapidez.setText("");
			textCasosP.setText("");
			textEVersion.setText("");
			textETest.setText("");
			CPrueba cpru = new CPrueba(null, null, null);
			cpru.rellenarComboProyecto();
			//comboVersion.setSelectedIndex(-1);
			comboTipo.setSelectedIndex(0);
			
		}
	}

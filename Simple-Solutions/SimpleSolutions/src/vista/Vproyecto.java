package vista;

	import java.awt.BorderLayout;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

import modelo.ProyectoDAO;

import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JSeparator;
	import javax.swing.JTextField;
	import javax.swing.KeyStroke;
	import javax.swing.JComboBox;
	import javax.swing.DefaultComboBoxModel;
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
	import java.awt.event.KeyAdapter;
	import javax.swing.JMenuBar;
	import javax.swing.JMenu;
	import javax.swing.JMenuItem;
import javax.swing.JCheckBox;

	public class Vproyecto extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		public JTextField textID;
		public JTextField textNombre;
		public JTextField textAvance;
		public JButton btnRegistrar;
		public JButton btnBuscar;
		public JButton btnLimpiar;
		public int cantEdad=0;
		public String sex= "";
		public String city= "";
		public JComboBox<String> comboEstado;
		public JMenuItem mntmRegistrar;
		public JTextField textVersion;
		public JComboBox<String> comboDuracion;
		public JTextField textEfec;
		public JMenuItem mntmActualizar;
		public JCheckBox chAngular;
		public JCheckBox chReact;
		public JCheckBox chVue;
		public JCheckBox chJava;
		public JCheckBox chPython;
		public JCheckBox chPHP;
		public JButton btnActualizar;
		public JLabel lblVersion;
		private java.text.DecimalFormat formateador = new java.text.DecimalFormat("000000");

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Vproyecto frame = new Vproyecto();
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
		public Vproyecto() {
			
			//Componentes de la vista
			setTitle("Proyecto");
			setBounds(100, 100, 743, 447);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("ID Proyecto:");
				lblNewLabel.setBounds(25, 34, 76, 14);
				contentPanel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setBounds(25, 105, 76, 14);
				contentPanel.add(lblNewLabel_1);
			}
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(25, 59, 635, 14);
				contentPanel.add(separator);
			}
			{
				textID = new JTextField();
				textID.setEditable(false);
				textID.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						validarCampoNumerico(e);
						if (textID.getText().length() == 10)  
				             e.consume();
					}
				});
				textID.setBounds(111, 31, 64, 20);
				contentPanel.add(textID);
				textID.setColumns(10);
			}
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnBuscar.setBounds(187, 27, 103, 29);
				contentPanel.add(btnBuscar);
			}
			{
				textNombre = new JTextField();
				textNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						validarCampoTexto(e);
						if (textNombre.getText().length() == 20)  
				             e.consume();
					}
				});
				textNombre.setBounds(111, 102, 210, 20);
				contentPanel.add(textNombre);
				textNombre.setColumns(10);
			}
			
			lblVersion = new JLabel("Versi\u00F3n:");
			lblVersion.setBounds(25, 149, 89, 14);
			contentPanel.add(lblVersion);
			
			JLabel lblNewLabel_3 = new JLabel("Duraci\u00F3n:");
			lblNewLabel_3.setBounds(25, 195, 76, 14);
			contentPanel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_5 = new JLabel("Avance (%):");
			lblNewLabel_5.setBounds(25, 280, 76, 14);
			contentPanel.add(lblNewLabel_5);
			
			textAvance = new JTextField();
			textAvance.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validarCampoNumerico(e);
					if (textAvance.getText().length() == 3)  
			             e.consume();
				}
			});
			textAvance.setBounds(111, 277, 64, 20);
			contentPanel.add(textAvance);
			textAvance.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Estado:");
			lblNewLabel_6.setBounds(319, 195, 57, 14);
			contentPanel.add(lblNewLabel_6);
			
			comboEstado = new JComboBox<>();
			comboEstado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			comboEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"0-Planificaci\u00F3n", "1-Ejecuci\u00F3n", "2-Pruebas", "3-Finalizaci\u00F3n"}));
			comboEstado.setBounds(406, 191, 146, 22);
			contentPanel.add(comboEstado);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(25, 320, 635, 14);
			contentPanel.add(separator);
			
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnRegistrar.setBounds(111, 345, 103, 29);
			contentPanel.add(btnRegistrar);
			
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiar();
				}
			});
			btnLimpiar.setBounds(431, 345, 103, 29);
			contentPanel.add(btnLimpiar);
			
			JLabel lblNewLabel_7 = new JLabel("Datos del Proyecto:");
			lblNewLabel_7.setForeground(Color.BLUE);
			lblNewLabel_7.setBounds(25, 69, 210, 14);
			contentPanel.add(lblNewLabel_7);
			
			textVersion = new JTextField();
			textVersion.setBounds(111, 146, 64, 20);
			contentPanel.add(textVersion);
			textVersion.setColumns(10);
			
			chJava = new JCheckBox("Java");
			chJava.setBounds(563, 101, 97, 23);
			contentPanel.add(chJava);
			
			chAngular = new JCheckBox("Angular");
			chAngular.setBounds(455, 101, 97, 23);
			contentPanel.add(chAngular);
			
			chPython = new JCheckBox("Python");
			chPython.setBounds(563, 127, 97, 23);
			contentPanel.add(chPython);
			
			chReact = new JCheckBox("React");
			chReact.setBounds(455, 127, 97, 23);
			contentPanel.add(chReact);
			
			chPHP = new JCheckBox("PHP");
			chPHP.setBounds(563, 152, 97, 23);
			contentPanel.add(chPHP);
			
			chVue = new JCheckBox("Vue");
			chVue.setBounds(455, 152, 97, 23);
			contentPanel.add(chVue);
			
			JLabel lblNewLabel_8 = new JLabel("Lenguajes Implementados");
			lblNewLabel_8.setBounds(467, 69, 167, 14);
			contentPanel.add(lblNewLabel_8);
			
			comboDuracion = new JComboBox<>();
			comboDuracion.setModel(new DefaultComboBoxModel<String>(new String[] {"0 Semanas", "1 Semanas", "2 Semanas", "3 Semanas", "4 Semanas", "5 Semanas", "6 Semanas", "7 Semanas", "8 Semanas", "9 Semanas", "10 Semanas", "11 Semanas", "12 Semanas", "13 Semanas", "14 Semanas", "15 Semanas", "16 Semanas", "17 Semanas", "18 Semanas", "19 Semanas", "20 Semanas", "21 Semanas", "22 Semanas", "23 Semanas", "24 Semanas"}));
			comboDuracion.setBounds(111, 191, 146, 22);
			contentPanel.add(comboDuracion);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(25, 248, 635, 14);
			contentPanel.add(separator_1);
			
			JLabel lblNewLabel_4 = new JLabel("Efectividad Hist\u00F3rica (%):");
			lblNewLabel_4.setBounds(319, 280, 139, 14);
			contentPanel.add(lblNewLabel_4);
			
			textEfec = new JTextField();
			textEfec.setEditable(false);
			textEfec.setBounds(480, 277, 86, 20);
			contentPanel.add(textEfec);
			textEfec.setColumns(10);
			
			btnActualizar = new JButton("Actualizar");
			btnActualizar.setBounds(272, 345, 103, 29);
			contentPanel.add(btnActualizar);
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnArchivo = new JMenu("Archivo");
			menuBar.add(mnArchivo);
			
			JMenuItem mntmSalir = new JMenuItem("Cerrar todo");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mnArchivo.add(mntmSalir);
			
			JMenu mnHerramientas = new JMenu("Herramientas");
			menuBar.add(mnHerramientas);
			//Metodo auxiliar para hacer conversión de método en comboBox
			mntmRegistrar = new JMenuItem("Registrar");
			mntmRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProyectoDAO pdao = new ProyectoDAO();
					
					textID.setText(formateador.format(pdao.inicial()));
					btnBuscar.setVisible(false);
					btnRegistrar.setEnabled(true);
					btnActualizar.setEnabled(false);
					textNombre.setEditable(true);
					textAvance.setText("00");
					textAvance.setEditable(true);
					textEfec.setText("0.00");
					comboEstado.setEditable(false);
					comboDuracion.setEditable(true);
					btnLimpiar.setVisible(true);
					textVersion.setText("v1.0");
					textID.setEditable(false);
					lblVersion.setText("Versión");
					limpiar();
				}
			});
			mnHerramientas.add(mntmRegistrar);
			
			JMenu mnNewMenu_1 = new JMenu("Ayuda");
			menuBar.add(mnNewMenu_1);
			
			JMenuItem mntmNewMenuItem_2 = new JMenuItem("Empresa");
			mntmNewMenuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Desktop.getDesktop().browse(new URI ("https://simplesolutions.com.co/"));
						}
						catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "No se puede ejecutar");
						}
				}
			});
			mnNewMenu_1.add(mntmNewMenuItem_2);
			
			JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sistema");
			mntmNewMenuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "En construcción");
				}
			});
			mnNewMenu_1.add(mntmNewMenuItem_3);
			
			mntmActualizar = new JMenuItem("Actualizar");
			mntmActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnBuscar.setVisible(true);
					btnRegistrar.setEnabled(false);
					btnActualizar.setEnabled(true);
					btnLimpiar.setVisible(true);
					textNombre.setEditable(true);
					textAvance.setEditable(true);
					comboEstado.setEditable(true);
					comboDuracion.setEditable(true);
					textID.setEditable(true);
					lblVersion.setText("Ult. Versión");	
					limpiar();
				}				
			});
			mnHerramientas.add(mntmActualizar);
			
			mntmRegistrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
			mntmActualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
			
			
			
			addWindowListener((WindowListener) new WindowAdapter(){
	            public void windowClosing(WindowEvent evt){
	                int x = JOptionPane.showConfirmDialog(null, 
	                    "¿Estás seguro de salir?", "Confirmar !",
	                    JOptionPane.YES_NO_OPTION);

	                if(x == JOptionPane.YES_OPTION) {
	                	limpiar();
	                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                }else{
	                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	                }
	            }
	        });		 
		}
		
		//Metodos para el listener de los botones
		public void addRegistrarListener(ActionListener r){
			btnRegistrar.addActionListener(r);
		}
		public void addBuscarListener(ActionListener b) {
			btnBuscar.addActionListener(b);
		}
		public void addActualizarListener(ActionListener a){
			btnActualizar.addActionListener(a);
		}
			
		//Auxiliares para los JComboBox	
		void determinarEdad(int index) {
			cantEdad = index+15;
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
			textID.setText("");
			textNombre.setText("");
			textAvance.setText("");
			textVersion.setText("");
			textEfec.setText("");
			comboEstado.setSelectedIndex(0);
			comboDuracion.setSelectedIndex(0);
			chPHP.setSelected(false);
			chAngular.setSelected(false);
			chJava.setSelected(false);
			chVue.setSelected(false);
			chPython.setSelected(false);
			chReact.setSelected(false);
		}
	}

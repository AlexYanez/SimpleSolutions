package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.net.URI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Vprincipal extends JFrame {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	public JMenuItem mntmSalir;
	public JMenu mnNewMenu;
	public JMenuItem mntmEmpresa;
	public Vfondo contentPane;
	public JMenuItem mntmRegistrar;
	public JMenuItem mntmActualizar;
	public JMenuItem mntmAnalisisQA;
	public JMenuItem mntmConsultar;
	public JMenuItem mntmGenerales;
	public JMenuItem mntmEspecificos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vprincipal frame = new Vprincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vprincipal() {
		setBackground(Color.BLACK);
		//Instrucciones de componentes de la vista
		setTitle("Simple Solutions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 449);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnProyecto = new JMenu("Proyecto");
		mnProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			}
		});
		menuBar.add(mnProyecto);
		
		
		mntmRegistrar = new JMenuItem("Registrar");
		mnProyecto.add(mntmRegistrar);
		
		JMenu mnPruebas = new JMenu("Pruebas");
		menuBar.add(mnPruebas);
		

		mntmAnalisisQA = new JMenuItem("An\u00E1lisis QA");
		mnPruebas.add(mntmAnalisisQA);
		

		mntmConsultar = new JMenuItem("Consultar");
		mnPruebas.add(mntmConsultar);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mntmGenerales = new JMenuItem("Generales");
		mntmGenerales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "En construcción");
			}
		});
		mnReportes.add(mntmGenerales);
		
		mntmEspecificos = new JMenuItem("Espec\u00EDficos");
		mnReportes.add(mntmEspecificos);
		
		mnNewMenu = new JMenu("Ayuda");
		menuBar.add(mnNewMenu);
		
		mntmEmpresa = new JMenuItem("Empresa");
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
		mnNewMenu.add(mntmEmpresa);
		
		JMenuItem mntmSistema = new JMenuItem("Sistema");
		mntmSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "En construcción");
			}
		});
		mnNewMenu.add(mntmSistema);
		contentPane = new Vfondo("qa.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		mntmActualizar = new JMenuItem("Actualizar");
		mnProyecto.add(mntmActualizar);
		
		//Atajos de teclado
		mntmRegistrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mntmActualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mntmAnalisisQA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		mntmConsultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
				
		//Cerrar ventana
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
	
	public void addRegistrarListener(ActionListener gl){
		mntmRegistrar.addActionListener(gl);
	}
	public void addActualizarPruListener(ActionListener bl) {
		mntmActualizar.addActionListener(bl);
	}
	public void addAnalisisQAListener(ActionListener rl) {
		mntmAnalisisQA.addActionListener(rl);
	}
	public void addConsultarListener(ActionListener ac) {
		mntmConsultar.addActionListener(ac);
	}
	public void addReporteEspecificoListener(ActionListener cv) {
		mntmEspecificos.addActionListener(cv);
	}
	

}

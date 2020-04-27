package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import modelo.Proyecto;
import modelo.PruebaDAO;
import modelo.ReporteDAO;
import modelo.Version;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Vreporte extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnMostrar;
	private JComboBox<String> comboVersion;
	private JComboBox<String> comboProyecto;
	private JTextField textFail;
	private JTextField textCasos;
	private JTextField textTime;
	private JTextField textFVE;
	private JTextField textFPE;
	String x, y;
	PruebaDAO prudao = new PruebaDAO();
	private java.text.DecimalFormat formato = new java.text.DecimalFormat("0.00"); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vreporte frame = new Vreporte();
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
	public Vreporte() {
		setTitle("Reporte Estad\u00EDstico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Proyecto: ");
		lblNewLabel.setBounds(204, 11, 72, 14);
		contentPane.add(lblNewLabel);
		
		
		comboProyecto = new JComboBox<>();
		rellenarComboProyecto();
		comboProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rellenarComboVersion();
			}
		});
		
		comboProyecto.setBounds(280, 7, 150, 22);
		contentPane.add(comboProyecto);
		
		
		JLabel lblNewLabel_1 = new JLabel("Versi\u00F3n: ");
		lblNewLabel_1.setBounds(204, 44, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		comboVersion = new JComboBox<>();
		comboVersion.addItem("Seleccione");
		comboVersion.setBounds(280, 40, 150, 22);
		contentPane.add(comboVersion);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteDAO rdao = new ReporteDAO();
				
				int y = comboProyecto.getSelectedIndex(); // Esta es la ubicación en memoria del proyecto que se evalúa
				String x = (String) comboVersion.getSelectedItem(); // Esta es la ubicación en memoria de la versión que se evalúa
				Version ver = rdao.cualVersion(x);
				Proyecto p = rdao.cualProyecto(y);
				int valor = rdao.pruebasPorProyecto(y);
		
				//Colocamos los datos en pantalla en base a los acumuladores para promediar
				if (ver.getContPruebas() != 0 && valor != 0) {
					
					textFail.setText(formato.format(ver.getAcumFail()/ver.getContPruebas()));
					textTime.setText(formato.format(ver.getAcumTime()/ver.getContPruebas()));
					textCasos.setText(formato.format(ver.getAcumCasos()/ver.getContPruebas()));
					textFVE.setText(formato.format(ver.getEfiVersion()/ver.getContPruebas()));
					textFPE.setText(formato.format(p.getEfectividad()/valor));
				}
				else {
					textFail.setText(formato.format(0));
					textTime.setText(formato.format(0));
					textCasos.setText(formato.format(0));
					textFVE.setText(formato.format(0));
					textFPE.setText(formato.format(0));
				}
			}

	
		});
		btnMostrar.setBounds(280, 73, 150, 23);
		contentPane.add(btnMostrar);
		
		JPanel panelChart = new JPanel();
		panelChart.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Estad\u00EDsticas", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelChart.setBackground(Color.GRAY);
		panelChart.setForeground(Color.GRAY);
		panelChart.setBounds(23, 107, 623, 273);
		contentPane.add(panelChart);
		panelChart.setLayout(null);
		
		textFail = new JTextField();
		textFail.setEditable(false);
		textFail.setBounds(57, 84, 86, 20);
		panelChart.add(textFail);
		textFail.setColumns(10);
		
		textCasos = new JTextField();
		textCasos.setEditable(false);
		textCasos.setBounds(271, 84, 86, 20);
		panelChart.add(textCasos);
		textCasos.setColumns(10);
		
		textTime = new JTextField();
		textTime.setEditable(false);
		textTime.setBounds(481, 84, 86, 20);
		panelChart.add(textTime);
		textTime.setColumns(10);
		
		textFVE = new JTextField();
		textFVE.setEditable(false);
		textFVE.setBounds(164, 184, 86, 20);
		panelChart.add(textFVE);
		textFVE.setColumns(10);
		
		textFPE = new JTextField();
		textFPE.setEditable(false);
		textFPE.setBounds(376, 184, 86, 20);
		panelChart.add(textFPE);
		textFPE.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Casos Fallidos");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(62, 58, 98, 14);
		panelChart.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Casos evaluados");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(268, 58, 98, 14);
		panelChart.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tiempo de respuesta");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(461, 58, 145, 14);
		panelChart.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Full Version Eficiency");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(150, 158, 127, 14);
		panelChart.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Full Project Eficiency");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(364, 158, 119, 14);
		panelChart.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Promedio");
		lblNewLabel_7.setForeground(Color.CYAN);
		lblNewLabel_7.setBounds(72, 43, 64, 14);
		panelChart.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Promedio");
		lblNewLabel_7_1.setForeground(Color.CYAN);
		lblNewLabel_7_1.setBounds(285, 43, 72, 14);
		panelChart.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Promedio");
		lblNewLabel_7_1_1.setForeground(Color.CYAN);
		lblNewLabel_7_1_1.setBounds(487, 43, 64, 14);
		panelChart.add(lblNewLabel_7_1_1);
		
		JLabel lblNewLabel_7_1_1_1 = new JLabel("Promedio");
		lblNewLabel_7_1_1_1.setForeground(Color.CYAN);
		lblNewLabel_7_1_1_1.setBounds(178, 143, 64, 14);
		panelChart.add(lblNewLabel_7_1_1_1);
		
		JLabel lblNewLabel_7_1_1_2 = new JLabel("Promedio");
		lblNewLabel_7_1_1_2.setForeground(Color.CYAN);
		lblNewLabel_7_1_1_2.setBounds(388, 143, 64, 14);
		panelChart.add(lblNewLabel_7_1_1_2);
		
		
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
	
		public void rellenarComboProyecto() {
			comboProyecto.removeAllItems();
			ArrayList<String> lista = new ArrayList<String>();
			lista = prudao.comboProyecto();
					
			comboProyecto.addItem("Seleccione");
			for (int i = 0; i< lista.size(); i++) {
				x = lista.get(i);
				comboProyecto.addItem(x);
			}
		}
				
				// Se cargan al seleccionar una opción en el combo Proyecto
		public void rellenarComboVersion() {
			int item = comboProyecto.getSelectedIndex();
			comboVersion.removeAllItems();
					
			ArrayList<String> lista = new ArrayList<String>();
					
			lista = prudao.comboVersion(item);
					
			comboVersion.addItem("Seleccione");
			for (int i = 0; i< lista.size(); i++) {
				x = lista.get(i);
			comboVersion.addItem(x);
			}
		}
}

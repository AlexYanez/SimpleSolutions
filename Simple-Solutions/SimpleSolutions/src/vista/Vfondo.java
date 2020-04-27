package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Vfondo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon fondo;

	/**
	 * Create the panel.
	 */
	public Vfondo(String nombre) {
		super();
		inicializar();
		fondo = new ImageIcon(getClass().getResource(nombre));
		setSize(fondo.getIconWidth(), fondo.getIconHeight());
	}

	public void paintComponent (Graphics g) {
		Dimension d = getSize();
		g.drawImage(fondo.getImage(), 0, 0, d.width, d.height, null);
		this.setOpaque(false);
		super.paintComponent(g);
	}

	public void inicializar() {
		this.setLayout(new GridBagLayout());
	}
	
}

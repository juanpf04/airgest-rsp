
package presentacion.venta;

import java.awt.BorderLayout;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaExitoCerrarVenta extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(488, 430);

		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JLabel exito = new JLabel("Venta cerrada con éxito! id = " + (Integer) datos);
		exito.setFont(new Font("Tahoma", Font.PLAIN, 29));
		exito.setHorizontalAlignment(SwingConstants.CENTER);
		principal.add(exito, BorderLayout.PAGE_START);

		JLabel imagen = new JLabel();
		imagen.setIcon(new ImageIcon("recursos/iconos/exito.png"));
		principal.add(imagen, BorderLayout.CENTER);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() { //LO NUEVO
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
	            Controlador.getInstance().accion(new Contexto(Evento.VISTA_VENTA, null));
	        }
		});
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);

	}
}
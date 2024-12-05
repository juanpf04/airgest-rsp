
package presentacion.departamento;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.Observador;
import presentacion.UtilidadesP;

public class VistaExitoAltaDepartamento extends JFrame implements Observador {

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(480, 430);

		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JLabel exito = new JLabel("Alta de Departamento exitosa! con id = " + (Integer) datos);
		exito.setFont(new Font("Tahoma", Font.PLAIN, 23));
		exito.setHorizontalAlignment(SwingConstants.CENTER);
		principal.add(exito, BorderLayout.PAGE_START);

		JLabel imagen = new JLabel();
		imagen.setIcon(new ImageIcon("recursos/iconos/exito.png"));
		principal.add(imagen, BorderLayout.CENTER);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
	}
}
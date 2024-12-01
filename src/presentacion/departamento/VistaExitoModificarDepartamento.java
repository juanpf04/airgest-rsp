
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
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaExitoModificarDepartamento extends JFrame implements Observador {

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(488, 430);

		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JLabel exito = new JLabel("Modificación de Departamento exitosa!");
		exito.setFont(new Font("Tahoma", Font.PLAIN, 29));
		exito.setHorizontalAlignment(SwingConstants.CENTER);
		principal.add(exito, BorderLayout.PAGE_START);

		JLabel imagen = new JLabel();
		imagen.setIcon(new ImageIcon("recursos/iconos/exito.png"));
		principal.add(imagen, BorderLayout.CENTER);
		//no se si lo de debajo es necesario aqui, en todas o q
		Controlador controlador = Controlador.getInstance();
		controlador.accion(new Contexto(Evento.VISTA_DEPARTAMENTO));

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
	}
}
package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.Observador;

public class VistaExitoBajaProducto extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		// Configuración de la ventana
		this.setSize(488, 430);

		// Panel principal
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		// Etiqueta para mostrar el mensaje de éxito
		JLabel exito = new JLabel("Baja de Producto exitosa!");
		exito.setFont(new Font("Tahoma", Font.PLAIN, 29));
		exito.setHorizontalAlignment(SwingConstants.CENTER);
		principal.add(exito, BorderLayout.PAGE_START);

		// Etiqueta para mostrar la imagen de éxito
		JLabel imagen = new JLabel();
		imagen.setIcon(new ImageIcon("recursos/iconos/exito.png"));
		principal.add(imagen, BorderLayout.CENTER);

		// Configuración final
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
	}
}

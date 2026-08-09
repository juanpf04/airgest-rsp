package presentacion.venta;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import negocio.venta.TCarritoVenta;
import negocio.venta.TLineaVenta;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;


public class VistaAñadirProducto extends JFrame implements Observador {
	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(1000, 750);

		Controlador controlador = Controlador.getInstance();
		TCarritoVenta carrito_venta = (TCarritoVenta) datos;

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel centro = new JPanel();

		JPanel panel_titulo = new JPanel();

		JLabel titulo = new JLabel("Añadir Producto");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);

		JPanel panel_etiquetas = new JPanel();
		panel_etiquetas.setLayout(new BoxLayout(panel_etiquetas, BoxLayout.PAGE_AXIS));

		JPanel panel_textfield = new JPanel();
		panel_textfield.setLayout(new BoxLayout(panel_textfield, BoxLayout.PAGE_AXIS));

		JLabel etiquetaId = new JLabel("id producto: ");
		etiquetaId.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoId = new JTextField();
		textoId.setMaximumSize(new Dimension(200, 30));
		textoId.setMinimumSize(new Dimension(200, 30));
		textoId.setPreferredSize(new Dimension(200, 30));
		textoId.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_etiquetas.add(etiquetaId);
		panel_textfield.add(textoId);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JLabel etiquetaCantidad = new JLabel("cantidad: ");
		etiquetaCantidad.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoCantidad = new JTextField();
		textoCantidad.setMaximumSize(new Dimension(200, 30));
		textoCantidad.setMinimumSize(new Dimension(200, 30));
		textoCantidad.setPreferredSize(new Dimension(200, 30));
		textoCantidad.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_etiquetas.add(etiquetaCantidad);
		panel_textfield.add(textoCantidad);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		principal.add(funcion);
		principal.add(centro);

		JPanel botones = new JPanel();
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id_producto = Integer.parseInt(textoId.getText());
					int cantidad = Integer.parseInt(textoCantidad.getText());
					
					TLineaVenta linea_venta = new TLineaVenta(0, id_producto, cantidad, 0);
					carrito_venta.anyadirLinea(linea_venta);
					controlador.accion(new Contexto(Evento.VISTA_CARRITO_VENTA, carrito_venta));
					dispose();
				} catch (NumberFormatException n) {

				}
			}

		});

		aceptar.setMaximumSize(new Dimension(100, 30));
		aceptar.setPreferredSize(new Dimension(100, 30));

		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				controlador.accion(new Contexto(Evento.VISTA_VENTA, carrito_venta));
			}

		});

		atras.setMaximumSize(new Dimension(90, 30));
		atras.setPreferredSize(new Dimension(90, 30));

		botones.add(atras);
		botones.add(aceptar);
		principal.add(botones);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();
	}
}

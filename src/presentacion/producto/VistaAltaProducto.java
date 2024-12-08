package presentacion.producto;

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

import negocio.producto.TProducto;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaAltaProducto extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel centro = new JPanel();

		JPanel panel_titulo = new JPanel();

		JLabel titulo = new JLabel("Alta Producto");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);

		JPanel panel_etiquetas = new JPanel();
		panel_etiquetas.setLayout(new BoxLayout(panel_etiquetas, BoxLayout.PAGE_AXIS));

		JPanel panel_textfield = new JPanel();
		panel_textfield.setLayout(new BoxLayout(panel_textfield, BoxLayout.PAGE_AXIS));

		JLabel etiquetaNombre = new JLabel("Nombre: ");
		etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoNombre = new JTextField();
		textoNombre.setMaximumSize(new Dimension(200, 30));
		textoNombre.setMinimumSize(new Dimension(200, 30));
		textoNombre.setPreferredSize(new Dimension(200, 30));
		textoNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoNombre.setToolTipText("letras y/o numeros");

		panel_etiquetas.add(etiquetaNombre);
		panel_textfield.add(textoNombre);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JLabel etiquetaStock = new JLabel("Stock: ");
		etiquetaStock.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoStock = new JTextField();
		textoStock.setMaximumSize(new Dimension(200, 30));
		textoStock.setMinimumSize(new Dimension(200, 30));
		textoStock.setPreferredSize(new Dimension(200, 30));
		textoStock.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoStock.setToolTipText("numero natural con 0");

		panel_etiquetas.add(etiquetaStock);
		panel_textfield.add(textoStock);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JLabel etiquetaPrecio = new JLabel("Precio: ");
		etiquetaPrecio.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoPrecio = new JTextField();
		textoPrecio.setMaximumSize(new Dimension(200, 30));
		textoPrecio.setMinimumSize(new Dimension(200, 30));
		textoPrecio.setPreferredSize(new Dimension(200, 30));
		textoPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoPrecio.setToolTipText("numero double");

		panel_etiquetas.add(etiquetaPrecio);
		panel_textfield.add(textoPrecio);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JLabel etiquetaRef = new JLabel("Ref:    ");
		etiquetaRef.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoRef = new JTextField();
		textoRef.setMaximumSize(new Dimension(200, 30));
		textoRef.setMinimumSize(new Dimension(200, 30));
		textoRef.setPreferredSize(new Dimension(200, 30));
		textoRef.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoRef.setToolTipText("numero natural sin 0");

		panel_etiquetas.add(etiquetaRef);
		panel_textfield.add(textoRef);
		
		JLabel etiquetaIdMarca = new JLabel("Id Marca:    ");
		etiquetaIdMarca.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoIdMarca = new JTextField();
		textoIdMarca.setMaximumSize(new Dimension(200, 30));
		textoIdMarca.setMinimumSize(new Dimension(200, 30));
		textoIdMarca.setPreferredSize(new Dimension(200, 30));
		textoIdMarca.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoIdMarca.setToolTipText("numero natural > 0");

		panel_etiquetas.add(etiquetaIdMarca);
		panel_textfield.add(textoIdMarca);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		principal.add(funcion);
		principal.add(centro);

		Controlador controlador = Controlador.getInstance();

		JPanel botones = new JPanel();
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreLeido = textoNombre.getText();
					int stockLeido = Integer.parseInt(textoStock.getText());
					double precioLeido = Double.parseDouble(textoPrecio.getText());
					int refLeido = Integer.parseInt(textoRef.getText());
					int idMarcaLeido = Integer.parseInt(textoIdMarca.getText());
					TProducto transfer = new TProducto(0, nombreLeido, stockLeido, precioLeido, refLeido, idMarcaLeido, true);
					controlador.accion(new Contexto(Evento.ALTA_PRODUCTO, transfer));
				} catch (NumberFormatException n) {
					controlador.accion(new Contexto(Evento.VISTA_FALLO_ALTA_PRODUCTO));
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
				controlador.accion(new Contexto(Evento.VISTA_PRODUCTO, null));
			}

		});
		atras.setMaximumSize(new Dimension(90, 30));
		atras.setPreferredSize(new Dimension(90, 30));

		botones.add(atras);
		botones.add(aceptar);
		principal.add(botones);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.pack();
		this.setResizable(false);
	}
}
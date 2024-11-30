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
		UtilidadesP.setAirGestRSP(this); // Ajustar utilidades según sea necesario
		this.setSize(600, 400);

		Controlador ctrl = Controlador.getInstance();

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panelTitulo = new JPanel();
		JLabel titulo = new JLabel("Alta Producto");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(titulo);

		funcion.add(panelTitulo);
		principal.add(funcion);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.LINE_AXIS));
		centro.setAlignmentX(CENTER_ALIGNMENT);
		principal.add(centro);

		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.PAGE_AXIS));
		panelEtiquetas.setAlignmentX(CENTER_ALIGNMENT);

		JPanel panelTexto = new JPanel();
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.PAGE_AXIS));
		panelTexto.setAlignmentX(CENTER_ALIGNMENT);

		// Campos para los datos del producto
		JLabel etiquetaNombre = new JLabel("Nombre: ");
		etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoNombre = new JTextField();
		textoNombre.setMaximumSize(new Dimension(200, 30));
		textoNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaNombre);
		panelTexto.add(textoNombre);

		JLabel etiquetaStock = new JLabel("Stock: ");
		etiquetaStock.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoStock = new JTextField();
		textoStock.setMaximumSize(new Dimension(200, 30));
		textoStock.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaStock);
		panelTexto.add(textoStock);

		JLabel etiquetaPrecio = new JLabel("Precio: ");
		etiquetaPrecio.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoPrecio = new JTextField();
		textoPrecio.setMaximumSize(new Dimension(200, 30));
		textoPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaPrecio);
		panelTexto.add(textoPrecio);

		JLabel etiquetaRef = new JLabel("Referencia: ");
		etiquetaRef.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoRef = new JTextField();
		textoRef.setMaximumSize(new Dimension(200, 30));
		textoRef.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaRef);
		panelTexto.add(textoRef);

		JLabel etiquetaIdMarca = new JLabel("ID Marca: ");
		etiquetaIdMarca.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoIdMarca = new JTextField();
		textoIdMarca.setMaximumSize(new Dimension(200, 30));
		textoIdMarca.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaIdMarca);
		panelTexto.add(textoIdMarca);

		centro.add(panelEtiquetas);
		centro.add(panelTexto);

		// Botones
		JPanel panelBotones = new JPanel();
		principal.add(panelBotones);
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);

		JButton atras = new JButton("ATRÁS");
		atras.setToolTipText("Volver a la ventana anterior");
		atras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_PRODUCTO, null));
			}
		});
		panelBotones.add(atras);

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreLeido = textoNombre.getText();
					int stockLeido = Integer.parseInt(textoStock.getText());
					double precioLeido = Double.parseDouble(textoPrecio.getText());
					int refLeida = Integer.parseInt(textoRef.getText());
					int idMarcaLeida = Integer.parseInt(textoIdMarca.getText());

					TProducto transfer = new TProducto(0, nombreLeido, stockLeido, precioLeido, refLeida, idMarcaLeida, true);
					ctrl.accion(new Contexto(Evento.ALTA_PRODUCTO, transfer));
				} catch (NumberFormatException ex) {
					// Manejo de errores para campos numéricos
					System.err.println("Error en el formato de los datos: " + ex.getMessage());
				}
			}
		});
		panelBotones.add(aceptar);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.pack();
	}
}

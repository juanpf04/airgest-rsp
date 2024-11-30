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

import presentacion.Observador;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaConsultarProductoPorId extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		// Configurar la ventana principal
		this.setSize(460, 180);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		// Título
		JPanel panelTitulo = new JPanel();
		JLabel titulo = new JLabel("Consultar Producto por ID");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(titulo);

		principal.add(panelTitulo);

		// Centro (campo ID)
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));

		JPanel id = new JPanel();
		id.setLayout(new BoxLayout(id, BoxLayout.LINE_AXIS));
		JLabel etiquetaId = new JLabel("ID: ");
		etiquetaId.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoId = new JTextField();
		textoId.setMaximumSize(new Dimension(200, 30));
		textoId.setFont(new Font("Tahoma", Font.BOLD, 18));
		id.add(etiquetaId);
		id.add(textoId);
		centro.add(id);

		principal.add(centro);

		// Controlador
		Controlador controlador = Controlador.getInstance();

		// Botones
		JPanel botones = new JPanel();

		// Botón ACEPTAR
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idProducto = Integer.parseInt(textoId.getText());
					controlador.accion(new Contexto(Evento.CONSULTAR_PRODUCTO_POR_ID, idProducto));
				} catch (NumberFormatException n) {
					System.err.println("Error: El ID debe ser un número entero.");
					controlador.accion(new Contexto(Evento.CONSULTAR_PRODUCTO_POR_ID, 0)); // Manejo de error
				}
			}
		});

		aceptar.setMaximumSize(new Dimension(100, 30));
		aceptar.setPreferredSize(new Dimension(100, 30));
		botones.add(aceptar);

		// Botón ATRÁS
		JButton atras = new JButton("ATRÁS");
		atras.setToolTipText("Volver a la ventana anterior");
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

		principal.add(botones);

		// Configuración final de la ventana
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
	}
}

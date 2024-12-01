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

public class VistaConsultarProductosPorProveedor extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		// Configuración de la ventana
		this.setSize(450, 250);

		Controlador controlador = Controlador.getInstance();

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		// Título
		JPanel panelTitulo = new JPanel();
		JLabel titulo = new JLabel("Consultar Productos por Proveedor");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(titulo);

		principal.add(panelTitulo);

		// Panel central para ingresar el proveedor
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.LINE_AXIS));
		centro.setAlignmentX(CENTER_ALIGNMENT);

		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.PAGE_AXIS));
		panelEtiquetas.setAlignmentX(CENTER_ALIGNMENT);

		JPanel panelTexto = new JPanel();
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.PAGE_AXIS));
		panelTexto.setAlignmentX(CENTER_ALIGNMENT);

		JLabel etiquetaProveedor = new JLabel("Proveedor: ");
		etiquetaProveedor.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoProveedor = new JTextField();
		textoProveedor.setMaximumSize(new Dimension(200, 30));
		textoProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaProveedor);
		panelTexto.add(textoProveedor);

		centro.add(panelEtiquetas);
		centro.add(panelTexto);

		principal.add(centro);

		// Panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);

		// Botón ACEPTAR
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String proveedor = textoProveedor.getText().trim();
				if (!proveedor.isEmpty()) {
					controlador.accion(new Contexto(Evento.CONSULTAR_PRODUCTOS_POR_PROVEEDOR, proveedor));
				} else {
					controlador.accion(new Contexto(Evento.CONSULTAR_PRODUCTOS_POR_PROVEEDOR, null)); // Manejo de error
				}
			}
		});

		aceptar.setMaximumSize(new Dimension(100, 30));
		aceptar.setPreferredSize(new Dimension(100, 30));
		panelBotones.add(aceptar);

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
		panelBotones.add(atras);

		principal.add(panelBotones);

		// Configuración final de la ventana
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.pack();
	}
}

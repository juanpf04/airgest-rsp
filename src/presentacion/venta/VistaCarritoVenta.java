
package presentacion.venta;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import negocio.venta.TCarritoVenta;
import negocio.venta.TLineaVenta;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaCarritoVenta extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		TCarritoVenta carrito_venta = (TCarritoVenta) datos;
		this.setSize(600, 552);
		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Carrito empleado " + carrito_venta.getIdEmpleado());
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);
		principal.add(funcion);

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(0, 1, 5, 5));

		Controlador ctrl = Controlador.getInstance();

		JButton anyadir = new JButton("ANYADIR PRODUCTO");
		anyadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_ANYADIR_PRODUCTO, carrito_venta));
			}
		});
		botones.add(anyadir);

		// -------------------------------------------
		JButton eliminar = new JButton("ELIMINAR PRODUCTO");
		eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_ELIMINAR_PRODUCTO, carrito_venta));
			}
		});
		botones.add(eliminar);

		// -------------------------------------------
		JButton cerrar = new JButton("CERRAR VENTA");

		cerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LocalDate fechaActual = LocalDate.now();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String fechaHoy = fechaActual.format(formato);
				carrito_venta.getVenta().setFecha(fechaHoy);
				ctrl.accion(new Contexto(Evento.CERRAR_VENTA, carrito_venta));
				
			}
		});
		botones.add(cerrar);

		principal.add(botones, BorderLayout.CENTER);

		// -----------------------------------------------------

		String s = "";
		for (TLineaVenta l : carrito_venta.getLineasVenta()) {
			s += "id_producto: " + l.getIdProducto() + " cantidad: " + l.getCantidad()
					+ "\n";
		}

		JTextArea exito = new JTextArea(s);
		exito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exito.setLineWrap(true); // Habilita el ajuste autom�tico de l�nea
		exito.setWrapStyleWord(true); // Ajusta el texto en palabras completas
		exito.setEditable(false); // Hace que el JTextArea sea de solo lectura

		JScrollPane scroll = new JScrollPane(exito);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(500, 300)); // Ajusta el tama�o
															// del JScrollPane
		principal.add(scroll);

		// -----------------------------------------------------

		JPanel panel_atras = new JPanel();
		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(new Contexto(Evento.VISTA_VENTA, null));
			}

		});
		panel_atras.setAlignmentX(CENTER_ALIGNMENT);
		panel_atras.add(atras);
		principal.add(panel_atras);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
	}
}
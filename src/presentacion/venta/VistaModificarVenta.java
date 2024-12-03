
package presentacion.venta;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import negocio.venta.TVenta;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaModificarVenta extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(420, 250);

		Controlador controlador = Controlador.getInstance();
		TVenta venta = (TVenta) datos;

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel centro = new JPanel();

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Modificar Venta");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);

		JPanel panel_etiquetas = new JPanel();
		panel_etiquetas.setLayout(new BoxLayout(panel_etiquetas, BoxLayout.PAGE_AXIS));

		JPanel panel_textfield = new JPanel();
		panel_textfield.setLayout(new BoxLayout(panel_textfield, BoxLayout.PAGE_AXIS));

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JLabel etiquetaEmpleado = new JLabel("id empleado: ");
		etiquetaEmpleado.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoEmpleado = new JTextField("" + venta.getIdEmpleado());
		textoEmpleado.setMaximumSize(new Dimension(200, 30));
		textoEmpleado.setMinimumSize(new Dimension(200, 30));
		textoEmpleado.setPreferredSize(new Dimension(200, 30));
		textoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_etiquetas.add(etiquetaEmpleado);
		panel_textfield.add(textoEmpleado);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JPanel precio = new JPanel();
		precio.setLayout(new BoxLayout(precio, BoxLayout.LINE_AXIS));
		JLabel etiquetaPrecio = new JLabel("precio: ");
		etiquetaPrecio.setFont(new Font("Tahoma", Font.BOLD, 23));
		JTextField textoPrecio = new JTextField("" + venta.getPrecio());
		textoPrecio.setMaximumSize(new Dimension(200, 30));
		textoPrecio.setMinimumSize(new Dimension(200, 30));
		textoPrecio.setPreferredSize(new Dimension(200, 30));
		textoPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_etiquetas.add(etiquetaPrecio);
		panel_textfield.add(textoPrecio);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);
		
		// SPINNER O COMBOBOX PARA LA FECHA
		JLabel etiquetaFecha = new JLabel("Fecha de Fabricación: ");
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 25));
		LocalDate currentDate = LocalDate.now();
		Date initialDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());// pasarlo
																									// a
																									// Date
																									// para
																									// el
																									// spinner
		SpinnerDateModel model = new SpinnerDateModel(initialDate, null, initialDate,
				java.util.Calendar.DAY_OF_MONTH);
		JSpinner spinner = new JSpinner(model);
		spinner.setMaximumSize(new Dimension(200, 30));
		spinner.setMinimumSize(new Dimension(200, 30));
		spinner.setPreferredSize(new Dimension(200, 30));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");// formato
																					// de
																					// la
																					// fecha
		spinner.setEditor(editor);
		panel_etiquetas.add(etiquetaFecha);
		panel_textfield.add(spinner);

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
					int id_empleado = Integer.parseInt(textoEmpleado.getText());
					double precio = Double.parseDouble(textoPrecio.getText());
					Date seleccion = (Date) spinner.getValue();
					ZonedDateTime zonedDateTime = seleccion.toInstant().atZone(ZoneId.systemDefault());
					LocalDate fecha = zonedDateTime.toLocalDate();
					// Formatear la fecha como dd-MM-yyyy
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String fechaFormateada = fecha.format(formatter);

					TVenta v = new TVenta(venta.getId(), precio, fechaFormateada, id_empleado);
					controlador.accion(new Contexto(Evento.MODIFICAR_VENTA, v));
					dispose();
				} catch (NumberFormatException n) {
					dispose();
					controlador.accion(new Contexto(Evento.MODIFICAR_VENTA, new TVenta()));
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
				controlador.accion(new Contexto(Evento.VISTA_VENTA, null));
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
		this.setResizable(false);
		this.pack();
	}
}
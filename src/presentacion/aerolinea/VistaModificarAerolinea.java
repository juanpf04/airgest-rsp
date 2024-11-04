package presentacion.aerolinea;

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

import negocio.aerolinea.TAerolinea;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaModificarAerolinea extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		TAerolinea ta = (TAerolinea) datos;
		UtilidadesP.setAirGestRSP(this);
		this.setSize(420, 220);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel centro = new JPanel();

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Modificar Aerolinea");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);

		JPanel panel_etiquetas = new JPanel();
		panel_etiquetas.setLayout(new BoxLayout(panel_etiquetas, BoxLayout.PAGE_AXIS));

		JPanel panel_textfield = new JPanel();
		panel_textfield.setLayout(new BoxLayout(panel_textfield, BoxLayout.PAGE_AXIS));

		JLabel etiquetaNombre = new JLabel("nombre: ");
		etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoNombre = new JTextField(ta.getNombre());
		textoNombre.setMaximumSize(new Dimension(200, 30));
		textoNombre.setMinimumSize(new Dimension(200, 30));
		textoNombre.setPreferredSize(new Dimension(200, 30));
		textoNombre.setFont(new Font("Tahoma", Font.BOLD, 18));

		panel_etiquetas.add(etiquetaNombre);
		panel_textfield.add(textoNombre);

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
					TAerolinea transfer = new TAerolinea(ta.getId(), nombreLeido, true);
					controlador.accion(new Contexto(Evento.MODIFICAR_AEROLINEA, transfer));
					dispose();
				} catch (NumberFormatException n) {
					dispose();
					controlador.accion(new Contexto(Evento.MODIFICAR_AEROLINEA, new TAerolinea()));
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
				controlador.accion(new Contexto(Evento.VISTA_AEROLINEA, null));
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
	}

}
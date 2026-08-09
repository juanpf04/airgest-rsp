package presentacion.personal;

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
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaConsultarPersonalPorHangar extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(450, 250);

		Controlador ctrl = Controlador.getInstance();

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Consultar Personal por Hangar");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);
		principal.add(funcion);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.LINE_AXIS));
		centro.setAlignmentX(CENTER_ALIGNMENT);
		principal.add(centro);

		JPanel panelBotones = new JPanel();
		principal.add(panelBotones);
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);

		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_PERSONAL, null));
			}

		});
		panelBotones.add(atras);

		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.PAGE_AXIS));
		panelEtiquetas.setAlignmentX(CENTER_ALIGNMENT);

		JPanel panelTexto = new JPanel();
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.PAGE_AXIS));
		panelTexto.setAlignmentX(CENTER_ALIGNMENT);

		JLabel etiquetaA = new JLabel("id Hangar: ");
		etiquetaA.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoA = new JTextField();
		textoA.setMaximumSize(new Dimension(200, 30));
		textoA.setMinimumSize(new Dimension(200, 30));
		textoA.setPreferredSize(new Dimension(200, 30));
		textoA.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelEtiquetas.add(etiquetaA);
		panelTexto.add(textoA);

		centro.add(panelEtiquetas);
		centro.add(panelTexto);

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idHang = Integer.valueOf(textoA.getText());

					ctrl.accion(new Contexto(Evento.CONSULTAR_PERSONAL_POR_HANGAR, idHang));

				} catch (Exception ex) {
					ctrl.accion(new Contexto(Evento.CONSULTAR_PERSONAL_POR_HANGAR, 0));
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

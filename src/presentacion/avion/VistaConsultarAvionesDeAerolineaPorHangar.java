package presentacion.avion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaConsultarAvionesDeAerolineaPorHangar extends JFrame implements Observador{

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(470, 160);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Consultar aviones de una Aerolínea por Hangar");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		principal.add(panel_titulo);
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));

		JPanel idA = new JPanel();
		JPanel idH = new JPanel();
		idA.setLayout(new BoxLayout(idA, BoxLayout.LINE_AXIS));
		idH.setLayout(new BoxLayout(idA, BoxLayout.LINE_AXIS));
		JLabel etiquetaIdAerolinea = new JLabel("id aerolínea: ");
		JLabel etiquetaIdHangar = new JLabel("id hangar: ");
		etiquetaIdAerolinea.setFont(new Font("Tahoma", Font.PLAIN, 25));
		etiquetaIdHangar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		JTextField textoIdA = new JTextField();
		JTextField textoIdH = new JTextField();
		textoIdA.setMaximumSize(new Dimension(200, 30));
		textoIdA.setMinimumSize(new Dimension(200, 30));
		textoIdA.setPreferredSize(new Dimension(200, 30));
		textoIdA.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoIdH.setMaximumSize(new Dimension(200, 30));
		textoIdH.setMinimumSize(new Dimension(200, 30));
		textoIdH.setPreferredSize(new Dimension(200, 30));
		textoIdH.setFont(new Font("Tahoma", Font.BOLD, 18));
		idA.add(etiquetaIdAerolinea);
		idA.add(textoIdA);
		centro.add(idH);
		idH.add(etiquetaIdHangar);
		idH.add(textoIdH);
		centro.add(idA);
		principal.add(centro);

		Controlador controlador = Controlador.getInstance();
		JSplitPane botones = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idA = Integer.parseInt(textoIdA.getText());
					int idH = Integer.parseInt(textoIdH.getText());
					ArrayList<Integer> lista = new ArrayList<>();
					lista.add(idA);
					lista.add(idH);
					controlador.accion(new Contexto(Evento.CONSULTAR_AVIONES_DE_AEROLINEA_POR_HANGAR, lista));
				} catch (NumberFormatException n) {
					ArrayList<Integer> lista = new ArrayList<>();
					lista.add(0);
					lista.add(0);
					controlador.accion(new Contexto(Evento.CONSULTAR_AVIONES_DE_AEROLINEA_POR_HANGAR, lista));
				}
			}
		});
		aceptar.setMaximumSize(new Dimension(100, 30));
		aceptar.setPreferredSize(new Dimension(100, 30));
		botones.setMaximumSize(new Dimension(190, 30));
		botones.setPreferredSize(new Dimension(190, 30));

		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				controlador.accion(new Contexto(Evento.VISTA_AVION, null));
			}
		});

		atras.setMaximumSize(new Dimension(90, 30));
		atras.setPreferredSize(new Dimension(90, 30));

		botones.add(aceptar);
		botones.add(atras);
		principal.add(botones);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);		
	}

}

package presentacion.contrato;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaConsultarContratoPorAerolineaPyD extends JFrame implements Observador{

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(530, 170);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Consultar contratos por Aerolínea, precio y duración");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		principal.add(panel_titulo);
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));

		JPanel id = new JPanel();
		JPanel precio = new JPanel();
		JPanel dia = new JPanel();
		id.setLayout(new BoxLayout(id, BoxLayout.LINE_AXIS));
		precio.setLayout(new BoxLayout(id, BoxLayout.LINE_AXIS));
		dia.setLayout(new BoxLayout(id, BoxLayout.LINE_AXIS));
		JLabel etiquetaId = new JLabel("id aerolinea: ");
		JLabel etiquetaPrecio = new JLabel("precio mínimo: ");
		JLabel etiquetaDia = new JLabel("días mínimos: ");
		etiquetaId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		etiquetaPrecio.setFont(new Font("Tahoma", Font.PLAIN, 25));
		etiquetaDia.setFont(new Font("Tahoma", Font.PLAIN, 25));
		JTextField textoId = new JTextField();
		JTextField textoPrecio = new JTextField();
		JTextField textoDia = new JTextField();
		textoId.setMaximumSize(new Dimension(200, 30));
		textoId.setMinimumSize(new Dimension(200, 30));
		textoId.setPreferredSize(new Dimension(200, 30));
		textoId.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoPrecio.setMaximumSize(new Dimension(200, 30));
		textoPrecio.setMinimumSize(new Dimension(200, 30));
		textoPrecio.setPreferredSize(new Dimension(200, 30));
		textoPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));
		textoDia.setMaximumSize(new Dimension(200, 30));
		textoDia.setMinimumSize(new Dimension(200, 30));
		textoDia.setPreferredSize(new Dimension(200, 30));
		textoDia.setFont(new Font("Tahoma", Font.BOLD, 18));
		id.add(etiquetaId);
		id.add(textoId);
		centro.add(id);
		precio.add(etiquetaPrecio);
		precio.add(textoPrecio);
		centro.add(precio);
		dia.add(etiquetaDia);
		dia.add(textoDia);
		centro.add(dia);

		principal.add(centro);

		Controlador controlador = Controlador.getInstance();
		JSplitPane botones = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Object> lista = new ArrayList<>();
					int valor_id = Integer.parseInt(textoId.getText());
					double valor_precio = Double.parseDouble(textoPrecio.getText());
					int valor_dia = Integer.parseInt(textoDia.getText());
					lista.add(valor_id);
					lista.add(valor_precio);
					lista.add(valor_dia);
					controlador.accion(new Contexto(Evento.VISTA_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION, lista));
				} catch (NumberFormatException n) {
					ArrayList<Object> lista = new ArrayList<>();
					lista.add(0);
					lista.add(0);
					lista.add(0);
					controlador.accion(new Contexto(Evento.VISTA_CONSULTAR_CONTRATOS_POR_AEROLINEA_PRECIO_Y_DURACION, lista));
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
				controlador.accion(new Contexto(Evento.VISTA_CONTRATO, null));
			}
		});

		atras.setMaximumSize(new Dimension(90, 30));
		atras.setPreferredSize(new Dimension(90, 30));

		botones.setAlignmentX(LEFT_ALIGNMENT);
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

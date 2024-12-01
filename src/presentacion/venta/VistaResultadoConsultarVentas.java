package presentacion.venta;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import negocio.venta.TVenta;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaResultadoConsultarVentas extends JFrame implements Observador {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(400, 450);

		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		List<TVenta> ventas;
		
		if(datos != null) {
			ventas = (List<TVenta>) datos;
		}
		else{
			ventas = new ArrayList<>();
		}
		
		/* ESTO ESTABA ANTES, LO COMENTO PARA QUE FUNCIONE YA QUE DATOS ES NULL PORQUE LO DE NEGOCIO NO ESTA HECHO AUN
		 * 	@SuppressWarnings("unchecked")
		 * List<TVenta> ventas = (List<TVenta>) datos;
		 * */
		
		String s = "";
		for (TVenta v : ventas)
			s += v.toString() + "\n";

		JTextArea exito = new JTextArea(s);
		exito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exito.setLineWrap(true); // Habilita el ajuste automático de línea
		exito.setWrapStyleWord(true); // Ajusta el texto en palabras completas
		exito.setEditable(false); // Hace que el JTextArea sea de solo lectura

		JScrollPane scroll = new JScrollPane(exito, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño
															// del JScrollPane

		principal.add(scroll, BorderLayout.CENTER);

		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador c = Controlador.getInstance();
				c.accion(new Contexto(Evento.VISTA_VENTA, datos));
				dispose();
			}
		});

		principal.add(atras, BorderLayout.SOUTH);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
	}
}
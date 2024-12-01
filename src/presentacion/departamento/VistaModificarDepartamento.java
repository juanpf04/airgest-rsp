
package presentacion.departamento;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import negocio.departamento.TDepartamento;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaModificarDepartamento extends JFrame implements Observador {

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(450, 270);

		//que es esto??
		@SuppressWarnings("unchecked")
		ArrayList<Object> listaInfo = (ArrayList<Object>) datos;
		Controlador ctrl = Controlador.getInstance();

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Modificar Departamento");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);
		principal.add(funcion);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.LINE_AXIS));
		principal.add(centro);

		JPanel panelBotones = new JPanel();
		principal.add(panelBotones);
		
		
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					/*int tag = Integer.valueOf(textoTag.getText());
					int horasMensuales = Integer.valueOf(textoHorasMensuales.getText());
					int idDepartamento = Integer.valueOf(textoIdDepartamento.getText());
					TEmpleado transfer;
					if (listaInfo.get(1) == "GERENTE") {
						int despacho = Integer.valueOf(textoDespacho.getText());
						int horasExtra = Integer.valueOf(textoHorasExtra.getText());
						transfer = new TGerente(0, tag, horasMensuales, idDepartamento, true, despacho, horasExtra);
					} else {
						int seccion = Integer.valueOf(textoSeccion.getText());
						String noche = textoNoches.getText();
						boolean noches = false;
						if(noche.equals("si") || noche.equals("Si") || noche.equals("SI")) noches = true;
						transfer = new TDependiente(0, tag, horasMensuales, idDepartamento, true, seccion, noches);
					}*/
					TDepartamento tdep = new TDepartamento();
					dispose();
					ctrl.accion(new Contexto(Evento.MODIFICAR_DEPARTAMENTO, tdep));
				} catch (Exception ex) {
					dispose();
					ctrl.accion(new Contexto(Evento.VISTA_FALLO_MODIFICAR_DEPARTAMENTO, null));
				}
			}

		});

		panelBotones.add(aceptar);
		
		JButton atras = new JButton("ATRAS"); 
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (listaInfo.get(1) == null)
					ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_ID_DEPARTAMENTO, null));
				else
					ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_ID_DEPARTAMENTO, listaInfo));
			}

		});
		
		panelBotones.add(atras);

	this.setContentPane(principal);
	this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	this.setVisible(true);
	this.setLocation(200, 200);
	this.setResizable(false);
	this.pack();
	}
}
package presentacion.empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import negocio.empleado.TEmpleado;
import negocio.empleado.TGerente;
import negocio.empleado.TDependiente;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaModificarEmpleado extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(450, 270);

		@SuppressWarnings("unchecked")
		ArrayList<Object> listaInfo = (ArrayList<Object>) datos;
		Controlador ctrl = Controlador.getInstance();

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Modificar Empleado");
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

		if (listaInfo.get(1) == null) {
			JPanel botones = new JPanel();
			botones.setLayout(new GridLayout(0, 1, 8, 8));

			// -------------------------------------------
			JButton gerente = new JButton("GERENTE");

			gerente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listaInfo.add(1, "GERENTE");
					dispose();
					ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO, listaInfo));
				}
			});

			gerente.setAlignmentX(CENTER_ALIGNMENT);
			botones.add(gerente);

			// -------------------------------------------
			JButton dependiente = new JButton("DEPENDIENTE");

			dependiente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listaInfo.add(1, "DEPENDIENTE");
					dispose();
					ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO, listaInfo));
				}
			});

			dependiente.setAlignmentX(CENTER_ALIGNMENT);
			botones.add(dependiente);
			botones.add(panelBotones);

			JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
			// principal
			atras.setToolTipText("Esto vuelve a la ventana anterior");
			atras.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					if (listaInfo.get(1) == null)
						ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO_ID, null));
					else
						ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO_ID, listaInfo));
				}

			});
			panelBotones.add(atras);

			principal.add(botones);

			// -----------------------------------------------------
		} else {
			TEmpleado aux = (TEmpleado) listaInfo.get(0);
			String despacho = "";
			String horasExtra = "";
			
			String seccion = "";
			String noches = "";
			if (aux instanceof TGerente){
				despacho = "" + ((TGerente) aux).getDespacho();
				horasExtra = "" + ((TGerente) aux).getHorasExtra();
			}
			else{
				seccion = "" + ((TDependiente) aux).getSeccion();
				noches = "" + ((TDependiente) aux).getNoches();
			}

			JPanel panelEtiquetas = new JPanel();
			panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.PAGE_AXIS));

			JPanel panelTexto = new JPanel();
			panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.PAGE_AXIS));

			JLabel etiquetaTag = new JLabel("Tag: ");
			etiquetaTag.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoTag = new JTextField("" +  aux.getTag());
			textoTag.setMaximumSize(new Dimension(200, 30));
			textoTag.setMinimumSize(new Dimension(200, 30));
			textoTag.setPreferredSize(new Dimension(200, 30));
			textoTag.setFont(new Font("Tahoma", Font.BOLD, 18));
			panelEtiquetas.add(etiquetaTag);
			panelTexto.add(textoTag);
			textoTag.setToolTipText("número mayor que 0");
			centro.add(panelEtiquetas);
			centro.add(panelTexto);

			JLabel etiquetaHorasMensuales = new JLabel("Horas Mensuales:    ");
			etiquetaHorasMensuales.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoHorasMensuales = new JTextField("" + aux.getHorasMensuales());
			textoHorasMensuales.setMaximumSize(new Dimension(200, 30));
			textoHorasMensuales.setMinimumSize(new Dimension(200, 30));
			textoHorasMensuales.setPreferredSize(new Dimension(200, 30));
			textoHorasMensuales.setFont(new Font("Tahoma", Font.BOLD, 18));
			panelEtiquetas.add(etiquetaHorasMensuales);
			panelTexto.add(textoHorasMensuales);
			textoHorasMensuales.setToolTipText("int de las horas mensuales");

			JLabel etiquetaIdDepartamento = new JLabel("id Departamento:    ");
			etiquetaIdDepartamento.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoIdDepartamento = new JTextField("" + aux.getIdDepartamento());
			textoIdDepartamento.setMaximumSize(new Dimension(200, 30));
			textoIdDepartamento.setMinimumSize(new Dimension(200, 30));
			textoIdDepartamento.setPreferredSize(new Dimension(200, 30));
			textoIdDepartamento.setFont(new Font("Tahoma", Font.BOLD, 18));
			panelEtiquetas.add(etiquetaIdDepartamento);
			panelTexto.add(textoIdDepartamento);
			textoIdDepartamento.setToolTipText("numero mayor que 0");
			
			JLabel etiquetaDespacho = new JLabel("Nº Despacho:    ");
			etiquetaDespacho.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoDespacho = new JTextField(despacho);
			textoDespacho.setMaximumSize(new Dimension(200, 30));
			textoDespacho.setMinimumSize(new Dimension(200, 30));
			textoDespacho.setPreferredSize(new Dimension(200, 30));
			textoDespacho.setFont(new Font("Tahoma", Font.BOLD, 18));
			textoDespacho.setToolTipText("numero mayor que 0");

			JLabel etiquetaHorasExtra = new JLabel("Horas Extra:    ");
			etiquetaHorasExtra.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoHorasExtra = new JTextField(horasExtra);
			textoHorasExtra.setMaximumSize(new Dimension(200, 30));
			textoHorasExtra.setMinimumSize(new Dimension(200, 30));
			textoHorasExtra.setPreferredSize(new Dimension(200, 30));
			textoHorasExtra.setFont(new Font("Tahoma", Font.BOLD, 18));
			textoHorasExtra.setToolTipText("numero mayor que 0");
			
			JLabel etiquetaSeccion = new JLabel("Sección:    ");
			etiquetaSeccion.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoSeccion = new JTextField(seccion);
			textoSeccion.setMaximumSize(new Dimension(200, 30));
			textoSeccion.setMinimumSize(new Dimension(200, 30));
			textoSeccion.setPreferredSize(new Dimension(200, 30));
			textoSeccion.setFont(new Font("Tahoma", Font.BOLD, 18));
			textoSeccion.setToolTipText("numero mayor que 0");
			
			JLabel etiquetaNoches = new JLabel("Noches:    ");
			etiquetaNoches.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoNoches = new JTextField(noches);
			textoNoches.setMaximumSize(new Dimension(200, 30));
			textoNoches.setMinimumSize(new Dimension(200, 30));
			textoNoches.setPreferredSize(new Dimension(200, 30));
			textoNoches.setFont(new Font("Tahoma", Font.BOLD, 18));
			textoNoches.setToolTipText("true/false");

			if ("GERENTE" == listaInfo.get(1)) {
				panelEtiquetas.add(etiquetaDespacho);
				panelTexto.add(textoDespacho);
				
				panelEtiquetas.add(etiquetaHorasExtra);
				panelTexto.add(textoHorasExtra);
			} else {
				panelEtiquetas.add(etiquetaSeccion);
				panelTexto.add(textoSeccion);
				
				panelEtiquetas.add(etiquetaNoches);
				panelTexto.add(textoNoches);
			}

			JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
			// principal
			atras.setToolTipText("Esto vuelve a la ventana anterior");
			atras.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					if (listaInfo.get(1) == null)
						ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO_ID, null));
					else
						ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO_ID, listaInfo));
				}

			});
			panelBotones.add(atras);

			JButton aceptar = new JButton("ACEPTAR");
			aceptar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int tag = Integer.valueOf(textoTag.getText());
						int horasMensuales = Integer.valueOf(textoHorasMensuales.getText());
						int idDepartamento = Integer.valueOf(textoIdDepartamento.getText());
						TEmpleado transfer;
						if (listaInfo.get(1) == "GERENTE") {
							int despacho = Integer.valueOf(textoDespacho.getText());
							int horasExtra = Integer.valueOf(textoHorasExtra.getText());
							transfer = new TGerente(aux.getId(), tag, horasMensuales, idDepartamento, true, despacho, horasExtra);
						} else {
							int seccion = Integer.valueOf(textoSeccion.getText());
							String noche = textoNoches.getText();
							boolean noches = false;
							if(noche.equals("true") || noche.equals("True") || noche.equals("TRUE")) noches = true;
							transfer = new TDependiente(aux.getId(), tag, horasMensuales, idDepartamento, true, seccion, noches);
						}
						dispose();
						ctrl.accion(new Contexto(Evento.MODIFICAR_EMPLEADO, transfer));
					} catch (Exception ex) {
						dispose();
						ctrl.accion(new Contexto(Evento.VISTA_FALLO_MODIFICAR_EMPLEADO, null));
					}
				}

			});

			panelBotones.add(aceptar);

		}

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.pack();
	}
}
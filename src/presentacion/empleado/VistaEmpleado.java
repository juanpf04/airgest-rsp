package presentacion.empleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaEmpleado extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(430, 390); // hace que la ventana no salga tan chiquitita

		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		JPanel page_start_panel = new JPanel();
		page_start_panel.setLayout(new BoxLayout(page_start_panel, BoxLayout.PAGE_AXIS));

		JPanel panel_label_personal = new JPanel();
		JLabel personal = new JLabel("EMPLEADO"); //titulo de la ventana en la que estamos, apareceran las funciones de personal 
		personal.setFont(new Font("Comic Sans", Font.BOLD, 30));
		personal.setHorizontalAlignment(SwingConstants.CENTER);
		personal.setBorder(new LineBorder(Color.BLACK, 2));
		panel_label_personal.add(personal);

		JSeparator separador_personal = new JSeparator(SwingConstants.CENTER);
		separador_personal.setBorder(new MatteBorder(1, 1, 10, 10, Color.BLACK));
		separador_personal.setPreferredSize(new Dimension(0, 2));
		page_start_panel.add(panel_label_personal);
		page_start_panel.add(separador_personal);
		principal.add(page_start_panel, BorderLayout.PAGE_START);

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(0, 1, 7, 7));

		Controlador ctrl = Controlador.getInstance();

		JButton alta = new JButton("ALTA DE EMPLEADO");
		alta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_ALTA_EMPLEADO, null));
			}
		});
		alta.setToolTipText("Aqui das de alta un empleado maquina");
		botones.add(alta);

		// -------------------------------------------
		JButton baja = new JButton("BAJA DE EMPLEADO");
		baja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_BAJA_EMPLEADO, null));
			}
		});
		baja.setToolTipText("Aqui das de baja un empleado maquina");
		botones.add(baja);

		// -------------------------------------------
		JButton consultarID = new JButton("CONSULTAR EMPLEADO POR ID");

		consultarID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_EMPLEADO_POR_ID, null));
			}
		});
		consultarID.setToolTipText("Aqui consultas un empleado por id maquina");
		botones.add(consultarID);

		// -------------------------------------------
		JButton consultarTodos = new JButton("CONSULTAR EMPLEADOS");

		consultarTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(new Contexto(Evento.CONSULTAR_EMPLEADOS, null));
			}
		});
		consultarTodos.setToolTipText("Aqui consultas todos los empleados maquina");
		botones.add(consultarTodos);

		// -------------------------------------------

		JButton consultarPorHangar = new JButton("CONSULTAR EMPLEADO POR DEPARTAMENTO");//SALTA EXCEPCION Y NO SÉ XQ

		consultarPorHangar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_EMPLEADOS_POR_DEPARTAMENTO, null));
			}
		});
		consultarPorHangar.setToolTipText("Aqui consultas un empleado por departamento maquina");
		botones.add(consultarPorHangar);

		// -------------------------------------------
		JButton modificar = new JButton("MODIFICAR EMPLEADO");

		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_EMPLEADO_ID, null));
			}
		});
		modificar.setToolTipText("Aqui modificas un empleado maquina");
		botones.add(modificar);


		principal.add(botones, BorderLayout.CENTER);

		// -----------------------------------------------------

		JPanel panel_atras = new JPanel();
		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(new Contexto(Evento.VISTA_PRINCIPAL, null));
			}

		});
		panel_atras.add(atras);
		principal.add(panel_atras, BorderLayout.PAGE_END);

		this.setContentPane(principal);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);

	}

}
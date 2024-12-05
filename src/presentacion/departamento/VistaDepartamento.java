/**
 * 
 */
package presentacion.departamento;

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

public class VistaDepartamento extends JFrame implements Observador {
	
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(430, 390); // hace que la ventana no salga tan chiquitita
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JPanel page_start_panel = new JPanel();
		page_start_panel.setLayout(new BoxLayout(page_start_panel, BoxLayout.PAGE_AXIS));

		JPanel panel_label_dep = new JPanel();
		JLabel dep = new JLabel("DEPARTAMENTO"); // titulo de la ventana en la que
												// estamos, apareceran las
												// funciones de modelo
												// modelo.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 1),
												// new MatteBorder(5,3,7,10, Color.ORANGE))); // OPCION 2
		dep.setBorder(new LineBorder(Color.BLACK, 2));
		dep.setFont(new Font("Comic Sans", Font.BOLD, 30));
		dep.setHorizontalAlignment(SwingConstants.CENTER);

		panel_label_dep.add(dep);

		JSeparator separador_departamento = new JSeparator(SwingConstants.CENTER);
		separador_departamento.setBorder(new MatteBorder(1, 1, 10, 10, Color.BLACK));
		separador_departamento.setPreferredSize(new Dimension(0, 2));
		page_start_panel.add(panel_label_dep);
		page_start_panel.add(separador_departamento);
		principal.add(page_start_panel, BorderLayout.PAGE_START);

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(0, 1, 7, 7));

		Controlador ctrl = Controlador.getInstance();

		JButton alta = new JButton("ALTA DE DEPARTAMENTO");
		alta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_ALTA_DEPARTAMENTO, null));
			}
		});
		alta.setToolTipText("Aqui das de alta un departamento maquina");
		botones.add(alta);

		// -------------------------------------------
		JButton baja = new JButton("BAJA DE DEPARTAMENTO");
		baja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_BAJA_DEPARTAMENTO, null));
			}
		});
		baja.setToolTipText("Aqui das de baja un departamento maquina");
		botones.add(baja);

		// -------------------------------------------
		JButton consultarID = new JButton("CONSULTAR DEPARTAMENTO POR ID");

		consultarID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_DEPARTAMENTO_POR_ID, null));
			}
		});

		consultarID.setToolTipText("Aqui consultas un departamento por id maquina");
		botones.add(consultarID);

		// -------------------------------------------
		JButton consultarTodos = new JButton("CONSULTAR TODOS LOS DEPARTAMENTOS");

		consultarTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(new Contexto(Evento.CONSULTAR_DEPARTAMENTOS, null));
			}
		});

		consultarTodos.setToolTipText("Aqui consultas todos los departamentos maquina");
		botones.add(consultarTodos);

		// -------------------------------------------
		JButton modificar = new JButton("MODIFICAR DEPARTAMENTO");

		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_ID_DEPARTAMENTO, null));
			}
		});

		modificar.setToolTipText("Aqui modificas un departamento maquina");
		botones.add(modificar);

		// -------------------------------------------
		JButton calcularNomina = new JButton("CALCULAR NOMINA DEL DEPARTAMENTO");

		calcularNomina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CALCULAR_NOMINA, null));
			}
		});

		calcularNomina.setToolTipText("Aqui calculas la nomina de un departamento maquina");
		botones.add(calcularNomina);

		// -----------------------------------------------------
		JPanel panel_atras = new JPanel();
		JButton atras = new JButton("ATRAS");
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(new Contexto(Evento.VISTA_PRINCIPAL, null));
			}

		});
		principal.add(botones);
		panel_atras.add(atras);
		principal.add(panel_atras, BorderLayout.PAGE_END);

		this.setContentPane(principal);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);

	}
}
package presentacion.proveedor;

import javax.swing.JFrame;
import presentacion.Observador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaProveedor extends JFrame implements Observador {
	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(430, 390); // hace que la ventana no salga tan chiquitita

		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JPanel page_start_panel = new JPanel();
		page_start_panel.setLayout(new BoxLayout(page_start_panel, BoxLayout.PAGE_AXIS));

		JPanel panel_label_proveedor = new JPanel();
		JLabel proveedor = new JLabel("PROVEEDOR"); //titulo de la ventana en la que estamos, apareceran las funciones de avion 
		proveedor.setFont(new Font("Comic Sans", Font.BOLD, 30));
		proveedor.setHorizontalAlignment(SwingConstants.CENTER);
		proveedor.setBorder(new LineBorder(Color.BLACK, 2));
		panel_label_proveedor.add(proveedor);

		JSeparator separador_proveedor = new JSeparator(SwingConstants.CENTER);
		separador_proveedor.setBorder(new MatteBorder(1, 1, 10, 10, Color.BLACK));
		separador_proveedor.setPreferredSize(new Dimension(0, 2));

		page_start_panel.add(panel_label_proveedor);
		page_start_panel.add(separador_proveedor);
		principal.add(page_start_panel, BorderLayout.PAGE_START);

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(0, 1, 7, 7));

		Controlador ctrl = Controlador.getInstance();

		JButton alta = new JButton("ALTA DE PROVEEDOR");
		alta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_ALTA_PROVEEDOR, null));
			}
		});
		alta.setToolTipText("Aqui das de alta un proveedor maquina");
		botones.add(alta);

		// -------------------------------------------
		JButton baja = new JButton("BAJA DE PROVEEDOR");
		baja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_BAJA_PROVEEDOR, null));
			}
		});
		baja.setToolTipText("Aqui das de baja un proveedor maquina");
		botones.add(baja);

		// -------------------------------------------
		JButton consultarID = new JButton("CONSULTAR PROVEEDOR POR ID");

		consultarID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_PROVEEDOR_POR_ID, null));
			}
		});

		consultarID.setToolTipText("Aqui consultas un proveedor por id maquina");
		botones.add(consultarID);

		// -------------------------------------------
		JButton consultarTodos = new JButton("CONSULTAR TODOS LOS PROVEEDORES");

		consultarTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(new Contexto(Evento.CONSULTAR_PROVEEDORES, null));
			}
		});

		consultarTodos.setToolTipText("Aqui consultas todos los proveedores maquina");
		botones.add(consultarTodos);

		// -------------------------------------------

		JButton consultarPorProducto = new JButton("CONSULTAR PROVEEDORES POR PRODUCTO");

		consultarPorProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_PROVEEDORES_POR_PRODUCTO, null));
			}
		});

		consultarPorProducto.setToolTipText("Aqui consultas proveedores por producto");
		botones.add(consultarPorProducto);

		// -------------------------------------------
		JButton modificar = new JButton("MODIFICAR PROVEEDOR");

		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_PROVEEDOR_ID, null));
			}
		});

		modificar.setToolTipText("Aqui modificas un proveedor maquina");
		botones.add(modificar);

		// -------------------------------------------
		JButton vincular = new JButton("VINCULAR PROVEEDOR");

		vincular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_VINCULAR_PROVEEDOR, null));
			}
		});

		vincular.setToolTipText("Aqui vinculas un proveedor maquina");
		botones.add(vincular);
		
		// -------------------------------------------
		JButton desvincular = new JButton("DESVINCULAR PROVEEDOR");

		desvincular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_DESVINCULAR_PROVEEDOR, null));
			}
		});

		desvincular.setToolTipText("Aqui desvinculas un proveedor maquina");
		botones.add(desvincular);

		// -------------------------------------------
		principal.add(botones, BorderLayout.CENTER);

		JPanel panel_atras = new JPanel();
		JButton atras = new JButton("ATRAS"); //boton para volver a la ventana principal
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
package presentacion.venta;

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

public class VistaVenta extends JFrame implements Observador {
	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(415, 370);
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JPanel page_start_panel = new JPanel();
		page_start_panel.setLayout(new BoxLayout(page_start_panel, BoxLayout.PAGE_AXIS));

		JPanel panel_label_venta = new JPanel();
		JLabel venta = new JLabel("VENTA"); //titulo de la ventana en la que estamos, apareceran las funciones de VENTA 
		venta.setFont(new Font("Comic Sans", Font.BOLD, 30));
		venta.setHorizontalAlignment(SwingConstants.CENTER);
		venta.setBorder(new LineBorder(Color.BLACK, 2));
		panel_label_venta.add(venta);

		JSeparator separador_venta = new JSeparator(SwingConstants.CENTER);
		separador_venta.setBorder(new MatteBorder(1, 1, 10, 10, Color.BLACK));
		separador_venta.setPreferredSize(new Dimension(0, 2));
		
		page_start_panel.add(panel_label_venta);
		page_start_panel.add(separador_venta);
		principal.add(page_start_panel, BorderLayout.PAGE_START);

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(0, 1, 7, 7));

		Controlador ctrl = Controlador.getInstance();
		
		//-------------------------------------------
		JButton abrir = new JButton("ABRIR CARRITO");
		abrir.setToolTipText("Aqui puedes abrir un carrito nuevo");
		abrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_ABRIR_VENTA, null));
			}
		});
		botones.add(abrir);
		
		//-------------------------------------------
		JButton consultar = new JButton("CONSULTAR VENTA POR ID");
		consultar.setToolTipText("Aqui puedes ver una venta por su id");
		consultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_VENTA_POR_ID, null));
			}
		});
		
		botones.add(consultar);

		//-------------------------------------------
		JButton consultarTodas = new JButton("CONSULTAR VENTAS");
		consultarTodas.setToolTipText("Aqui puedes ver todas las ventas");
		consultarTodas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.CONSULTAR_VENTAS, null));
			}
		});
		botones.add(consultarTodas);
		
		//-------------------------------------------
		JButton consultarPorEmpleado = new JButton("CONSULTAR VENTAS POR EMPLEADO");
		consultarPorEmpleado.setToolTipText("Aqui puedes consultar las ventas de un empleado");
		consultarPorEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_VENTAS_POR_EMPLEADO, null));
			}
		});

		botones.add(consultarPorEmpleado);
		
		//-------------------------------------------
		JButton modificarVenta = new JButton("MODIFICAR VENTA");
		modificarVenta.setToolTipText("Aqui puedes modificar una venta dado su id");
		modificarVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_VENTA_ID, null));
			}
		});

		botones.add(modificarVenta);
		

		//-------------------------------------------
		JButton modificarLinea = new JButton("MODIFICAR LINEA VENTA");
		modificarLinea.setToolTipText("Aqui puedes modificar los detalles de una venta");
		modificarLinea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_LINEA_VENTA, null));
			}
		});

		botones.add(modificarLinea);

//		//-------------------------------------------
//		JButton modificar_datos_venta = new JButton("MODIFICAR DATOS DE VENTA");
//		modificar_datos_venta.setToolTipText("Aqui modificas los datos de una venta");
//		modificar_datos_venta.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_VENTA, null));
//			}
//		});
//		botones.add(modificar_datos_venta);
		
		

		//-------------------------------------------
		JButton devolucion = new JButton("DEVOLUCION");
		devolucion.setToolTipText("Aqui puedes hacer una devolucion");
		devolucion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_DEVOLUCION_VENTA, null));
			}
		});
		
		botones.add(devolucion);
			

		principal.add(botones, BorderLayout.CENTER);


		JPanel panel_atras = new JPanel();
		JButton atras = new JButton("ATRAS"); //boton para volver a la ventana principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(new Contexto(Evento.VISTA_PRINCIPAL, null));
				dispose();
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
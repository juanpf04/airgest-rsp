package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import integracion.factoria.EMFSingleton;
import negocio.departamento.Departamento;
import negocio.departamento.TDepartamento;
import negocio.empleado.Empleado;
import negocio.empleado.Gerente;
import negocio.empleado.TGerente;
import negocio.marca.Marca;
import negocio.marca.TMarca;
import negocio.producto.Producto;
import negocio.producto.TProducto;
import negocio.proveedor.Nacional;
import negocio.proveedor.Proveedor;
import negocio.proveedor.TNacional;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaPrincipal extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(620, 1200); //hace que la ventana no salga tan chiquitita
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());

		JPanel page_start_panel = new JPanel();
		page_start_panel.setLayout(new BoxLayout(page_start_panel, BoxLayout.PAGE_AXIS));

		JPanel panel_label_titulo = new JPanel();
		JLabel titulo = new JLabel("AIRGEST RSP"); //titulo de la ventana en la que estamos, apareceran las funciones de modelo 
		titulo.setBorder(new LineBorder(Color.BLACK, 2));

		titulo.setFont(new Font("Comic Sans", Font.BOLD, 30));
		titulo.setHorizontalAlignment(SwingConstants.CENTER); //ESTO ES LO QUE LO CENTRA, SWINGCONSTANTS
		panel_label_titulo.add(titulo);

		JSeparator separador_titulo = new JSeparator(SwingConstants.CENTER);
		separador_titulo.setBorder(new MatteBorder(1, 1, 10, 10, Color.BLACK));
		separador_titulo.setPreferredSize(new Dimension(0, 2));
		page_start_panel.add(panel_label_titulo);
		page_start_panel.add(separador_titulo);
		principal.add(page_start_panel, BorderLayout.PAGE_START);

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(0, 1, 1, 7));

		Controlador ctrl = Controlador.getInstance();

		//-------------------------------------------

		JButton modelo = new JButton("MODELO");
		modelo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MODELO));
			}
		});
		modelo.setToolTipText("MODULO MODELO");
		botones.add(modelo);

		//-------------------------------------------
		JButton avion = new JButton("AVION");
		avion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_AVION));
			}
		});
		avion.setToolTipText("MODULO AVION");
		botones.add(avion);

		//-------------------------------------------
		JButton aerolinea = new JButton("AEROLINEA");
		aerolinea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_AEROLINEA));
			}
		});
		aerolinea.setToolTipText("MODULO AEROLINEA");
		botones.add(aerolinea);

		//-------------------------------------------
		JButton hangar = new JButton("HANGAR");
		hangar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_HANGAR));
			}
		});
		hangar.setToolTipText("MODULO HANGAR");
		botones.add(hangar);

		//-------------------------------------------
		JButton personal = new JButton("PERSONAL");
		personal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_PERSONAL));
			}
		});
		personal.setToolTipText("MODULO PERSONAL");
		botones.add(personal);

		//-------------------------------------------
		JButton contrato = new JButton("CONTRATO");
		contrato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_CONTRATO));
			}
		});

		contrato.setToolTipText("MODULO CONTRATO");
		botones.add(contrato);

		JButton marca = new JButton("MARCA");
		marca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_MARCA));
			}
		});
		marca.setToolTipText("MODULO MARCA");
		botones.add(marca);

		//-------------------------------------------
		JButton proveedor = new JButton("PROVEEDOR");
		proveedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_PROVEEDOR));
			}
		});
		proveedor.setToolTipText("MODULO PROVEEDOR");
		botones.add(proveedor);

		//-------------------------------------------
		JButton producto = new JButton("PRODUCTO");
		producto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_PRODUCTO));
			}
		});
		producto.setToolTipText("MODULO PRODUCTO");
		botones.add(producto);

		//-------------------------------------------
		JButton venta = new JButton("VENTA");
		venta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_VENTA));
			}
		});
		venta.setToolTipText("MODULO VENTA");
		botones.add(venta);

		//-------------------------------------------
		JButton departamento = new JButton("DEPARTAMENTO");
		departamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_DEPARTAMENTO));
			}
		});
		departamento.setToolTipText("MODULO DEPARTAMENTO");
		botones.add(departamento);

		//-------------------------------------------
		JButton empleado = new JButton("EMPLEADO");
		empleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ctrl.accion(new Contexto(Evento.VISTA_EMPLEADO));
			}
		});
		empleado.setToolTipText("MODULO EMPLEADO");
		botones.add(empleado);

		//-------------------------------------------
		JButton nuclear = new JButton("nuclear");
		nuclear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO añadir uno de cada entidad a la bbdd
				EMFSingleton emf = EMFSingleton.getInstance();
				EntityManager em = emf.getEMF().createEntityManager();

				em.getTransaction().begin();

				Marca marca = new Marca(new TMarca(2, "nike", "alemania", true));
				Marca marca2 = new Marca(new TMarca(2, "adidas", "alemania", true));
				Producto p = new Producto(new TProducto(1, "cocacola", 5, 2.20, 82872, 1, true));
				Nacional n = new Nacional(new TNacional(0, "Nacional1", true, 12345));

				//Marca
				em.persist(marca);
				em.persist(marca2);

				/*
				//Producto
				List<Proveedor> proveedores = new ArrayList<>();
				proveedores.add(n);
				p.setProveedores(proveedores); // Vincular proveedor y producto
				p.setMarca(marca2);
				em.persist(p);

				//Proveedor nacional
				List<Producto> productos = new ArrayList<>();
				productos.add(p);
				n.setProductos(productos); // Vincular proveedor y producto
				em.persist(n);

				//Departamento y Empleado
				Departamento d = new Departamento(new TDepartamento(0, "Recursos Humanos", 1, 23.5, true));

				Gerente g = new Gerente(new TGerente(0, 1, 160, 1, true, 1, 10));
				g.setDepartamento(d);

				// Añadir el empleado a la lista que tiene departamento
				List<Empleado> empleados = new ArrayList<>();
				empleados.add(g);
				d.setEmpleados(empleados);

				em.persist(d);
				em.persist(g);*/

				em.getTransaction().commit();
				
				em.getTransaction().begin();
				
				List<Marca> resultados = em.createNamedQuery("negocio.marca.Marca.findBynombre", Marca.class)
                        .setParameter("nombre", "nike")
                        .getResultList();
				
				if (resultados.isEmpty()){
					System.out.println("No hay marca con ese nombre");
				} else{
					System.out.println(resultados.get(0).getId());
				}
				

				em.close();
				emf.getEMF().close();
			}
		});
		nuclear.setToolTipText("nuclear");
		botones.add(nuclear);
		//-------------------------------------------

		principal.add(botones, BorderLayout.CENTER);

		principal.add(botones, BorderLayout.CENTER);

		this.setContentPane(principal);
		this.setResizable(true); // no se puede modificar la ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
	}
}
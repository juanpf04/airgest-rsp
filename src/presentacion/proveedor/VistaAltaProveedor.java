package presentacion.proveedor;

import javax.swing.JFrame;
import presentacion.Observador;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

import negocio.proveedor.TProveedor;
import negocio.proveedor.TNacional;
import negocio.proveedor.TInternacional;
 
public class VistaAltaProveedor extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(450, 250);

		Controlador ctrl = Controlador.getInstance();

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Alta Proveedor");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);
		principal.add(funcion);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.LINE_AXIS));
		centro.setAlignmentX(CENTER_ALIGNMENT);
		principal.add(centro);

		JPanel panelAceptar = new JPanel();

		JPanel panelBotones = new JPanel();
		principal.add(panelBotones);
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);

		if (datos == null) {
			JPanel botones = new JPanel();
			botones.setLayout(new GridLayout(0, 1, 8, 8));

			// -------------------------------------------
			JButton gerente = new JButton("NACIONAL");
			gerente.setToolTipText("Aqui das de alta a un proveedor nacional maquina");
			gerente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					ctrl.accion(new Contexto(Evento.VISTA_ALTA_PROVEEDOR, "NACIONAL"));
				}
			});

			gerente.setAlignmentX(CENTER_ALIGNMENT);
			botones.add(gerente);

			// -------------------------------------------
			JButton dependiente = new JButton("INTERNACIONAL");
			dependiente.setToolTipText("Aqui das de alta a un proveedor internacional maquina");
			dependiente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					ctrl.accion(new Contexto(Evento.VISTA_ALTA_PROVEEDOR, "INTERNACIONAL"));
				}
			});

			dependiente.setAlignmentX(CENTER_ALIGNMENT);
			botones.add(dependiente);
			botones.add(panelBotones);

			principal.add(botones);

			// -----------------------------------------------------
		} else {
			JPanel panelEtiquetas = new JPanel();
			panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.PAGE_AXIS));
			panelEtiquetas.setAlignmentX(CENTER_ALIGNMENT);
			
			JPanel panelTexto = new JPanel();
			panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.PAGE_AXIS));
			panelTexto.setAlignmentX(CENTER_ALIGNMENT);

			JLabel etiquetaNombre = new JLabel("Nombre: ");
			etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoNombre = new JTextField();
			textoNombre.setMaximumSize(new Dimension(200, 30));
			textoNombre.setMinimumSize(new Dimension(200, 30));
			textoNombre.setPreferredSize(new Dimension(200, 30));
			textoNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
			panelEtiquetas.add(etiquetaNombre);
			panelTexto.add(textoNombre);
			centro.add(panelEtiquetas);
			centro.add(panelTexto);
			
			JLabel etiquetaCodigoPostal = new JLabel("Codigo Postal:    ");
			etiquetaCodigoPostal.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoCodigoPostal = new JTextField();
			textoCodigoPostal.setMaximumSize(new Dimension(200, 30));
			textoCodigoPostal.setMinimumSize(new Dimension(200, 30));
			textoCodigoPostal.setPreferredSize(new Dimension(200, 30));
			textoCodigoPostal.setFont(new Font("Tahoma", Font.BOLD, 18));
			textoCodigoPostal.setToolTipText("numero de 5 cifras");
			
			JLabel etiquetaImpuesto = new JLabel("Impuesto:    ");
			etiquetaImpuesto.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoImpuesto = new JTextField();
			textoImpuesto.setMaximumSize(new Dimension(200, 30));
			textoImpuesto.setMinimumSize(new Dimension(200, 30));
			textoImpuesto.setPreferredSize(new Dimension(200, 30));
			textoImpuesto.setFont(new Font("Tahoma", Font.BOLD, 18));
			textoImpuesto.setToolTipText("numero a partir de 0");
			
			JLabel etiquetaPais = new JLabel("Pais:    ");
			etiquetaPais.setFont(new Font("Tahoma", Font.BOLD, 25));
			JTextField textoPais = new JTextField();
			textoPais.setMaximumSize(new Dimension(200, 30));
			textoPais.setMinimumSize(new Dimension(200, 30));
			textoPais.setPreferredSize(new Dimension(200, 30));
			textoPais.setFont(new Font("Tahoma", Font.BOLD, 18));

			if ("NACIONAL" == datos) {
				panelEtiquetas.add(etiquetaCodigoPostal);
				panelTexto.add(textoCodigoPostal);
			} else {

				panelEtiquetas.add(etiquetaImpuesto);
				panelTexto.add(textoImpuesto);
				
				panelEtiquetas.add(etiquetaPais);
				panelTexto.add(textoPais);
			}

			JButton aceptar = new JButton("ACEPTAR");
			aceptar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String nombre = textoNombre.getText();
						TProveedor transfer;
						if (datos == "NACIONAL") {
							String codigoPostal = textoCodigoPostal.getText();
							transfer = new TNacional(0, nombre, true, codigoPostal);
						} else {
							double impuesto = Double.valueOf(textoImpuesto.getText());
							String pais = textoPais.getText();
							transfer = new TInternacional(0, nombre, true, pais, impuesto);
						}
						ctrl.accion(new Contexto(Evento.ALTA_PROVEEDOR, transfer));
					} catch (Exception ex) {
						ctrl.accion(new Contexto(Evento.VISTA_FALLO_ALTA_PROVEEDOR));
					}
				}

			});

			panelAceptar.add(aceptar);

		}

		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (datos == null)
					ctrl.accion(new Contexto(Evento.VISTA_PROVEEDOR, null));
				else
					ctrl.accion(new Contexto(Evento.VISTA_ALTA_PROVEEDOR, null));
			}

		});
		panelBotones.add(atras);
		panelBotones.add(panelAceptar);
		
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.pack();
	}
}
/**
 * 
 */
package presentacion.proveedor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import negocio.modeloAerolinea.TModeloAerolinea;
import negocio.personalHangar.TPersonalHangar;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaVincularProveedorProducto extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);

		this.setSize(470, 250);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel centro = new JPanel();

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Vincular Proveedor con Producto");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);

		JPanel panel_etiquetas = new JPanel();
		panel_etiquetas.setLayout(new BoxLayout(panel_etiquetas, BoxLayout.PAGE_AXIS));

		JPanel panel_textfield = new JPanel();
		panel_textfield.setLayout(new BoxLayout(panel_textfield, BoxLayout.PAGE_AXIS));

		JLabel etiquetaIdProveedor = new JLabel("idProveedor: ");
		etiquetaIdProveedor.setFont(new Font("Tahoma", Font.BOLD, 24));
		JTextField textoIdProveedor = new JTextField();
		textoIdProveedor.setMaximumSize(new Dimension(200, 30));
		textoIdProveedor.setMinimumSize(new Dimension(200, 30));
		textoIdProveedor.setPreferredSize(new Dimension(200, 30));
		textoIdProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));

		panel_etiquetas.add(etiquetaIdProveedor);
		panel_textfield.add(textoIdProveedor);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		JPanel idProducto = new JPanel();
		idProducto.setLayout(new BoxLayout(idProducto, BoxLayout.LINE_AXIS));
		JLabel etiquetaIdProducto = new JLabel("idProducto: ");
		etiquetaIdProducto.setFont(new Font("Tahoma", Font.BOLD, 24));
		JTextField textoIdProducto = new JTextField();
		textoIdProducto.setMaximumSize(new Dimension(200, 30));
		textoIdProducto.setMinimumSize(new Dimension(200, 30));
		textoIdProducto.setPreferredSize(new Dimension(200, 30));
		textoIdProducto.setFont(new Font("Tahoma", Font.BOLD, 18));

		panel_etiquetas.add(etiquetaIdProducto);
		panel_textfield.add(textoIdProducto);

		centro.add(panel_etiquetas);
		centro.add(panel_textfield);

		principal.add(funcion);
		principal.add(centro);

		Controlador controlador = Controlador.getInstance();
		JPanel botones = new JPanel();
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int idProveedorLeido = Integer.valueOf(textoIdProveedor.getText());
					int idHangarLeido = Integer.valueOf(textoIdProducto.getText());
					TPersonalHangar transfer = new TPersonalHangar(idProveedorLeido, idHangarLeido);
					controlador.accion(new Contexto(Evento.VINCULAR_PROVEEDOR, transfer));
				} catch (NumberFormatException n) {
					controlador.accion(new Contexto(Evento.VISTA_FALLO_VINCULAR_PROVEEDOR, new TModeloAerolinea())); //creo
				}
			}

		});

		aceptar.setMaximumSize(new Dimension(100, 30));
		aceptar.setPreferredSize(new Dimension(100, 30));

		JButton atras = new JButton("ATRAS"); // boton para volver a la ventana
												// principal
		atras.setToolTipText("Esto vuelve a la ventana anterior");
		atras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				controlador.accion(new Contexto(Evento.VISTA_PERSONAL, null));
			}

		});
		atras.setMaximumSize(new Dimension(90, 30));
		atras.setPreferredSize(new Dimension(90, 30));

		botones.add(atras);
		botones.add(aceptar);
		principal.add(botones);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200, 200);
		this.setResizable(false);
	}
}
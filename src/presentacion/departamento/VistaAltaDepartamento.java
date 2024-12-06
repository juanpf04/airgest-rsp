/**
 * 
 */
package presentacion.departamento;

import java.awt.BorderLayout;
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
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import negocio.departamento.TDepartamento;
import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaAltaDepartamento extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	public void actualizar(Object datos) {
		UtilidadesP.setAirGestRSP(this);
		this.setSize(400, 240);

		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

		JPanel funcion = new JPanel();
		funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

		JPanel panel_titulo = new JPanel();
		JLabel titulo = new JLabel("Alta Departamento");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBorder(new LineBorder(Color.BLACK, 2));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo.add(titulo);

		funcion.add(panel_titulo);

		SpringLayout layout = new SpringLayout();
		JPanel centro = new JPanel();
		centro.setLayout(layout);

		JLabel etiquetaNombre = new JLabel("nombre:");
		etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoNombre = new JTextField();
		textoNombre.setToolTipText("letras");

		textoNombre.setMaximumSize(new Dimension(200, 30));
		textoNombre.setMinimumSize(new Dimension(200, 30));
		textoNombre.setPreferredSize(new Dimension(200, 30));
		textoNombre.setFont(new Font("Tahoma", Font.BOLD, 18));

		centro.add(etiquetaNombre);
		centro.add(textoNombre);
		layout.putConstraint(SpringLayout.WEST, textoNombre, 53, SpringLayout.EAST, etiquetaNombre);

		JLabel etiquetaSala = new JLabel("sala");
		etiquetaSala.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoSala = new JTextField();
		textoSala.setToolTipText("entero");
		textoSala.setMaximumSize(new Dimension(200, 30));
		textoSala.setMinimumSize(new Dimension(200, 30));
		textoSala.setPreferredSize(new Dimension(200, 30));
		textoSala.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel etiquetaSueldoHora = new JLabel("sueldo/hora");
		etiquetaSueldoHora.setFont(new Font("Tahoma", Font.BOLD, 25));
		JTextField textoSueldoHora = new JTextField();
		textoSueldoHora.setToolTipText("double");
		textoSueldoHora.setMaximumSize(new Dimension(200, 30));
		textoSueldoHora.setMinimumSize(new Dimension(200, 30));
		textoSueldoHora.setPreferredSize(new Dimension(200, 30));
		textoSueldoHora.setFont(new Font("Tahoma", Font.BOLD, 18));
		

		layout.putConstraint(SpringLayout.NORTH, etiquetaSala, 2, SpringLayout.SOUTH, etiquetaNombre);
		layout.putConstraint(SpringLayout.NORTH, textoSala, 2, SpringLayout.SOUTH, textoNombre);
		layout.putConstraint(SpringLayout.WEST, textoSala, 108, SpringLayout.EAST, etiquetaSala);
		
		layout.putConstraint(SpringLayout.NORTH, etiquetaSueldoHora, 2, SpringLayout.SOUTH, etiquetaSala);
		layout.putConstraint(SpringLayout.NORTH, textoSueldoHora, 2, SpringLayout.SOUTH, textoSala);
		layout.putConstraint(SpringLayout.WEST, textoSueldoHora, 5, SpringLayout.EAST, etiquetaSueldoHora);

		centro.add(etiquetaSala);
		centro.add(textoSala);
		centro.add(etiquetaSueldoHora);
		centro.add(textoSueldoHora);

		Controlador controlador = Controlador.getInstance();

		principal.add(funcion);
		principal.add(centro);
		JPanel botones = new JPanel();
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String nombreLeido = textoNombre.getText();
					int salaLeida = Integer.valueOf(textoSala.getText());
					double sueldoLeido = Double.valueOf(textoSueldoHora.getText());
					TDepartamento transfer = new TDepartamento(0, nombreLeido, salaLeida, sueldoLeido, true);
					controlador.accion(new Contexto(Evento.ALTA_DEPARTAMENTO, transfer));
					
				}catch(Exception ex){
					controlador.accion(new Contexto(Evento.VISTA_FALLO_ALTA_DEPARTAMENTO));
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
				controlador.accion(new Contexto(Evento.VISTA_DEPARTAMENTO, null));
			}

		});

		atras.setMaximumSize(new Dimension(90, 30));
		atras.setPreferredSize(new Dimension(90, 30));

		botones.add(atras);
		botones.add(aceptar);
		principal.add(botones, BorderLayout.PAGE_END);

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(200, 200);
	}
}
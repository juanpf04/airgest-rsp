package presentacion.producto;

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

import presentacion.Observador;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaModificarIdProducto extends JFrame implements Observador {
    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        // Configuración inicial de la ventana
        this.setSize(460, 180);
        
        // Panel principal que contiene los componentes
        JPanel principal = new JPanel();
        principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

        // Panel de título
        JPanel panel_titulo = new JPanel();
        JLabel titulo = new JLabel("Modificar Producto");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        titulo.setBorder(new LineBorder(Color.BLACK, 2));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel_titulo.add(titulo);

        // Añadimos el panel de título al principal
        principal.add(panel_titulo);

        // Panel central con la caja de texto para el ID del producto
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));

        // Panel para ID
        JPanel id = new JPanel();
        id.setLayout(new BoxLayout(id, BoxLayout.LINE_AXIS));
        JLabel etiquetaId = new JLabel("ID del Producto: ");
        etiquetaId.setFont(new Font("Tahoma", Font.BOLD, 25));
        JTextField textoId = new JTextField();
        textoId.setMaximumSize(new Dimension(200, 30));
        textoId.setMinimumSize(new Dimension(200, 30));
        textoId.setPreferredSize(new Dimension(200, 30));
        textoId.setFont(new Font("Tahoma", Font.BOLD, 18));
        id.add(etiquetaId);
        id.add(textoId);
        centro.add(id);

        // Añadimos el panel central al principal
        principal.add(centro);

        // Instancia del controlador
        Controlador controlador = Controlador.getInstance();

        // Panel para los botones de acción
        JPanel botones = new JPanel();
        JButton aceptar = new JButton("ACEPTAR");
        
        // Acción del botón ACEPTAR
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    int id = Integer.parseInt(textoId.getText());
                    controlador.accion(new Contexto(Evento.MODIFICAR_PRODUCTO_ID, id));
                } catch (NumberFormatException n) {
                    controlador.accion(new Contexto(Evento.MODIFICAR_PRODUCTO_ID, 0));
                }
            }
        });

        aceptar.setMaximumSize(new Dimension(100, 30));
        aceptar.setPreferredSize(new Dimension(100, 30));

        // Botón de atrás para regresar a la vista anterior
        JButton atras = new JButton("ATRAS");
        atras.setToolTipText("Vuelve a la ventana anterior");
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controlador.accion(new Contexto(Evento.VISTA_PRODUCTO, null));
            }
        });

        atras.setMaximumSize(new Dimension(90, 30));
        atras.setPreferredSize(new Dimension(90, 30));

        // Añadimos los botones al panel
        botones.add(atras);
        botones.add(aceptar);
        principal.add(botones);

        // Configuramos la ventana
        this.setContentPane(principal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 200);
        this.setResizable(false);
    }
}

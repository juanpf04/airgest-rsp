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

import negocio.producto.TProducto;
import presentacion.Observador;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaModificarProducto extends JFrame implements Observador {
    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        // Convertimos los datos en un objeto TProducto (producto de transferencia)
        TProducto producto = (TProducto) datos;

        // Configuramos la ventana
        this.setSize(420, 350); // Aumentamos el tamaño de la ventana para los nuevos campos
        JPanel principal = new JPanel();
        principal.setLayout(new BoxLayout(principal, BoxLayout.PAGE_AXIS));

        JPanel funcion = new JPanel();
        funcion.setLayout(new BoxLayout(funcion, BoxLayout.PAGE_AXIS));

        JPanel panel_titulo = new JPanel();
        JLabel titulo = new JLabel("Modificar Producto");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        titulo.setBorder(new LineBorder(Color.BLACK, 2));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel_titulo.add(titulo);
        funcion.add(panel_titulo);

        JPanel panel_etiquetas = new JPanel();
        panel_etiquetas.setLayout(new BoxLayout(panel_etiquetas, BoxLayout.PAGE_AXIS));

        JPanel panel_textfield = new JPanel();
        panel_textfield.setLayout(new BoxLayout(panel_textfield, BoxLayout.PAGE_AXIS));

        // Campo de texto para modificar el nombre del producto
        JLabel etiquetaNombre = new JLabel("Nombre: ");
        etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 25));
        JTextField textoNombre = new JTextField(producto.getNombre());
        textoNombre.setMaximumSize(new Dimension(200, 30));
        textoNombre.setMinimumSize(new Dimension(200, 30));
        textoNombre.setPreferredSize(new Dimension(200, 30));
        textoNombre.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Campo de texto para modificar el stock
        JLabel etiquetaStock = new JLabel("Stock: ");
        etiquetaStock.setFont(new Font("Tahoma", Font.BOLD, 25));
        JTextField textoStock = new JTextField(String.valueOf(producto.getStock()));
        textoStock.setMaximumSize(new Dimension(200, 30));
        textoStock.setMinimumSize(new Dimension(200, 30));
        textoStock.setPreferredSize(new Dimension(200, 30));
        textoStock.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Campo de texto para modificar el precio
        JLabel etiquetaPrecio = new JLabel("Precio: ");
        etiquetaPrecio.setFont(new Font("Tahoma", Font.BOLD, 25));
        JTextField textoPrecio = new JTextField(String.valueOf(producto.getPrecio()));
        textoPrecio.setMaximumSize(new Dimension(200, 30));
        textoPrecio.setMinimumSize(new Dimension(200, 30));
        textoPrecio.setPreferredSize(new Dimension(200, 30));
        textoPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Campo de texto para modificar la referencia (ref)
        JLabel etiquetaRef = new JLabel("Referencia: ");
        etiquetaRef.setFont(new Font("Tahoma", Font.BOLD, 25));
        JTextField textoRef = new JTextField(producto.getRef());
        textoRef.setMaximumSize(new Dimension(200, 30));
        textoRef.setMinimumSize(new Dimension(200, 30));
        textoRef.setPreferredSize(new Dimension(200, 30));
        textoRef.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Campo de texto para modificar el ID de la marca
        JLabel etiquetaIdMarca = new JLabel("ID Marca: ");
        etiquetaIdMarca.setFont(new Font("Tahoma", Font.BOLD, 25));
        JTextField textoIdMarca = new JTextField(String.valueOf(producto.getIdMarca()));
        textoIdMarca.setMaximumSize(new Dimension(200, 30));
        textoIdMarca.setMinimumSize(new Dimension(200, 30));
        textoIdMarca.setPreferredSize(new Dimension(200, 30));
        textoIdMarca.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Agregamos todos los campos de texto al panel de etiquetas y textfields
        panel_etiquetas.add(etiquetaNombre);
        panel_textfield.add(textoNombre);
        
        panel_etiquetas.add(etiquetaStock);
        panel_textfield.add(textoStock);
        
        panel_etiquetas.add(etiquetaPrecio);
        panel_textfield.add(textoPrecio);
        
        panel_etiquetas.add(etiquetaRef);
        panel_textfield.add(textoRef);
        
        panel_etiquetas.add(etiquetaIdMarca);
        panel_textfield.add(textoIdMarca);

        JPanel centro = new JPanel();
        centro.add(panel_etiquetas);
        centro.add(panel_textfield);

        principal.add(funcion);
        principal.add(centro);

        // Controlador
        Controlador controlador = Controlador.getInstance();

        // Panel de botones
        JPanel botones = new JPanel();
        JButton aceptar = new JButton("ACEPTAR");
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Tomamos los nuevos valores de los campos de texto
                    String nombreLeido = textoNombre.getText();
                    int stockLeido = Integer.parseInt(textoStock.getText());
                    double precioLeido = Double.parseDouble(textoPrecio.getText());
                    String refLeido = textoRef.getText();
                    int idMarcaLeido = Integer.parseInt(textoIdMarca.getText());

                    // Creamos un nuevo objeto TProducto con los datos modificados
                    TProducto transfer = new TProducto(producto.getId(), nombreLeido, stockLeido, precioLeido, refLeido, idMarcaLeido, true);
                    controlador.accion(new Contexto(Evento.MODIFICAR_PRODUCTO, transfer));
                    dispose();
                } catch (NumberFormatException n) {
                    dispose();
                    controlador.accion(new Contexto(Evento.MODIFICAR_PRODUCTO, new TProducto()));
                }
            }
        });

        aceptar.setMaximumSize(new Dimension(100, 30));
        aceptar.setPreferredSize(new Dimension(100, 30));

        // Botón de Atrás
        JButton atras = new JButton("ATRÁS");
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

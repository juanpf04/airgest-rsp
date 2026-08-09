package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import negocio.producto.TProducto;
import presentacion.Observador;
import presentacion.UtilidadesP;

public class VistaResultadoConsultarProductosPorProveedor extends JFrame implements Observador {

    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        // Configuración básica de la ventana
        UtilidadesP.setAirGestRSP(this);
        this.setSize(600, 650);

        // Crear el panel principal con BorderLayout
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());

        // Obtener la lista de productos (TProducto) filtrados por proveedor
        @SuppressWarnings("unchecked")
        List<TProducto> productos = (List<TProducto>) datos;

        // Crear una cadena de texto con los detalles de los productos
        StringBuilder s = new StringBuilder();
        for (TProducto producto : productos) {
            s.append(producto.toString()).append("\n");
        }

        // Crear el JTextArea para mostrar los productos
        JTextArea exito = new JTextArea(s.toString());
        exito.setFont(new Font("Tahoma", Font.PLAIN, 20));  // Configuración de fuente
        exito.setEditable(false);  // Evitar la edición del texto

        // Agregar el JTextArea al panel principal
        principal.add(exito, BorderLayout.PAGE_START);

        // Agregar una barra de desplazamiento para el JTextArea
        JScrollPane scroll = new JScrollPane(exito, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        principal.add(scroll, BorderLayout.CENTER);

        // Crear el botón "ATRAS" para volver a la vista anterior
        JButton atras = new JButton("ATRAS");
        atras.setToolTipText("Esto vuelve a la ventana anterior");
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cerrar la ventana actual
            }
        });

        // Agregar el botón al panel principal
        principal.add(atras, BorderLayout.PAGE_END);

        // Configurar la ventana
        this.setContentPane(principal);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  // No cerrar la aplicación al cerrar la ventana
        this.setVisible(true);  // Hacer visible la ventana
        this.setLocation(200, 200);  // Establecer la posición de la ventana
    }
}

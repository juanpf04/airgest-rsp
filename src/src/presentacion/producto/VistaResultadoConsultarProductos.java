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

public class VistaResultadoConsultarProductos extends JFrame implements Observador {

    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        UtilidadesP.setAirGestRSP(this);
        this.setSize(600, 650); // Tamaño de la ventana

        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());

        // Convertir datos a una lista de productos (TProducto)
        @SuppressWarnings("unchecked")
        List<TProducto> productos = (List<TProducto>) datos;

        // Concatenar los detalles de los productos
        StringBuilder s = new StringBuilder();
        for (TProducto producto : productos) {
            s.append(producto.toString()).append("\n");
        }

        // Crear un JTextArea para mostrar todos los productos
        JTextArea exito = new JTextArea(s.toString());
        exito.setFont(new Font("Tahoma", Font.PLAIN, 20));  // Fuente y tamaño del texto
        exito.setEditable(false);  // Hacer que el JTextArea no sea editable

        // Agregar el JTextArea al panel principal
        principal.add(exito, BorderLayout.PAGE_START);

        // Agregar una barra de desplazamiento (scroll) en caso de que haya más productos
        JScrollPane scroll = new JScrollPane(exito, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        principal.add(scroll, BorderLayout.CENTER);

        // Botón para volver a la vista anterior
        JButton atras = new JButton("ATRAS");
        atras.setToolTipText("Esto vuelve a la ventana anterior");
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cerrar la ventana actual
            }
        });

        principal.add(atras, BorderLayout.PAGE_END);  // Agregar el botón en la parte inferior

        // Configuración de la ventana
        this.setContentPane(principal);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  // Cerrar sin terminar la aplicación
        this.setVisible(true);  // Hacerla visible
        this.setLocation(200, 200);  // Establecer la posición en la pantalla
    }
}

package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import negocio.producto.TProducto;
import presentacion.Observador;
import presentacion.UtilidadesP;

public class VistaResultadoConsultarProductoPorId extends JFrame implements Observador {

    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        UtilidadesP.setAirGestRSP(this);

        // Crear un panel principal con BorderLayout
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());

        // Convertir los datos recibidos (TProducto) en una cadena de texto
        String producto = datos == null ? "" : ((TProducto) datos).toString();

        // Crear un JTextArea para mostrar la información del producto
        JTextArea exito = new JTextArea(producto);
        exito.setEditable(false);  // El área de texto no será editable
        exito.setFont(new Font("Tahoma", Font.PLAIN, 26));  // Establecer el tipo y tamaño de la fuente
        exito.setAlignmentX(CENTER_ALIGNMENT);  // Alinear el texto al centro

        // Agregar el JTextArea al panel principal
        principal.add(exito, BorderLayout.PAGE_START);

        // Configurar la ventana
        this.setContentPane(principal);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  // Cerrar la ventana sin salir de la aplicación
        this.setVisible(true);
        this.setLocation(200, 200);  // Establecer la ubicación de la ventana
        this.pack();  // Ajustar el tamaño de la ventana automáticamente según su contenido
    }
}

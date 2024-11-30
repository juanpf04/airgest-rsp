package presentacion.producto;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.Observador;

public class VistaFalloModificarProducto extends JFrame implements Observador {

    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        // Configuración de la ventana
        this.setSize(600, 660);

        // Panel principal
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());

        // Etiqueta para mostrar el mensaje de fallo
        JLabel fallo = new JLabel("Modificación de Producto fallida! :(");
        fallo.setFont(new Font("Tahoma", Font.PLAIN, 29));
        fallo.setHorizontalAlignment(SwingConstants.CENTER);
        principal.add(fallo, BorderLayout.PAGE_START);

        // Etiqueta para mostrar la imagen de fallo
        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon("recursos/iconos/fallo.png"));
        principal.add(imagen, BorderLayout.CENTER);

        // Configuración final
        this.setContentPane(principal);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 200);
        this.setResizable(false);
    }
}

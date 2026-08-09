package presentacion.marca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import presentacion.Observador;
import presentacion.UtilidadesP;
import presentacion.controlador.Contexto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class VistaMarca extends JFrame implements Observador {

    private static final long serialVersionUID = 1L;

    @Override
    public void actualizar(Object datos) {
        UtilidadesP.setAirGestRSP(this);
        this.setSize(430, 390);
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());

        JPanel page_start_panel = new JPanel();
        page_start_panel.setLayout(new BoxLayout(page_start_panel, BoxLayout.PAGE_AXIS));

        JPanel panel_label_marca = new JPanel();
        JLabel marca = new JLabel("MARCA");
        marca.setBorder(new LineBorder(Color.BLACK, 2));
        marca.setFont(new Font("Comic Sans", Font.BOLD, 30));
        marca.setHorizontalAlignment(SwingConstants.CENTER);

        panel_label_marca.add(marca);

        JSeparator separador_marca = new JSeparator(SwingConstants.CENTER);
        separador_marca.setBorder(new MatteBorder(1, 1, 10, 10, Color.BLACK));
        separador_marca.setPreferredSize(new Dimension(0, 2));
        page_start_panel.add(panel_label_marca);
        page_start_panel.add(separador_marca);
        principal.add(page_start_panel, BorderLayout.PAGE_START);

        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(0, 1, 7, 7));

        Controlador ctrl = Controlador.getInstance();

        JButton alta = new JButton("ALTA DE MARCA");
        alta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ctrl.accion(new Contexto(Evento.VISTA_ALTA_MARCA, null));
            }
        });
        alta.setToolTipText("Aquí das de alta una marca");
        botones.add(alta);

        //-------------------------------------------
        JButton baja = new JButton("BAJA DE MARCA");
        baja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ctrl.accion(new Contexto(Evento.VISTA_BAJA_MARCA, null));
            }
        });
        baja.setToolTipText("Aquí das de baja una marca");
        botones.add(baja);

        //-------------------------------------------
        JButton consultarID = new JButton("CONSULTAR MARCA POR ID");

        consultarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ctrl.accion(new Contexto(Evento.VISTA_CONSULTAR_MARCA_POR_ID, null));
            }
        });

        consultarID.setToolTipText("Aquí consultas una marca por ID");
        botones.add(consultarID);

        //-------------------------------------------
        JButton consultarTodos = new JButton("CONSULTAR MARCAS");

        consultarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.accion(new Contexto(Evento.CONSULTAR_MARCAS, null));
            }
        });

        consultarTodos.setToolTipText("Aquí consultas todas las marcas");
        botones.add(consultarTodos);

        //-------------------------------------------
        JButton modificar = new JButton("MODIFICAR MARCA");

        modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ctrl.accion(new Contexto(Evento.VISTA_MODIFICAR_ID_MARCA, null));
            }
        });

        modificar.setToolTipText("Aquí modificas una marca");
        botones.add(modificar);

        principal.add(botones, BorderLayout.CENTER);

        //-------------------------------------------
        JPanel panel_atras = new JPanel();
        JButton atras = new JButton("ATRAS");
        atras.setToolTipText("Esto vuelve a la ventana anterior");
        atras.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ctrl.accion(new Contexto(Evento.VISTA_PRINCIPAL, null));
            }

        });
        panel_atras.add(atras);
        principal.add(panel_atras, BorderLayout.PAGE_END);

        this.setContentPane(principal);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(200, 200);
    }
}

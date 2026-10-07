/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author juans
 */

import controlador.ControladorRectangulo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.Punto;

public class VistaRectangulo extends JFrame implements ActionListener {

    private final ControladorRectangulo controlador;
    private PanelDibujo panelDibujo;

    private JTextField txtX1;
    private JTextField txtY1;
    private JTextField txtX2;
    private JTextField txtY2;
    private JTextField txtDx;
    private JTextField txtDy;
    private JTextField txtFactor;
    private JTextField txtPuntoX;
    private JTextField txtPuntoY;
    private JTextArea areaSalida;

    private JButton btnCrear;
    private JButton btnArea;
    private JButton btnMover;
    private JButton btnEscalar;
    private JButton btnVerificar;
    private JButton btnLimpiar;
    private JButton btnSalir;

    public VistaRectangulo() {
        controlador = new ControladorRectangulo();
        initComponents();
        setTitle("Gestor de Figuras - Rectángulo (MVC)");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        areaSalida.append("Bienvenido. Ingrese las coordenadas del rectángulo y presione Crear.\n");
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // ================== PANEL DE ENTRADAS ==================
        JPanel panelEntradas = new JPanel(new GridLayout(0, 4, 5, 5));

        panelEntradas.add(new JLabel("X1:"));
        txtX1 = new JTextField();
        panelEntradas.add(txtX1);
        panelEntradas.add(new JLabel("Y1:"));
        txtY1 = new JTextField();
        panelEntradas.add(txtY1);

        panelEntradas.add(new JLabel("X2:"));
        txtX2 = new JTextField();
        panelEntradas.add(txtX2);
        panelEntradas.add(new JLabel("Y2:"));
        txtY2 = new JTextField();
        panelEntradas.add(txtY2);

        panelEntradas.add(new JLabel("dx (mover):"));
        txtDx = new JTextField();
        panelEntradas.add(txtDx);
        panelEntradas.add(new JLabel("dy (mover):"));
        txtDy = new JTextField();
        panelEntradas.add(txtDy);

        panelEntradas.add(new JLabel("Factor escala:"));
        txtFactor = new JTextField();
        panelEntradas.add(txtFactor);
        panelEntradas.add(new JLabel("Punto x:"));
        txtPuntoX = new JTextField();
        panelEntradas.add(txtPuntoX);

        panelEntradas.add(new JLabel("Punto y:"));
        txtPuntoY = new JTextField();
        panelEntradas.add(txtPuntoY);
        panelEntradas.add(new JLabel(""));
        panelEntradas.add(new JLabel(""));

        add(panelEntradas, BorderLayout.NORTH);

        // ================== PANEL CENTRAL (DIBUJO + TEXTO) ==================
        panelDibujo = new PanelDibujo();

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                panelDibujo,
                new JScrollPane(areaSalida)
        );
        splitPane.setDividerLocation(320);
        splitPane.setResizeWeight(0.6);

        add(splitPane, BorderLayout.CENTER);

        // ================== PANEL DE BOTONES ==================
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnCrear = new JButton("Crear rectángulo");
        btnArea = new JButton("Calcular área");
        btnMover = new JButton("Mover");
        btnEscalar = new JButton("Escalar");
        btnVerificar = new JButton("Verificar punto");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");

        btnCrear.addActionListener(this);
        btnArea.addActionListener(this);
        btnMover.addActionListener(this);
        btnEscalar.addActionListener(this);
        btnVerificar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnSalir.addActionListener(this);

        panelBotones.add(btnCrear);
        panelBotones.add(btnArea);
        panelBotones.add(btnMover);
        panelBotones.add(btnEscalar);
        panelBotones.add(btnVerificar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnCrear) {
                double x1 = leerDouble(txtX1, "X1");
                double y1 = leerDouble(txtY1, "Y1");
                double x2 = leerDouble(txtX2, "X2");
                double y2 = leerDouble(txtY2, "Y2");
                mostrar(controlador.crearRectangulo(x1, y1, x2, y2));

                // Actualizar dibujo
                panelDibujo.setRectangulo(controlador.getRectangulo());
                panelDibujo.setPuntoPrueba(null);

            } else if (e.getSource() == btnArea) {
                mostrar(controlador.calcularArea());

            } else if (e.getSource() == btnMover) {
                double dx = leerDouble(txtDx, "dx");
                double dy = leerDouble(txtDy, "dy");
                mostrar(controlador.mover(dx, dy));

                // Actualizar dibujo
                panelDibujo.setRectangulo(controlador.getRectangulo());

            } else if (e.getSource() == btnEscalar) {
                double factor = leerDouble(txtFactor, "Factor");
                mostrar(controlador.escalar(factor));

                // Actualizar dibujo
                panelDibujo.setRectangulo(controlador.getRectangulo());

            } else if (e.getSource() == btnVerificar) {
                double px = leerDouble(txtPuntoX, "Punto x");
                double py = leerDouble(txtPuntoY, "Punto y");
                mostrar(controlador.verificarPunto(px, py));

                // Actualizar dibujo con el punto
                Punto p = new Punto(px, py);
                panelDibujo.setPuntoPrueba(p);

            } else if (e.getSource() == btnLimpiar) {
                limpiar();
                panelDibujo.setRectangulo(null);
                panelDibujo.setPuntoPrueba(null);

            } else if (e.getSource() == btnSalir) {
                System.exit(0);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dato inválido", JOptionPane.ERROR_MESSAGE);
            mostrar("Error: " + ex.getMessage());
        }
    }

    private double leerDouble(JTextField campo, String nombre) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombre + " no puede estar vacío.");
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo " + nombre + " debe ser un número válido.");
        }
    }

    private void mostrar(String mensaje) {
        areaSalida.append(mensaje + "\n");
        areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
    }

    private void limpiar() {
        txtX1.setText("");
        txtY1.setText("");
        txtX2.setText("");
        txtY2.setText("");
        txtDx.setText("");
        txtDy.setText("");
        txtFactor.setText("");
        txtPuntoX.setText("");
        txtPuntoY.setText("");
        areaSalida.setText("");
    }
}
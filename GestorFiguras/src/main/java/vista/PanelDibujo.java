/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author juans
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import modelo.Punto;
import modelo.Rectangulo;

public class PanelDibujo extends JPanel {

    private Rectangulo rectangulo;
    private Punto puntoPrueba;

    public PanelDibujo() {
        setBackground(Color.WHITE);
    }

    public void setRectangulo(Rectangulo rectangulo) {
        this.rectangulo = rectangulo;
        repaint(); // Vuelve a dibujar
    }

    public void setPuntoPrueba(Punto puntoPrueba) {
        this.puntoPrueba = puntoPrueba;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (rectangulo != null) {
            // Calcular coordenadas para dibujar
            int x1 = (int) Math.min(rectangulo.getPunto1().getX(), rectangulo.getPunto2().getX());
            int y1 = (int) Math.min(rectangulo.getPunto1().getY(), rectangulo.getPunto2().getY());
            int ancho = (int) rectangulo.getBase();
            int alto = (int) rectangulo.getAltura();

            // Dibujar rectángulo (azul)
            g2.setColor(Color.BLUE);
            g2.drawRect(x1, y1, ancho, alto);
            
            // Dibujar un poco de relleno transparente para que se vea mejor
            g2.setColor(new Color(0, 0, 255, 30));
            g2.fillRect(x1, y1, ancho, alto);

            // Dibujar los vértices (puntos rojos)
            g2.setColor(Color.RED);
            g2.fillOval((int) rectangulo.getPunto1().getX() - 3, (int) rectangulo.getPunto1().getY() - 3, 6, 6);
            g2.fillOval((int) rectangulo.getPunto2().getX() - 3, (int) rectangulo.getPunto2().getY() - 3, 6, 6);
        }

        if (puntoPrueba != null) {
            // Dibujar el punto de prueba (verde)
            g2.setColor(Color.GREEN);
            g2.fillOval((int) puntoPrueba.getX() - 5, (int) puntoPrueba.getY() - 5, 10, 10);
            g2.setColor(Color.BLACK);
            g2.drawString("P", (int) puntoPrueba.getX() + 8, (int) puntoPrueba.getY());
        }
    }
}

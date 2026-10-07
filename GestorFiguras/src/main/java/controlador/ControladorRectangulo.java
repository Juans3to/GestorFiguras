/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author juans
 */

import modelo.Rectangulo;
import modelo.Punto;

public class ControladorRectangulo {
    private Rectangulo rectangulo;

    public ControladorRectangulo() {
        this.rectangulo = null;
    }

    public boolean hayRectangulo() {
        return rectangulo != null;
    }

    public String crearRectangulo(double x1, double y1, double x2, double y2) {
        rectangulo = new Rectangulo(x1, y1, x2, y2);
        return "Rectángulo creado correctamente.\n" + rectangulo.toString();
    }

    public String calcularArea() {
        if (!hayRectangulo()) {
            return "Error: primero debe crear un rectángulo.";
        }
        return "Área del rectángulo: " + rectangulo.calcularArea();
    }

    public String mover(double dx, double dy) {
        if (!hayRectangulo()) {
            return "Error: primero debe crear un rectángulo.";
        }
        rectangulo.mover(dx, dy);
        return "Rectángulo movido.\n" + rectangulo.toString();
    }

    public String escalar(double factor) {
        if (!hayRectangulo()) {
            return "Error: primero debe crear un rectángulo.";
        }
        try {
            rectangulo.escalar(factor);
            return "Rectángulo escalado.\n" + rectangulo.toString();
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String verificarPunto(double x, double y) {
        if (!hayRectangulo()) {
            return "Error: primero debe crear un rectángulo.";
        }
        Punto p = new Punto(x, y);
        boolean dentro = rectangulo.contienePunto(p);
        if (dentro) {
            return "El punto " + p + " SÍ está dentro del rectángulo.";
        } else {
            return "El punto " + p + " NO está dentro del rectángulo.";
        }
    }

    public Rectangulo getRectangulo() {
        return rectangulo;
    }
}

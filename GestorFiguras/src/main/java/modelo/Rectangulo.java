/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juans
 */

public class Rectangulo extends FiguraGeometrica {
    private Punto punto1;
    private Punto punto2;

    public Rectangulo(Punto punto1, Punto punto2) {
        this.punto1 = new Punto(punto1.getX(), punto1.getY());
        this.punto2 = new Punto(punto2.getX(), punto2.getY());
    }

    public Rectangulo(double x1, double y1, double x2, double y2) {
        this(new Punto(x1, y1), new Punto(x2, y2));
    }

    public Punto getPunto1() {
        return punto1.copia();
    }

    public Punto getPunto2() {
        return punto2.copia();
    }

    public double getBase() {
        return Math.abs(punto2.getX() - punto1.getX());
    }

    public double getAltura() {
        return Math.abs(punto2.getY() - punto1.getY());
    }

    @Override
    public double calcularArea() {
        return getBase() * getAltura();
    }

    @Override
    public void mover(double dx, double dy) {
        punto1.mover(dx, dy);
        punto2.mover(dx, dy);
    }

    public void escalar(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("El factor de escala debe ser mayor que cero.");
        }
        // Se escala manteniendo fijo el punto1.
        double nuevoX2 = punto1.getX() + (punto2.getX() - punto1.getX()) * factor;
        double nuevoY2 = punto1.getY() + (punto2.getY() - punto1.getY()) * factor;
        punto2.setX(nuevoX2);
        punto2.setY(nuevoY2);
    }

    public boolean contienePunto(Punto p) {
        double minX = Math.min(punto1.getX(), punto2.getX());
        double maxX = Math.max(punto1.getX(), punto2.getX());
        double minY = Math.min(punto1.getY(), punto2.getY());
        double maxY = Math.max(punto1.getY(), punto2.getY());

        return p.getX() >= minX && p.getX() <= maxX
                && p.getY() >= minY && p.getY() <= maxY;
    }

    @Override
    public String toString() {
        return "Rectángulo [p1=" + punto1
                + ", p2=" + punto2
                + ", base=" + getBase()
                + ", altura=" + getAltura()
                + ", área=" + calcularArea() + "]";
    }
}

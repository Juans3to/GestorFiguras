/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juans
 */

public class Punto {
    private double x;
    private double y;

    public Punto() {
        this(0, 0);
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void mover(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public Punto copia() {
        return new Punto(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
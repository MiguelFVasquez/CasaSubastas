package co.edu.uniquindio.pr3.subastas.model;

public class Puja {
    private double DineroOfrecido;

    public Puja(double dineroOfrecido) {
        DineroOfrecido = dineroOfrecido;
    }

    public Puja() {
    }

    public double getDineroOfrecido() {
        return DineroOfrecido;
    }

    public void setDineroOfrecido(double dineroOfrecido) {
        DineroOfrecido = dineroOfrecido;
    }

    @Override
    public String toString() {
        return "Puja{" +
                "DineroOfrecido=" + DineroOfrecido +
                '}';
    }


}

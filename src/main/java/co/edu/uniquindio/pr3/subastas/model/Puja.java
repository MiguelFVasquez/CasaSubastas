package co.edu.uniquindio.pr3.subastas.model;

import java.util.Objects;

public class Puja {
    private Anuncio anuncio;
    private Comprador comprador;
    private Double valor;
    private String fecha;

    public Puja() {
    }

    public Puja(Anuncio anuncio, Comprador comprador, Double valor, String fecha) {
        this.anuncio = anuncio;
        this.comprador = comprador;
        this.valor = valor;
        this.fecha = fecha;
    }


    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puja puja = (Puja) o;
        return Objects.equals(anuncio, puja.anuncio) && Objects.equals(comprador, puja.comprador) && Objects.equals(valor, puja.valor) && Objects.equals(fecha, puja.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anuncio, comprador, valor, fecha);
    }

    @Override
    public String toString() {
        return "Puja{" +
                "anuncio=" + anuncio +
                ", comprador=" + comprador +
                ", valor=" + valor +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}

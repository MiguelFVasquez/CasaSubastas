package co.edu.uniquindio.pr3.subastas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Puja implements Serializable {
    private static final long serialVersionUID = 1l;
    private Anuncio anuncio;
    private Comprador comprador;
    private Double valor;
    private LocalDate fecha;
    private String codigo;

    public Puja() {
    }

    public Puja(Anuncio anuncio, Comprador comprador, Double valor, LocalDate fecha, String codigo) {
        this.anuncio = anuncio;
        this.comprador = comprador;
        this.valor = valor;
        this.fecha = fecha;
        this.codigo=codigo;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

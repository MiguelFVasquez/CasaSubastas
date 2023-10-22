package co.edu.uniquindio.pr3.subastas.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Serializable {
    private static final long serialVersionUID = 1l;
    private String codigo;
    private String fechaInicio;
    private String fechaFinal;
    private String nombreAnunciante;
    private Producto producto;
    private List<Puja> listaPujas= new ArrayList<>();

    public Anuncio() {
    }

    public Anuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.nombreAnunciante = nombreAnunciante;
        this.producto = producto;
    }

    public Anuncio(String codigo, String fechaInicio, String fechaFinal,
                   String nombreAnunciante, Producto producto,
                   List<Puja> listaPujas) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.nombreAnunciante = nombreAnunciante;
        this.producto = producto;
        this.listaPujas = new ArrayList<>();
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    public void setProducto(String s) {
    }

    @Override
    public String toString() {
        return "Anuncio: " +
                "\ncodigo='" + codigo + '\'' +
                "\nfechaInicio:'" + fechaInicio + '\'' +
                "\nfechaFinal:'" + fechaFinal + '\'' +
                "\nnombreAnunciante:'" + nombreAnunciante + '\'' +
                "\nproducto:" + producto ;
    }
}

package co.edu.uniquindio.pr3.subastas.model;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1l;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Image imagen;
    private String valorInicial;
    private TipoProducto tipoProducto;
    private Boolean estaAnunciado;


    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, Image imagen, String valorInicial, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.valorInicial = valorInicial;
        this.tipoProducto = tipoProducto;
    }

    public Producto(String codigo, String nombre, String descripcion, Image imagen,
                    String valorInicial, TipoProducto tipoProducto, Boolean estaAnunciado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.valorInicial = valorInicial;
        this.tipoProducto = tipoProducto;
        this.estaAnunciado = estaAnunciado;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Boolean getAnunciado() {
        return estaAnunciado;
    }

    public void setAnunciado(Boolean anunciado) {
        estaAnunciado = anunciado;
    }

    @Override
    public String toString() {
        return "Producto" +
                "codigo: '" + codigo +
                "\nnombre:'" + nombre +
                "\ndescripcion: '" + descripcion +
                "\nvalorInicial: '" + valorInicial +
                "\ntipoProducto: " + tipoProducto +
                "\nestaAnunciado: " + estaAnunciado ;
    }
}

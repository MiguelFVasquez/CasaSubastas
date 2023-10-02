package co.edu.uniquindio.pr3.subastas.model;

import co.edu.uniquindio.pr3.subastas.model.Interfaces.IAnunciante;

import java.util.List;

public class Anunciante extends Usuario implements IAnunciante {
    private int cantidadAnuncios;

    private List<Anuncio> listaAnuncios;
    private List<Producto> listaProductos;

    //Constructores
    public Anunciante() {
    }

    public Anunciante(int cantidadAnuncios, List<Anuncio> listaAnuncios,
                      List<Producto> listaProductos) {
        this.cantidadAnuncios = cantidadAnuncios;
        this.listaAnuncios = listaAnuncios;
        this.listaProductos = listaProductos;
    }

    public Anunciante(String nombres, String apellidos, String identificacion, int edad,
                      String nombreUsuario, String correo, String contrasenia,
                      TipoUsuario tipoUsuario, boolean autenticado, int cantidadAnuncios,
                      List<Anuncio> listaAnuncios, List<Producto> listaProductos) {
        super(nombres, apellidos, identificacion, edad, nombreUsuario, correo, contrasenia, tipoUsuario, autenticado);
        this.cantidadAnuncios = cantidadAnuncios;
        this.listaAnuncios = listaAnuncios;
        this.listaProductos = listaProductos;
    }


    //Getters y Setters

    public int getCantidadAnuncios() {
        return cantidadAnuncios;
    }

    public void setCantidadAnuncios(int cantidadAnuncios) {
        this.cantidadAnuncios = cantidadAnuncios;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "cantidadAnuncios=" + cantidadAnuncios +
                '}';
    }

    @Override
    public boolean crearAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto) {
        return false;
    }

    @Override
    public boolean actualizarAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto) {
        return false;
    }

    @Override
    public boolean eliminarAnuncio(String codigo) {
        return false;
    }

    @Override
    public boolean crearProducto(String codigo, String nombre, String descripcion, String imagen, Double valorInicial, TipoProducto tipoProducto) {
        return false;
    }

    @Override
    public boolean actualizarProducto(String codigo, String nombre, String descripcion, String imagen, Double valorInicial, TipoProducto tipoProducto) {
        return false;
    }

    @Override
    public boolean eliminarProducto(String codigo) {
        return false;
    }
}

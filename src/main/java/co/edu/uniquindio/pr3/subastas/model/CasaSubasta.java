package co.edu.uniquindio.pr3.subastas.model;

import java.util.ArrayList;
import java.util.List;

public class CasaSubasta {

    private String nombre;
    private List<Comprador> listaCompradores= new ArrayList<>();
    private List<Anunciante> listaAnunciantes= new ArrayList<>();
    private List<Usuario> listaUsuarios= new ArrayList<>();
    private  List<Producto> listaProductos= new ArrayList<>();
    private List<Anuncio> listaAnuncios= new ArrayList<>();

    //Constructores
    public CasaSubasta(String nombre) {
        this.nombre = nombre;
        this.listaCompradores= new ArrayList<Comprador>();
        this.listaAnunciantes=new ArrayList<Anunciante>();
        this.listaUsuarios= new ArrayList<Usuario>();
        this.listaProductos= new ArrayList<Producto>();
        this.listaAnuncios= new ArrayList<Anuncio>();
    }

    public CasaSubasta() {
        this.listaCompradores= new ArrayList<Comprador>();
        this.listaAnunciantes=new ArrayList<Anunciante>();
        this.listaUsuarios= new ArrayList<Usuario>();
        this.listaProductos= new ArrayList<Producto>();
        this.listaAnuncios= new ArrayList<Anuncio>();
    }

    //Metodos getters and setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Comprador> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(List<Comprador> listaCompradores) {
        this.listaCompradores = listaCompradores;
    }

    public List<Anunciante> getListaAnunciantes() {
        return listaAnunciantes;
    }

    public void setListaAnunciantes(List<Anunciante> listaAnunciantes) {
        this.listaAnunciantes = listaAnunciantes;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }


}

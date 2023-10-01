package co.edu.uniquindio.pr3.subastas.model;

import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.Interfaces.ISubasta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CasaSubasta implements ISubasta {

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

    /**
     *
     * @param usuario
     * @param contrasenia
     * @return true, si la lista "UsuariosIdenticos" NO está vacia, esto significa que encontro un usuario con el mismo nombreDeusuario y contraseña y lo añadio a la lista
     * @return false, si la lista "UsuariosIdenticos" permance vacia, ya que no encontraria ningun usuario con el mismo nombreDeUsuario y contraseña
     */
    @Override
    public boolean verificarUsuario(String usuario, String contrasenia) {
        boolean encontrado= false;
        List<Usuario> usuariosIdenticos = this.listaUsuarios.stream()
                .filter(u-> u.getNombreUsuario().equals(usuario) && u.getContrasenia().equals(contrasenia))
                .collect(Collectors.toList());
                ;
        if(!usuariosIdenticos.isEmpty()) {
            encontrado = true;
        }

        return encontrado;
    }
    //----------------------CRUD DEL COMPRADOR--------------------------------------------------

    @Override
    public Comprador obtenerComprador(String usuario, String contrasenia) {
        return (Comprador) listaCompradores.stream().filter(c-> c.getNombreUsuario().equals(usuario) && c.getContrasenia().equals(contrasenia));
    }

    @Override
    public boolean crearComprador(Comprador newComprador) throws UsuarioException, CompradorException {
        boolean creado= false;
        if (verificarUsuario(newComprador.getNombreUsuario(),newComprador.getContrasenia())){
            throw new CompradorException("Este Usuario ya se encuentra registrado");
        }else {
            creado=true;
            listaUsuarios.add(newComprador);
            listaCompradores.add(newComprador);
        }
        return creado;
    }

    @Override
    public boolean actualizarComprador(String nombre, String apellido, String identificacion, int edad, String nombreUsuario, String correo, String contrasenia, boolean autenticado) {
        return false;
    }

    @Override
    public boolean eliminarComprador(String identificacion) {
        return false;
    }

    @Override
    public Anunciante obtenerAnunciante(String identificacion) {
        return null;
    }

    //----------------------CRUD DEL ANUNCIANTE--------------------------------------------------
    @Override
    public boolean crearAnunciante(String nombre, String apellido, String identificacion, int edad, String nombreUsuario, String correo, String contrasenia, boolean autenticado) {
        return false;
    }

    @Override
    public boolean actualizarAnunciante(String nombre, String apellido, String identificacion, int edad, String nombreUsuario, String correo, String contrasenia, boolean autenticado) {
        return false;
    }

    @Override
    public boolean eliminarAnunciante(String identificacion) {
        return false;
    }




}

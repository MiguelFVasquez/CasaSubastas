package co.edu.uniquindio.pr3.subastas.model;

import co.edu.uniquindio.pr3.subastas.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastas.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastas.model.Interfaces.IAnunciante;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Anunciante extends Usuario implements IAnunciante, Serializable {

    private static final long serialVersionUID = 1l;
    private int cantidadAnuncios;
    private List<Anuncio> listaAnuncios= new ArrayList<>();
    private List<Producto> listaProductos= new ArrayList<>();

    //Constructores
    public Anunciante() {
    }

    public Anunciante(int cantidadAnuncios, List<Anuncio> listaAnuncios,
                      List<Producto> listaProductos) {
        this.cantidadAnuncios = cantidadAnuncios;
        this.listaAnuncios = listaAnuncios;
        this.listaProductos = listaProductos;
    }

    public Anunciante(String nombres, String apellidos, String identificacion, String edad,
                      String nombreUsuario, String correo, String contrasenia,
                      TipoUsuario tipoUsuario, boolean autenticado, int cantidadAnuncios) {
        super(nombres, apellidos, identificacion, edad, nombreUsuario, correo, contrasenia, tipoUsuario, autenticado);
        this.cantidadAnuncios = cantidadAnuncios;

    }

    public Anunciante(String nombres, String apellidos, String identificacion, String edad, String nombreUsuario, String correo,
                      String contrasenia, int cantidadAnuncios) {
        super(nombres, apellidos, identificacion, edad, nombreUsuario, correo, contrasenia);
        this.listaAnuncios = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.cantidadAnuncios = cantidadAnuncios;
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

    //----------------METODOS DE LOS ANUNCIOS(CRUD)-------------------

    /**
     *
     * @param codigo
     * @return
     */
    public boolean verificarAnuncio(String codigo){
        boolean encontrado= false;
        List<Anuncio> anunciosIguales= this.listaAnuncios.stream()
                .filter(a->a.getCodigo().equals(codigo))
                .collect(Collectors.toList());
        if (!anunciosIguales.isEmpty()){
            encontrado=true;
        }
        return encontrado;
    }


    /**
     *
     * @param codigo
     * @return un anuncio que coincida con el codigo, si no lo encuentra retorna null
     */
    public Anuncio obtenerAnuncio(String codigo) {
        Optional<Anuncio> anuncioOptional = listaAnuncios.stream()
                .filter(a -> a.getCodigo().equals(codigo))
                .findFirst();

        return anuncioOptional.orElse(null);
    }

    /**
     *
     * @param newAnuncio
     * @return
     * @throws AnuncioException
     */
    @Override
    public boolean crearAnuncio(Anuncio newAnuncio) throws AnuncioException,ProductoException {
        boolean creado= false;

        if (verificarAnuncio(newAnuncio.getCodigo())){
            throw new AnuncioException("Ya existe un anuncio con el código: "+ newAnuncio.getCodigo());
        }
        else if(!verificarProducto(newAnuncio.getProducto().getCodigo())){//SI el producto que se quiere anunciar no existe no se
                                                                                        // podra crear un anuncio
            throw new ProductoException("El producto que quiere anunciar no existe");
        }
        else if (newAnuncio.getProducto().getEstaAnunciado()){
            throw new ProductoException("El producto ya se encuentra anunciado");//Si el producto ya se encuentra anunciado no se puede volver a anunciar
        }
        else {
            newAnuncio.getProducto().setEstaAnunciado(true);
            creado=true;
            listaAnuncios.add(newAnuncio);
        }

        return creado;
    }

    /**
     *
     * @param anuncioActualizar
     * @return
     * @throws AnuncioException
     */
    @Override
    public boolean actualizarAnuncio(Anuncio anuncioActualizar) throws AnuncioException {
        boolean actualizado= false;
        Anuncio anuncioAux= obtenerAnuncio(anuncioActualizar.getCodigo());
        if (anuncioAux==null){
            throw new AnuncioException("El anuncio con código: "+anuncioActualizar.getCodigo()+" no ha sido encontrado");
        }else {
            actualizado=true;
            anuncioAux.setFechaInicio(anuncioActualizar.getFechaInicio());
            anuncioAux.setFechaFinal(anuncioActualizar.getFechaFinal());
            anuncioAux.setNombreAnunciante(anuncioActualizar.getNombreAnunciante());
        }
        return actualizado;
    }

    /**
     *
     * @param anuncioEliminar
     * @return
     * @throws AnuncioException
     */
    @Override
    public boolean eliminarAnuncio(Anuncio anuncioEliminar) throws AnuncioException{
        boolean eliminado= false;
        Anuncio anuncioAux= obtenerAnuncio(anuncioEliminar.getCodigo());
        if (anuncioAux==null){
            throw new AnuncioException("El anuncio que desea eliminar no se ha sido encontrado");
        }else {
            eliminado=true;
            anuncioEliminar.getProducto().setEstaAnunciado(false);
            anuncioEliminar.getListaPujas().clear();
            listaAnuncios.remove(anuncioAux);
        }
        return eliminado;
    }


    //--------------------METODOS DE LOS PRODUCTOS(CRUD)------------------------------
    /**
     *
     * @param codigo
     * @return
     */
    public boolean verificarProducto(String codigo){
        boolean encontrado = false;
        List<Producto> productosIguales= this.listaProductos.stream()
                .filter(p->p.getCodigo().equals(codigo))
                .collect(Collectors.toList());

        if (!productosIguales.isEmpty()){
            encontrado=true;
        }
        return encontrado;
    }

    /**
     *
     * @param codigo
     * @return
     */
    public Producto obtenerProducto(String codigo){
        Optional<Producto> productoOptional = listaProductos.stream()
                .filter(p->p.getCodigo().equals(codigo))
                .findFirst();
        return productoOptional.orElse(null);
    }


    @Override
    public boolean crearProducto(Producto newProducto) throws ProductoException {
        boolean creado= false;

        if (verificarProducto(newProducto.getCodigo())){
            throw new ProductoException("El producto con código: "+ newProducto.getCodigo()+" ya se encuentra registrado");
        }else{
            creado=true;
            this.listaProductos.add(newProducto);
        }
        return creado;
    }

    @Override
    public boolean actualizarProducto(Producto productoActualizar) throws ProductoException {
        boolean actualizado= false;
        Producto productoAux= obtenerProducto(productoActualizar.getCodigo());
        if (productoAux==null){
            throw new ProductoException("El producto con código: "+ productoActualizar.getCodigo()+" no ha sido encontrado");
        }else {
            actualizado=true;
            productoAux.setNombre(productoActualizar.getNombre());
            productoAux.setDescripcion(productoActualizar.getDescripcion());
            productoAux.setImagen(productoActualizar.getImagen());
        }
        return actualizado;

    }

    @Override
    public boolean eliminarProducto(Producto productoEliminar) throws ProductoException {
        boolean eliminado= false;
        Producto productoAux= obtenerProducto(productoEliminar.getCodigo());
        if (productoAux==null){
            throw new ProductoException("El producto con el código proporcionado no existe");
        }else {
            eliminado=true;
            listaProductos.remove(productoAux);
        }
        return eliminado;
    }


}

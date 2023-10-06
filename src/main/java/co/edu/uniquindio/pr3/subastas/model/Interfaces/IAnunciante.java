package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastas.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import co.edu.uniquindio.pr3.subastas.model.Producto;
import co.edu.uniquindio.pr3.subastas.model.TipoProducto;

public interface IAnunciante {
    //Metodos de los anuncios
    public boolean crearAnuncio(Anuncio newAnuncio) throws AnuncioException, ProductoException;
    public boolean actualizarAnuncio(Anuncio anuncioActualizar) throws AnuncioException;
    public boolean eliminarAnuncio(Anuncio anuncioEliminar) throws AnuncioException;
    //Metodos de los productos
    public boolean crearProducto(Producto newProducto) throws ProductoException;
    public boolean actualizarProducto(Producto productoActualizar) throws ProductoException;
    public boolean eliminarProducto(Producto productoEliminar) throws ProductoException;


}

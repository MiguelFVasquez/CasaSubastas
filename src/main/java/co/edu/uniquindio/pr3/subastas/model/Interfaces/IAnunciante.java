package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.model.Producto;
import co.edu.uniquindio.pr3.subastas.model.TipoProducto;

public interface IAnunciante {
    //Metodos de los anuncios
    public boolean crearAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto);
    public boolean actualizarAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto);
    public boolean eliminarAnuncio(String codigo);
    //Metodos de los productos
    public boolean crearProducto(String codigo, String nombre, String descripcion, String imagen,
                                 Double valorInicial, TipoProducto tipoProducto);
    public boolean actualizarProducto(String codigo, String nombre, String descripcion, String imagen,
                                      Double valorInicial, TipoProducto tipoProducto);
    public boolean eliminarProducto(String codigo);


}

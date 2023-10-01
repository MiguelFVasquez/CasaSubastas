package co.edu.uniquindio.pr3.subastas.mapping.DTO;

import co.edu.uniquindio.pr3.subastas.model.TipoProducto;

public record ProductoDTO (String codigo,
                          String nombre,
                          String descripcion,
                          String imagen,
                          Double valorInicial,
                          TipoProducto tipoProducto,
                          Boolean estaAnunciado){
}

package co.edu.uniquindio.pr3.subastas.mapping.mappers;

import co.edu.uniquindio.pr3.subastas.mapping.DTO.*;
import co.edu.uniquindio.pr3.subastas.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

import java.util.List;

@Mapper
public interface SubastaMapper {

    SubastaMapper INSTANCE = Mappers.getMapper(SubastaMapper.class);


    @Named("productoToProductoDTO")
    ProductoDTO productoToProductoDTO(Producto producto);
    Producto productoDTOtoProducto(ProductoDTO productoDTO);
    @IterableMapping(qualifiedByName = "productoToProductoDTO")
    List<ProductoDTO> getProductosDTO(List<Producto> listaProductos);
}

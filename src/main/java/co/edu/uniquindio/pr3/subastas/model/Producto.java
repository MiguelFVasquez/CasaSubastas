package co.edu.uniquindio.pr3.subastas.model;

public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double valorInicial;
    private TipoProducto tipoProducto;
    private Boolean estaAnunciado;

    public Producto() {
    }
     public Producto(String codigo, String nombre, String descripcion, String imagen,
                    Double valorInicial, TipoProducto tipoProducto, Boolean estaAnunciado) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
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
}

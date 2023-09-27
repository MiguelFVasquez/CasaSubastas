package co.edu.uniquindio.pr3.subastas.model;

public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String image;
    private Double valorInicial;
    private TipoProducto tipoProducto;
    private Boolean isAnunciado;

    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, String image,
                    Double valorInicial, TipoProducto tipoProducto, Boolean isAnunciado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.image = image;
        this.valorInicial = valorInicial;
        this.tipoProducto = tipoProducto;
        this.isAnunciado = isAnunciado;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return isAnunciado;
    }

    public void setAnunciado(Boolean anunciado) {
        isAnunciado = anunciado;
    }
}

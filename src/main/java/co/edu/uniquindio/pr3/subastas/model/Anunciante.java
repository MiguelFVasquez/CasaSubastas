package co.edu.uniquindio.pr3.subastas.model;

public class Anunciante extends Persona{
    private int cantidadAnuncios;

    public Anunciante(int cantidadAnuncios) {
        this.cantidadAnuncios = cantidadAnuncios;
    }

    public Anunciante(String nombres, String apellidos, String identificacion, int edad, int cantidadAnuncios) {
        super(nombres, apellidos, identificacion, edad);
        this.cantidadAnuncios = cantidadAnuncios;
    }

    public int getCantidadAnuncios() {
        return cantidadAnuncios;
    }

    public void setCantidadAnuncios(int cantidadAnuncios) {
        this.cantidadAnuncios = cantidadAnuncios;
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "cantidadAnuncios=" + cantidadAnuncios +
                '}';
    }

}

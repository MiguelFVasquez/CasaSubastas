package co.edu.uniquindio.pr3.subastas.model;

import java.util.Objects;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String identificacion;
    private Integer edad;


    public Persona() {
    }
    public Persona(String nombre, String apellido, String identificacion, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion=identificacion;
        this.edad=edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(identificacion, persona.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }

    @Override
    public String toString() {
        return "Nombres: " + nombre + "\nApellidos: " + apellido +
                "\nIdentificación: " + identificacion
                +"\nEdad: " + edad;

    }
}

package co.edu.uniquindio.pr3.subastas.model;

import java.util.Objects;

public abstract class Persona {
    private String nombres;
    private String apellidos;
    private String identificacion;
    private Integer edad;


    public Persona() {
    }
    public Persona(String nombres, String apellidos, String identificacion, int edad) {
        this.nombres = nombres;
        this.apellidos=apellidos;
        this.identificacion=identificacion;
        this.edad=edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
        return "Nombres: " + nombres + "\nApellidos: " + apellidos +
                "\nIdentificación: " + identificacion
                +"\nEdad: " + edad;

    }
}

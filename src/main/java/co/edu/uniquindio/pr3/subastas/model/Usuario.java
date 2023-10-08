package co.edu.uniquindio.pr3.subastas.model;

import java.io.Serial;
import java.util.Objects;

import java.io.Serializable;
public class Usuario extends Persona implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private String nombreUsuario;

    private String correo;

    private String contrasenia;

    private TipoUsuario tipoUsuario;

    private boolean autenticado;

    //Constructores
    public Usuario() {
    }

    public Usuario(String nombres, String apellidos, String identificacion, String edad,
                   String nombreUsuario, String correo, String contrasenia,
                   TipoUsuario tipoUsuario, boolean autenticado) {
        super(nombres, apellidos, identificacion, edad);
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
        this.autenticado = autenticado;
    }

    public Usuario(String nombre, String apellido, String identificacion, String edad, String nombreUsuario, String correo, String contrasenia) {
        super(nombre, apellido, identificacion, edad);
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }


    //Getters y setters



    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(correo, usuario.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombreUsuario, correo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", autenticado=" + autenticado +
                '}';
    }


}

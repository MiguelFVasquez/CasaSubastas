package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;

public interface ISubasta {
    public boolean verificarUsuario(String usuario, String contrasenia);//Metodo de usuarios
    //...................Metodos del comprador..............................

    public Comprador obtenerComprador(String usuario, String contrasenia);
    public boolean crearComprador(Comprador newComprador) throws UsuarioException, CompradorException;
    public boolean actualizarComprador(String nombre, String apellido, String identificacion, int edad,
                                       String nombreUsuario, String correo, String contrasenia,
                                       boolean autenticado);
    public boolean eliminarComprador(String identificacion);
    //................Metodos del anunciante.................................

    public Anunciante obtenerAnunciante(String identificacion);

    public boolean crearAnunciante(String nombre, String apellido, String identificacion, int edad,
                                   String nombreUsuario, String correo, String contrasenia, boolean autenticado);
    public boolean actualizarAnunciante(String nombre, String apellido, String identificacion, int edad,
                                        String nombreUsuario, String correo, String contrasenia, boolean autenticado);
    public boolean eliminarAnunciante(String identificacion);


}

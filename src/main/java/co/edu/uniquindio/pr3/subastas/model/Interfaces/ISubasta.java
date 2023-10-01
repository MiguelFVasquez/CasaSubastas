package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;

public interface ISubasta {

    //Metodos del comprador
    public boolean crearComprador(String nombre, String apellido, String identificacion, int edad,
                                  String nombreUsuario, String correo, String contrasenia,
                                  boolean autenticado);
    public boolean actualizarComprador(String nombre, String apellido, String identificacion, int edad,
                                       String nombreUsuario, String correo, String contrasenia,
                                       boolean autenticado);
    public boolean eliminarComprador(String identificacion);
    //Metodos del anunciante
    public boolean crearAnunciante(String nombre, String apellido, String identificacion, int edad,
                                   String nombreUsuario, String correo, String contrasenia, boolean autenticado);
    public boolean actualizarAnunciante(String nombre, String apellido, String identificacion, int edad,
                                        String nombreUsuario, String correo, String contrasenia, boolean autenticado);
    public boolean eliminarAnunciante(String identificacion);


}

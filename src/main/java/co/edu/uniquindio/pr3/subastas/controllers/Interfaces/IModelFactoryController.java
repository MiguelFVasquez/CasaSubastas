package co.edu.uniquindio.pr3.subastas.controllers.Interfaces;

import co.edu.uniquindio.pr3.subastas.viewControllers.*;

public interface IModelFactoryController {
    public void initVentanaPrincipalViewController(VentanaPrincipalViewController ventanaPrincipalViewController) ;

    void initRegistroViewController(RegistroViewController registroViewController);

    void initInicioSesionViewController(InicioSesionViewController inicioSesionViewController);

    void initMiCuentaViewController(MiCuentaViewController miCuentaViewController);

    void initMiAnuncioViewController(MiAnuncioViewController miAnuncioViewController);

    void initMiProductoViewController(MiProductoViewController miProductoViewController);

    void initUsuarioViewController(UsuarioViewController usuarioViewController);
}

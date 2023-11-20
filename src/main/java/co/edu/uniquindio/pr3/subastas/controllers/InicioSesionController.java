package co.edu.uniquindio.pr3.subastas.controllers;

public class InicioSesionController {

    public ModelFactoryController mfm;
    public InicioSesionController(){
        System.out.println("Llamando al singleton desde InicioSesionController");
        mfm = ModelFactoryController.getInstance();
    }
    public void producirMensaje(String message) {
        mfm.producirMensaje(message);
    }
}




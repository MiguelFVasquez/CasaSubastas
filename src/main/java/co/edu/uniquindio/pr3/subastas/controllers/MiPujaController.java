package co.edu.uniquindio.pr3.subastas.controllers;

public class MiPujaController {

    public ModelFactoryController mfm;
    public MiPujaController(){
        System.out.println("Llamando al singleton desde mi puja controller");
        mfm = ModelFactoryController.getInstance();
    }
    public void producirMensaje(String message) {
        mfm.producirMensaje(message);
    }
}

package co.edu.uniquindio.pr3.subastas.controllers;

public class MiAnuncioController {
    public ModelFactoryController mfm;

    public MiAnuncioController(){
        System.out.println("Llamando al singleton desde mi anucioController");
        mfm = ModelFactoryController.getInstance();
    }

    public void producirMensaje (String message) {
        mfm.producirMensaje(message);
    }
}

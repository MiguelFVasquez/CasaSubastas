package co.edu.uniquindio.pr3.subastas.controllers;

import co.edu.uniquindio.pr3.subastas.model.Comprador;

public class MiCuentaController {
    public ModelFactoryController mfm;

    public MiCuentaController(){
        System.out.println("Llamado al singleton desde Mi cuenta");
        mfm=  ModelFactoryController.getInstance();
    }

    public void producirMensaje (String message) {
        mfm.producirMensaje(message);
    }

}

package co.edu.uniquindio.pr3.subastas.controllers;

public class MiProductoController {

    public ModelFactoryController mfm;
    public MiProductoController(){
        System.out.println("Llamando al singleton desde MiProductoController");
        mfm = ModelFactoryController.getInstance();
    }
}

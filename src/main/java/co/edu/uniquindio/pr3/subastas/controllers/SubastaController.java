package co.edu.uniquindio.pr3.subastas.controllers;

public class SubastaController {

    public ModelFactoryController mfm;
    public SubastaController(){
        System.out.println("Llamando al singleton desde mi subastaController");
        mfm = ModelFactoryController.getInstance();
    }
}

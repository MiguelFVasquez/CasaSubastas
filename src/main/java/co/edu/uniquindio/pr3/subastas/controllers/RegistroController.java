package co.edu.uniquindio.pr3.subastas.controllers;

public class RegistroController {
    public ModelFactoryController mfm;
    public RegistroController(){
        System.out.println("Llamando al singleton desde RegistroController");
        mfm = ModelFactoryController.getInstance();
    }
}

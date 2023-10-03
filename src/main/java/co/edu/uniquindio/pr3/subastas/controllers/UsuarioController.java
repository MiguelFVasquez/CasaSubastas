package co.edu.uniquindio.pr3.subastas.controllers;

public class UsuarioController {

    public ModelFactoryController mfm;

    public UsuarioController(){
        System.out.println("Llamado al singleton desde usuario");
        mfm=  ModelFactoryController.getInstance();
    }
}

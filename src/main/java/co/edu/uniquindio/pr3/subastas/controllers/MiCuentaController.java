package co.edu.uniquindio.pr3.subastas.controllers;

public class MiCuentaController {
    public ModelFactoryController mfm;

    public MiCuentaController(){
        System.out.println("Llamado al singleton desde Mi cuenta");
        mfm=  ModelFactoryController.getInstance();
    }
}

package co.edu.uniquindio.pr3.subastas.controllers;

public class InicioSesionController {

    public ModelFactoryController mfm;
    public InicioSesionController(){
        System.out.println("Llamando al singleton desde VentanaPrincipalController");
        mfm = ModelFactoryController.getInstance();
    }
}




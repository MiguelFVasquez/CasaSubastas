package co.edu.uniquindio.pr3.subastas.Hilos;

import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;

public class HiloGuardarXML extends Thread{
    @Override
    public void run() {
        ModelFactoryController.guardarResourceXML();
    }
}

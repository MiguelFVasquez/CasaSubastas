package co.edu.uniquindio.pr3.subastas.Hilos;

import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;

public class HiloGuardarBinario extends Thread{
    @Override
    public void run() {
        ModelFactoryController.guardarResourceBinario();
    }
}

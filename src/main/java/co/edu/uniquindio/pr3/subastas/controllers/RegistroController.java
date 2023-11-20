package co.edu.uniquindio.pr3.subastas.controllers;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

import static co.edu.uniquindio.pr3.subastas.rabbitmq.utils.Constantes.QUEUE_PRODUCTOR;
public class RegistroController {
    public ModelFactoryController mfm;
    public RegistroController(){
        System.out.println("Llamando al singleton desde RegistroController");
        mfm = ModelFactoryController.getInstance();
    }
    public void producirMensaje(String message) {
        mfm.producirMensaje(message);
    }
}

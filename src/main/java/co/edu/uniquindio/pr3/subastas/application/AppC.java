package co.edu.uniquindio.pr3.subastas.application;

import co.edu.uniquindio.pr3.subastas.Hilos.HiloGuardarBinario;
import co.edu.uniquindio.pr3.subastas.Hilos.HiloGuardarXML;
import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastas.viewControllers.VentanaPrincipalViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppC extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaPrincipalView.fxml"));
        Parent AnchorPane = loader.load();
        Scene scene = new Scene(AnchorPane);
        primaryStage.setScene(scene);
        VentanaPrincipalViewController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
        /*
        Log.configurarLogger();
        //        //Se realiza la copia de respaldo para los archivos
        //        CopiasRespaldoThread copiasRespaldoThread = new CopiasRespaldoThread();
        //        copiasRespaldoThread.start();
        //
        //        //Se inicializa la informacion de los objetos en "objeto_xxx.txt"
        WriteBackupObjectsThread objectsThread = new WriteBackupObjectsThread();
        objectsThread.start();

        //Se inicializa el hilo consumidor de rabbitmq
        ModelFactoryController.getInstance().consumirMensajes();

        //Se inicializa la informacion de los objetos en "objeto_xxx.txt"
        WriteBackupObjectsThread objectsThread = new WriteBackupObjectsThread();
        objectsThread.start();
        */
        //Se inicializa el hilo consumidor de rabbitmq
        ModelFactoryController.getInstance().consumirMensajes();
    }


    @Override
    public void stop() throws Exception {
        //Guardar resource binario
        HiloGuardarBinario guardarBinario= new HiloGuardarBinario();
        guardarBinario.start();
        guardarBinario.join();
        //Guardar resource xml
        HiloGuardarXML guardarXML= new HiloGuardarXML();
        guardarXML.start();
        guardarXML.join();

        //Se detiene el hilo consumidor
        ModelFactoryController.getInstance().detenerConsumidor();

    }

    public static void main(String[] args) {
        launch();
    }
}

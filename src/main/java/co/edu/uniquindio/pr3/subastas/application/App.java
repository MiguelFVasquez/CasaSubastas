package co.edu.uniquindio.pr3.subastas.application;

import co.edu.uniquindio.pr3.subastas.viewControllers.VentanaPrincipalViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaPrincipalView.fxml"));
        Parent AnchorPane = loader.load();
        Scene scene = new Scene(AnchorPane);
        primaryStage.setScene(scene);
        VentanaPrincipalViewController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

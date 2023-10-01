package co.edu.uniquindio.pr3.subastas.application;
import co.edu.uniquindio.pr3.subastas.viewControllers.VentanaPrincipalViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
public class App extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = primaryStage;
        mostrarVentanaPrincipal();
    }

    //Tiene una serie de codigo que siempre es igual
    private void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            //De donde se va a cargar la intefaz
            loader.setLocation(App.class.getResource("co.edu.uniquindio.pr3.subastas/application/VentanaPrincipalView.fxml"));
            //Definimos el anchorPane
            AnchorPane anchorPane = loader.load();
            //Obtenemos el controlador
            VentanaPrincipalViewController ventanaPrincipalViewController = loader.getController();
            //Para decir que el controlador pueda acceder a aplicacion
            ventanaPrincipalViewController.setAplicacion(this);
            //Para crear la escena (Panel principal). Le decimos el layout que va a cargar
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import co.edu.uniquindio.pr3.subastas.application.App;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import co.edu.uniquindio.pr3.subastas.controllers.*;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;



public class VentanaPrincipalViewController implements Initializable {

    VentanaPrincipalController ventanaPrincipalController;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab usuarioTab;

    @FXML
    private Tab tabSubastas;

    @FXML
    private Tab usuarioTabC;

    @FXML
    private TabPane tabPane;

    @FXML
    void initialize() {
    }


    Stage stage;
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ventanaPrincipalController = new VentanaPrincipalController();
        ventanaPrincipalController.mfm.initVentanaPrincipalViewController(this);

    }

    public void cambiarContenidoComprador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/pr3/subastas/application/CompradorView.fxml"));
            Node nuevoContenido = loader.load();

            // Crea un nuevo Tab con el contenido
            Tab nuevoTabUsuario = new Tab("Nuevo Tab");
            nuevoTabUsuario.setContent(nuevoContenido);

            // Obtiene el índice del Tab a reemplazar
            int indiceTabUsuario = tabPane.getTabs().indexOf(usuarioTab);

            // Reemplaza el Tab existente con el nuevo Tab
            tabPane.getTabs().remove(indiceTabUsuario);
            tabPane.getTabs().add(indiceTabUsuario, nuevoTabUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

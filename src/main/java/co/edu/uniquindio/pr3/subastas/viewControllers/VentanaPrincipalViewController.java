package co.edu.uniquindio.pr3.subastas.viewControllers;

import co.edu.uniquindio.pr3.subastas.application.App;
import javafx.fxml.Initializable;
import co.edu.uniquindio.pr3.subastas.controllers.*;
import javafx.stage.Stage;
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
    private Tab ttabIniciarSesion;

    @FXML
    private Tab tabRegistro;

    @FXML
    private Tab tabMisAnuncios;

    @FXML
    private Tab tabMisProductos;

    @FXML
    private Tab tabMiCuenta;

    @FXML
    private Tab tabMisPujas;

    @FXML
    private Tab usuarioTab;

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
}

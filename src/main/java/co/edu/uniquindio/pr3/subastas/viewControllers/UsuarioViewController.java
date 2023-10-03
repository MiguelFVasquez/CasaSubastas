package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.UsuarioController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

public class UsuarioViewController implements Initializable {


    UsuarioController usuarioController;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab misAnunciosTab;

    @FXML
    private Tab misPujasTab;

    @FXML
    private Tab inicioSesionTab;

    @FXML
    private Tab registroTab;

    @FXML
    private Tab miCuentaTab;

    @FXML
    private Tab misProductosTab;


    //Metodo par deshabilitar las pestañas para sesión no iniciada
    public void dehabilitarPestanias(){
        registroTab.setDisable(true);
        misProductosTab.setDisable(true);
        misAnunciosTab.setDisable(true);
        misPujasTab.setDisable(true);
        miCuentaTab.setDisable(true);
    }
    @FXML
    void initialize() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dehabilitarPestanias();
    }
}

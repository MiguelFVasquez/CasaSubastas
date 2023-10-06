package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;


import co.edu.uniquindio.pr3.subastas.controllers.UsuarioController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class UsuarioViewController implements Initializable {


    UsuarioController usuarioController;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public Tab inicioSesionTab;

    @FXML
    public Tab registroTab;

    @FXML
    public TabPane TabPanePrincipal;



    //Metodo par deshabilitar las pestañas para sesión no iniciada

    @FXML
    void initialize() {
        System.out.println("aca");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        registroTab.setDisable(true);
        TabPanePrincipal = new TabPane();


        usuarioController = new UsuarioController();
        usuarioController.mfm.initUsuarioViewController(this);

    }
}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class  InicioSesionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInicioNombre;

    @FXML
    private Button btnRecuperarCuenta;

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private Button btnIniciar;

    @FXML
    private PasswordField txtInicioPassword;

    @FXML
    void iniciarSesion(ActionEvent event) {

    }

    @FXML
    void recuperarContrasenia(ActionEvent event) {

    }

    @FXML
    void crearCuentaNueva(ActionEvent event) {

    }
    @FXML
    void initialize() {
        assert txtInicioNombre != null : "fx:id=\"txtInicioNombre\" was not injected: check your FXML file 'InicioSesionView.fxml'.";
        assert btnRecuperarCuenta != null : "fx:id=\"btnRecuperarCuenta\" was not injected: check your FXML file 'InicioSesionView.fxml'.";
        assert btnCrearCuenta != null : "fx:id=\"btnCrearCuenta\" was not injected: check your FXML file 'InicioSesionView.fxml'.";
        assert btnIniciar != null : "fx:id=\"btnIniciar\" was not injected: check your FXML file 'InicioSesionView.fxml'.";
        assert txtInicioPassword != null : "fx:id=\"txtInicioPassword\" was not injected: check your FXML file 'InicioSesionView.fxml'.";

    }
}

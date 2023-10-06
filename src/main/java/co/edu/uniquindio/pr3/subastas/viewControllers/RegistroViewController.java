package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistroViewController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnInico;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtApellido;

    @FXML
    private ComboBox<?> comboBoxTipoUsuario;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContrasenia;

    @FXML
    void aniadirUsuario(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert btnInico != null : "fx:id=\"btnInico\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert txtEdad != null : "fx:id=\"txtEdad\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert txtApellido != null : "fx:id=\"txtApellido\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert comboBoxTipoUsuario != null : "fx:id=\"comboBoxTipoUsuario\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert btnRegistrar != null : "fx:id=\"btnRegistrar\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert txtIdentificacion != null : "fx:id=\"txtIdentificacion\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'RegistroView.fxml'.";
        assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'RegistroView.fxml'.";

    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {

    }
}

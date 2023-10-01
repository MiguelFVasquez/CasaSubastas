package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MiCuentaViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnEliminarCuenta;

    @FXML
    private TextField txtApellidos;

    @FXML
    private Button btnActualizarInformacion;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private TextField txtEdad;

    @FXML
    private ComboBox<?> comboBoxTipoUsuario;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    void actualizarUsuario(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void eliminarUsuario(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert btnEliminarCuenta != null : "fx:id=\"btnEliminarCuenta\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert txtApellidos != null : "fx:id=\"txtApellidos\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert btnActualizarInformacion != null : "fx:id=\"btnActualizarInformacion\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert btnCerrarSesion != null : "fx:id=\"btnCerrarSesion\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert txtEdad != null : "fx:id=\"txtEdad\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert comboBoxTipoUsuario != null : "fx:id=\"comboBoxTipoUsuario\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert txtIdentificacion != null : "fx:id=\"txtIdentificacion\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'MiCuentaView.fxml'.";
        assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'MiCuentaView.fxml'.";

    }
}

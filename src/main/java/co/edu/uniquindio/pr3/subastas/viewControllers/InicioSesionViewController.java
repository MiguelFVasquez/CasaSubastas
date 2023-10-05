package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastas.controllers.UsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

        ModelFactoryController.getInstance().mover();

    }

    @FXML
    void initialize() {
        System.out.println("aca");
    }
}

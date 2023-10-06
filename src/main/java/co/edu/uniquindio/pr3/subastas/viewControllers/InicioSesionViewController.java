package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.InicioSesionController;
import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastas.controllers.UsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class  InicioSesionViewController implements Initializable {

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

    private InicioSesionController inicioSesionController;








    @FXML
    void iniciarSesion(ActionEvent event) {
        String nombre = txtInicioNombre.getText();
        String password = txtInicioPassword.getText();

        if(validarDatos( nombre, password )){
            if(verificarComprador(nombre, password)){

            }
            else
                if(verificarAnunciante(nombre, password  )){

                }
        }

    }

    private boolean verificarAnunciante(String nombre , String password) {
        boolean flag = inicioSesionController.mfm.verificarAnunciante( nombre, password );
        return flag;
    }


    private boolean verificarComprador(String nombre , String password) {
        boolean flag = inicioSesionController.mfm.verificarComprador( nombre, password );
        return flag;
    }

    public boolean verificarUsuario(String nombre){
        boolean flag = inicioSesionController.mfm.verifificarUsuario( nombre );
        return flag;
    }

    @FXML
    void recuperarContrasenia(ActionEvent event) {


    }

    @FXML
    void crearCuentaNueva(ActionEvent event) {

        ModelFactoryController.getInstance().mover();

    }

    //FUNCIONES UTILITARIAS
    private boolean validarDatos(String nombre , String contraseña) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


        if (nombre == null || nombre.isEmpty() ) {
            notificacion += "Ingrese su nombre\n";
        }

        if (contraseña == null || contraseña.isEmpty() ) {
            notificacion += "Ingrese su contraseña\n";
        }

        if ( !notificacion.isEmpty() ) {
            mostrarMensaje("Notificación", "Inicio fallido",
                    "El nombre y/o la contraseña ingresados son incorrectos"
                    , Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private boolean esNumero(String string) {
        try {

            Float.parseFloat(string);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        System.out.println("aca");
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {

    }
}

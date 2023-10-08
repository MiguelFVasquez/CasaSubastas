package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.InicioSesionController;
import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;
import co.edu.uniquindio.pr3.subastas.controllers.VentanaPrincipalController;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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

    InicioSesionController inicioSesionController = new InicioSesionController();

    VentanaPrincipalViewController ventanaPrincipalViewController = new VentanaPrincipalViewController();


    private Stage stage;

    private App aplicacion;

    private String nombreIniciado;
    private String passwordIniciada;


    public String getNombreIniciado() {
        return nombreIniciado;
    }

    public void setNombreIniciado(String nombreIniciado) {
        this.nombreIniciado = nombreIniciado;
    }

    public String getPasswordIniciada() {
        return passwordIniciada;
    }

    public void setPasswordIniciada(String paswordIniciada) {
        this.passwordIniciada = paswordIniciada;
    }

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        String nombre = txtInicioNombre.getText();
        String password = txtInicioPassword.getText();

        if(validarDatos(nombre, password)){
            if(verificarComprador(nombre, password)){
                System.out.println("SI llegas");

                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(App.class.getResource("CompradorView.fxml"));
                AnchorPane anchorPane= loader.load();
                CompradorViewController controller = loader.getController();
                controller.setAplicacion(aplicacion);
                Scene scene= new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                controller.init(stage);
                stage.show();
                controller.setInfoCuenta(inicioSesionController.mfm.obtenerComprador(nombre,password));
                txtInicioPassword.clear();
                txtInicioNombre.clear();


            }
            else {
                if ( verificarAnunciante( nombre , password ) ) {
                    System.out.println("SI llegas");

                }
                else{
                    mostrarMensaje( "Notificación" , "Usuario no encontrado", "Usuario y/o contraseña incorrecta", Alert.AlertType.INFORMATION );
                    txtInicioPassword.clear();
                }

            }

        }
    }

    public Comprador setInfoCuentaComprador(String nombre, String password){
        return inicioSesionController.mfm.setInfoCuentaComprador(nombre, password);
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
                    notificacion
                    , Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private boolean esNumero(String string) {
        try {Float.parseFloat(string);
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

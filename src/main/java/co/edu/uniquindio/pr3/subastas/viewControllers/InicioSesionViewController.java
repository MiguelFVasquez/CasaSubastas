package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.InicioSesionController;
import co.edu.uniquindio.pr3.subastas.controllers.ModelFactoryController;
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
import javafx.scene.input.KeyCode;
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
    MiCuentaViewController miCuentaViewController = new MiCuentaViewController();

    MiProductoViewController miProductoViewController = new MiProductoViewController();


    private Stage stage;

    private App aplicacion;

    private String nombreIniciado;
    private String passwordIniciada;


    public String getNombreIniciado() {
        nombreIniciado= txtInicioNombre.getText();
        return nombreIniciado;
    }

    public void setNombreIniciado(String nombreIniciado) {
        this.nombreIniciado = nombreIniciado;
    }

    public String getPasswordIniciada() {
        passwordIniciada= txtInicioPassword.getText();
        return passwordIniciada;
    }

    public void setPasswordIniciada(String paswordIniciada) {
        this.passwordIniciada = paswordIniciada;
    }


    public TextField getTxtInicioNombre() {
        return txtInicioNombre;
    }

    public PasswordField getTxtInicioPassword() {
        return txtInicioPassword;
    }

    //---------------FUNCIONES UTILITARIAS----------------------------------------
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

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public Comprador setInfoCuentaComprador(String nombre, String password){
        return inicioSesionController.mfm.setInfoCuentaComprador(nombre, password);
    }

    private boolean verificarAnunciante(String nombre , String password) {
        return inicioSesionController.mfm.verificarAnunciante( nombre, password );
    }

    private boolean verificarComprador(String nombre , String password) {
        return inicioSesionController.mfm.verificarComprador( nombre, password );
    }

    public boolean verificarUsuario(String nombre){
        return inicioSesionController.mfm.verifificarUsuario( nombre );
    }

//--------------------MANEJO DEL FOCUS DE LOS BOTONES-----------------------------

    private void configurarEventos() {
        btnIniciar.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // El botón está enfocado
                btnIniciar.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
            } else {
                // El botón ha perdido el foco
                btnIniciar.setStyle("-fx-background-color:  #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
            }
        });

        //Cuando el mouse está sobre el boton
        btnIniciar.setOnMouseEntered(event -> {
            btnIniciar.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
        });
        //Cuando no lo esta
        btnIniciar.setOnMouseExited(event -> {
            btnIniciar.setStyle("-fx-background-color:  #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
        });


        btnCrearCuenta.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                //El boton esta enfocado
                btnCrearCuenta.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
            } else {
                // El botón ha perdido el foco
                btnCrearCuenta.setStyle("-fx-background-color:  #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
            }
        });

        //Cuando el mouse está sobre el boton
        btnCrearCuenta.setOnMouseEntered(event -> {
            btnCrearCuenta.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
        });
        //Cuando no lo esta
        btnCrearCuenta.setOnMouseExited(event -> {
            btnCrearCuenta.setStyle("-fx-background-color:  #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

    }


//--------------------FUNCIONES DE LOS BOTONES (EVENT)
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
                controller.setInfoCuentaComprador(inicioSesionController.mfm.obtenerComprador(nombre,password));
                txtInicioPassword.clear();
                txtInicioNombre.clear();
            }else {
                if (verificarAnunciante(nombre, password)) {
                    System.out.println("SI llegas");

                    inicioSesionController.mfm.setMiAnunciante(inicioSesionController.mfm.obtenerAnunciante(nombre, password));
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(App.class.getResource("AnuncianteView.fxml"));
                    AnchorPane anchorPane = loader.load();
                    AnuncianteViewController controller = loader.getController();
                    controller.setAplicacion(aplicacion);
                    Scene scene = new Scene(anchorPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    controller.init(stage,this);
                    stage.show();
                    controller.setInfoCuenta(inicioSesionController.mfm.obtenerAnunciante(nombre, password));

                    txtInicioPassword.clear();
                    txtInicioNombre.clear();
                } else {
                    mostrarMensaje("Notificación", "Usuario no encontrado", "Usuario y/o contraseña incorrecta", Alert.AlertType.INFORMATION);
                    txtInicioPassword.clear();
                }
            }

        }
    }

    @FXML
    void iniciarSesionTecla(ActionEvent event) throws IOException {
        iniciarSesion(event);
    }


    @FXML
    void recuperarContrasenia(ActionEvent event) {


    }

    @FXML
    void crearCuentaNueva(ActionEvent event) {
        ModelFactoryController.getInstance().mover();

        mostrarMensaje("Creacion de cuenta", "Crear una cuenta", "Ahora puede " +
                "acceder a la pestaña de registro", Alert.AlertType.INFORMATION);
    }

    @FXML
    void crearCuentaNuevaTecla(ActionEvent event) {
        crearCuentaNueva(event);
    }
    @FXML
    void initialize() {
        System.out.println("aca");
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        inicioSesionController = new InicioSesionController();
        inicioSesionController.mfm.initInicioSesionViewController(this);


        btnIniciar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    iniciarSesionTecla(new ActionEvent()); // Llama a tu método actual
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnCrearCuenta.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    iniciarSesionTecla(new ActionEvent()); // Llama a tu método actual
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        configurarEventos();
    }

}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.MiCuentaController;
import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CompradorViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarInformacion;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEliminarCuenta;
    @FXML
    private Button btnActualizarInformacion1;

    @FXML
    private ComboBox<TipoUsuario> comboBoxTipoUsuario;

    @FXML
    private Tab tabMicuenta;

    @FXML
    private Tab tabMisPujas;

    @FXML
    private TextField txtApellidos;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;
    private App aplicacion;
    private Stage stage;

    private String usuarioIniciado;
    private String passwordIniciada;

    MiCuentaController miCuentaController = new MiCuentaController();

    public String getUsuarioIniciado() {
        return usuarioIniciado;
    }

    public void setUsuarioIniciado(String usuarioIniciado) {
        this.usuarioIniciado = usuarioIniciado;
    }

    public String getPasswordIniciada() {
        return passwordIniciada;
    }

    public void setPasswordIniciada(String passwordIniciada) {
        this.passwordIniciada = passwordIniciada;
    }

    private boolean confirmacionAlert(){
        // Crear una alerta de tipo CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro de que quiere hacer esta acción?");

        // Configurar los botones
        ButtonType buttonTypeContinuar = new ButtonType("Continuar");
        ButtonType buttonTypeCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeContinuar, buttonTypeCancelar);

        // Mostrar la alerta y esperar a que el usuario haga clic en un botón
        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.filter(buttonType -> buttonType == buttonTypeContinuar).isPresent();
    }

    @FXML
    void initialize() {
    }

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;
        
    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }


    public void show() {
        stage.show();

    }
    //---------------------------------------------------TAB MI CUENTA--------------------------------------------------
    public  void setInfoCuenta(Comprador comprador) {
        txtNombre.setText( comprador.getNombre());
        txtApellidos.setText( comprador.getApellido() );
        txtEdad.setText( comprador.getEdad() );
        txtIdentificacion.setText( comprador.getIdentificacion() );
        txtUsuario.setText( comprador.getNombreUsuario() );
        txtCorreo.setText( comprador.getCorreo() );
        txtContrasenia.setText( comprador.getContrasenia() );
        comboBoxTipoUsuario.setValue( comprador.getTipoUsuario() );

        btnActualizarInformacion1.setVisible( false );


        txtNombre.setEditable( false );
        txtApellidos.setEditable( false);
        txtEdad.setEditable( false);
        txtIdentificacion.setEditable( false);
        txtUsuario.setEditable( false);
        txtCorreo.setEditable( false);
        txtContrasenia.setEditable( false);
        comboBoxTipoUsuario.setEditable( false );

    }
    @FXML
    void actualizarUsuario(ActionEvent event) {
        setUsuarioIniciado( txtUsuario.getText() );
        setPasswordIniciada( txtContrasenia.getText() );

        txtNombre.setEditable( true );
        txtApellidos.setEditable( true);
        txtEdad.setEditable( true);

        txtUsuario.setEditable( true);
        txtCorreo.setEditable( true);
        txtContrasenia.setEditable( true);
        mostrarMensaje( "Notificación", "Actualice la información", "Cambie la información que desee", Alert.AlertType.INFORMATION );
        btnActualizarInformacion1.setVisible( true );

    }
    @FXML
    void guardarCambiosActualizar(ActionEvent event) throws UsuarioException, CompradorException {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String edad = txtEdad.getText();

        String nombreUsu = txtUsuario.getText();
        String correo = txtCorreo.getText();
        String password = txtContrasenia.getText();

        if(validarDatos(nombre,apellidos, edad, nombreUsu, correo, password)){
            if(miCuentaController.mfm.actualizarInforComprador( miCuentaController.mfm.obtenerComprador( getUsuarioIniciado(), getPasswordIniciada()),nombre,apellidos,
                    edad, nombreUsu, correo, password)){
                setUsuarioIniciado( nombreUsu );
                setPasswordIniciada( password );
                setInfoCuenta(miCuentaController.mfm.obtenerComprador( nombreUsu, password ));
            }
        }

    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void eliminarUsuario(ActionEvent event) throws UsuarioException, CompradorException {
        if(confirmacionAlert()){
            miCuentaController.mfm.eliminarCuentaComprador(txtUsuario.getText(), txtContrasenia.getText());
            mostrarMensaje( "Notificación", "Cuenta eliminada", "La cuenta ha sido eliminada", Alert.AlertType.INFORMATION );
            cerrarSesion(  event );
        }
    }

    private boolean validarDatos(String nombre , String contraseña ,  String edad , String usuario , String correo ,
                                 String password) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


        if ( nombre == null || nombre.isEmpty() ) {
            notificacion += "Ingrese su nombre\n";
        }

        if ( contraseña == null || contraseña.isEmpty() ) {
            notificacion += "Ingrese su contraseña\n";
        }
        if ( edad == null || edad.isEmpty() ) {
            notificacion += "Ingrese su edad\n";
        } else {
            if ( !esNumero( edad ) ) {
                notificacion += "La edad ingresada debe ser numérica\n";
            }
        }
        if ( usuario == null || usuario.isEmpty() ) {
            notificacion += "Ingrese como quiere ser llamado en la App\n";
        }
        if ( correo == null || correo.isEmpty() ) {
            notificacion += "Ingrese su correo\n";
        }
        if ( password == null || password.isEmpty() ) {
            notificacion += "Ingrese su contraseña\n";
        }

        if ( !notificacion.isEmpty() ) {
            mostrarMensaje( "Notificación" , "Registro fallido" ,
                    notificacion
                    , Alert.AlertType.WARNING );
            return false;
        }

        return true;
    }

    //------------------------------------------FUNCIONES UTILITARIAS---------------------------------------------------
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean esNumero(String string) {
        try {Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {


    }


}

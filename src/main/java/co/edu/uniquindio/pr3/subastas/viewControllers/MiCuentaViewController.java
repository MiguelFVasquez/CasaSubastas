package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.zip.ZipOutputStream;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.MiCuentaController;
import co.edu.uniquindio.pr3.subastas.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MiCuentaViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public TextField txtNombre;

    @FXML
    private Button btnEliminarCuenta;

    @FXML
    public TextField txtApellidos;

    @FXML
    private Button btnActualizarInformacion;

    @FXML
    private Button btnActualizarInformacion1;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    public TextField txtEdad;

    @FXML
    public ComboBox<TipoUsuario> comboBoxTipoUsuario;

    @FXML
    public TextField txtIdentificacion;

    @FXML
    public TextField txtCorreo;

    @FXML
    public TextField txtUsuario;

    @FXML
    public PasswordField txtContrasenia;

    private String usuarioIniciado;
    private String passwordIniciada;
    private Stage stage;
    private App aplicacion;
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


    //--------------------------------------------Actions events--------------------------------------------------------
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
    void actualizarUsuarioTecla(ActionEvent event) {
        actualizarUsuario(event);
    }
    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        miCuentaController.mfm.setMiAnunciante(null);
        miCuentaController.mfm.setMiComprador(null);
        // Cerrar la ventana
        stage.close();
    }

    @FXML
    void cerrarSesionTecla(ActionEvent event) throws IOException {
        cerrarSesion(event);
    }

    @FXML
    void guardarCambiosActualizar(ActionEvent event) throws UsuarioException, CompradorException, AnuncianteException {
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
                setInfoCuentaComprador(miCuentaController.mfm.obtenerComprador( nombreUsu, password ));
                mostrarMensaje( "Notificación", "Información actualizada", "Los datos se han actualizado correctamente", Alert.AlertType.INFORMATION );
                btnActualizarInformacion1.setVisible( false );
            }else {
                if(miCuentaController.mfm.actualizarInforAnunciante(miCuentaController.mfm.getMiAnunciante(),nombre,apellidos,
                        edad, nombreUsu, correo, password)){
                    miCuentaController.mfm.setMiAnunciante( miCuentaController.mfm.obtenerAnunciante(nombreUsu, password));
                    setInfoCuentaAnunciante( miCuentaController.mfm.obtenerAnunciante(nombreUsu, password) );
                    mostrarMensaje( "Notificación", "Información actualizada", "Los datos se han actualizado correctamente", Alert.AlertType.INFORMATION );
                    btnActualizarInformacion1.setVisible( false );

                }
            }
            miCuentaController.mfm.guardarResourceXML();

        }
    }

    @FXML
    void guardarCambiosActualizarTecla(ActionEvent event) throws UsuarioException, CompradorException, AnuncianteException {
        guardarCambiosActualizar(event);
    }
    @FXML
    void eliminarUsuario(ActionEvent event) throws UsuarioException, CompradorException, AnuncianteException, IOException {
        TipoUsuario tipoUsuario = comboBoxTipoUsuario.getValue();
        if(confirmacionAlert()){
            if(tipoUsuario.equals( TipoUsuario.COMPRADOR )){
                miCuentaController.mfm.eliminarCuentaComprador(txtUsuario.getText(), txtContrasenia.getText());
                miCuentaController.mfm.guardarResourceXML();
                mostrarMensaje( "Notificación", "Cuenta eliminada", "La cuenta ha sido eliminada", Alert.AlertType.INFORMATION );
                cerrarSesion(  event );
            }else{
                if(tipoUsuario.equals( TipoUsuario.ANUNCIANTE )){
                    miCuentaController.mfm.eliminarCuentaAnunciante( txtUsuario.getText(), txtContrasenia.getText() );
                    mostrarMensaje( "Notificación", "Cuenta eliminada", "La cuenta ha sido eliminada", Alert.AlertType.INFORMATION );
                    cerrarSesion(  event );
                }
                else{
                    mostrarMensaje( "Notificación", "Cuenta no encontrada", "La cuenta no ha sido encontrada", Alert.AlertType.INFORMATION );
                }
            }

        }
    }


    @FXML
    void eliminarUsuarioTecla(ActionEvent event) throws UsuarioException, CompradorException, IOException, AnuncianteException {
        eliminarUsuario(event);
    }

    //----------------------------------------FUNCIONES DE INFORMACIÓN -------------------------------------------------
    public  void setInfoCuentaComprador(Comprador comprador) {
        miCuentaController.mfm.mostrarInfoComprador(comprador);
        miCuentaController.mfm.deshabilitarDatos();
    }

    public  void setInfoCuentaAnunciante(Anunciante anunciante) {
        miCuentaController.mfm.mostrarInfoAnunciante(anunciante);
        miCuentaController.mfm.deshabilitarDatos();
    }

    //----------------------------------------FUNCIONES UTILITARIAS-----------------------------------------------------
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

    private boolean validarDatos(String nombre , String apellidos ,  String edad , String usuario , String correo ,
                                 String password) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


        if ( nombre == null || nombre.isEmpty() ) {
            notificacion += "Ingrese su nombre\n";
        }

        if ( apellidos == null || apellidos.isEmpty() ) {
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
    private void configurarEventos() {

        //Boton de actualizar información
        btnActualizarInformacion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnActualizarInformacion.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill: #0697AE");
            } else {
                btnActualizarInformacion.setStyle("-fx-background-color:  #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
            }
        });
        //Cuando el mouse esta sobre el boton
        btnActualizarInformacion.setOnMouseEntered(event -> {
            btnActualizarInformacion.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:#0697AE;-fx-cursor: hand");
        });
        //Cuando no lo está
        btnActualizarInformacion.setOnMouseExited(event -> {
            btnActualizarInformacion.setStyle("-fx-background-color:  #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

        //Boton de guardar cambios
        btnActualizarInformacion1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnActualizarInformacion1.setStyle("-fx-background-color: white; -fx-border-color:  #008000; -fx-text-fill: #008000");
            } else {
                btnActualizarInformacion1.setStyle("-fx-background-color:   #008000; -fx-cursor: hand; -fx-text-fill:WHITE");
            }
        });
        //Cuando el mouse esta sobre el boton
        btnActualizarInformacion1.setOnMouseEntered(event -> {
            btnActualizarInformacion1.setStyle("-fx-background-color: white; -fx-border-color:  #008000; -fx-text-fill: #008000");
        });
        //Cuando no lo está
        btnActualizarInformacion1.setOnMouseExited(event -> {
            btnActualizarInformacion1.setStyle("-fx-background-color:   #008000; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

        //boton de cerrar sesion
        btnCerrarSesion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnCerrarSesion.setStyle("-fx-background-color: white; -fx-border-color:  red; -fx-text-fill:  red;-fx-cursor: hand");
            } else {
                btnCerrarSesion.setStyle("-fx-background-color:  red; -fx-cursor: hand; -fx-text-fill:WHITE");
            }
        });

        //Cuando el mouse esta sobre el boton
        btnCerrarSesion.setOnMouseEntered(event -> {
            btnCerrarSesion.setStyle("-fx-background-color: white; -fx-border-color:  red; -fx-text-fill:  red;-fx-cursor: hand");
        });
        //Cuando no lo esta
        btnCerrarSesion.setOnMouseExited(event -> {
            btnCerrarSesion.setStyle("-fx-background-color:  red; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

        //Boton de eliminar cuenta
        btnEliminarCuenta.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnEliminarCuenta.setStyle("-fx-background-color: white; -fx-border-color:  red; -fx-text-fill:  red;-fx-cursor: hand");
            } else {
                btnEliminarCuenta.setStyle("-fx-background-color:  red; -fx-cursor: hand; -fx-text-fill:WHITE");
            }
        });

        //Cuando el mouse está sobre el boton
        btnEliminarCuenta.setOnMouseEntered(event -> {
            btnEliminarCuenta.setStyle("-fx-background-color: white; -fx-border-color:  red; -fx-text-fill:  red;-fx-cursor: hand");
        });
        //Cuando no lo esta
        btnEliminarCuenta.setOnMouseExited(event -> {
            btnEliminarCuenta.setStyle("-fx-background-color:  red; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

    }

    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {

        miCuentaController= new MiCuentaController();
        miCuentaController.mfm.initMiCuentaViewController(this);
        btnActualizarInformacion1.setVisible( false );
        comboBoxTipoUsuario.setEditable( false );


        //Asociacion de los botones a la tecla ENTER
        btnActualizarInformacion.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                actualizarUsuarioTecla(new ActionEvent()); // Llama a tu método actual
            }
        });
        btnCerrarSesion.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    cerrarSesionTecla(new ActionEvent()); // Llama a tu método actual
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnEliminarCuenta.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    eliminarUsuarioTecla(new ActionEvent()); // Llama a tu método actual
                } catch (UsuarioException | CompradorException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (AnuncianteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnActualizarInformacion1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    guardarCambiosActualizarTecla(new ActionEvent()); // Llama a tu método actual
                } catch (UsuarioException | CompradorException e) {
                    throw new RuntimeException(e);
                } catch (AnuncianteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        configurarEventos();

    }

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;

    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }
}

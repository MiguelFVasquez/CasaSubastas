package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.MiCuentaController;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
    private ComboBox<?> comboBoxTipoUsuario;

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

    MiCuentaController miCuentaController = new MiCuentaController();
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

        txtNombre.setEditable( false );
        txtApellidos.setEditable( false);
        txtEdad.setEditable( false);
        txtIdentificacion.setEditable( false);
        txtUsuario.setEditable( false);
        txtCorreo.setEditable( false);
        txtContrasenia.setEditable( false);

    }



    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {


    }
}

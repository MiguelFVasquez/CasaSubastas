package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.CompradorController;
import co.edu.uniquindio.pr3.subastas.controllers.MiCuentaController;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompradorViewController implements Initializable {

    CompradorController compradorController;



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
    public  TabPane tabPaneCompador;
    @FXML
    public AnchorPane anchorPaneContenedor;
    @FXML
    private Tab tabMicuenta;
    @FXML
    public Tab tabMisPujas;
    @FXML
    private Tab tabAnunciosDisponibles;

    //-------------CAMPOS DE TEXTO----------------------
    @FXML
    private TextField txtApellidos;

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

    @FXML
    private PasswordField txtContrasenia;

    //--------------Variables auxiliares------------------------
    private App aplicacion;
    private Stage stage;

    MiCuentaViewController miCuentaViewController = new MiCuentaViewController();


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
    public  void setInfoCuentaComprador(Comprador comprador) throws IOException {
        miCuentaViewController.setInfoCuentaComprador(comprador);
    }
    public void setInfoCuentaAnunciante(Anunciante anunciante) throws IOException {
        miCuentaViewController.setInfoCuentaAnunciante(anunciante);
    }
    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        compradorController= new CompradorController();
        compradorController.mfm.initCompradorViewController(this);
    }
    public void close() {
    }
}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoProducto;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AnuncianteViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarInformacion;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnAniadirImagen;

    @FXML
    private Button btnAniadirProducto;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEliminarCuenta;

    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnActualizarInformacion1;

    @FXML
    private TableColumn<?, ?> columCodigo;

    @FXML
    private TableColumn<?, ?> columDescripcion;

    @FXML
    private TableColumn<?, ?> columNombreProducto;

    @FXML
    private TableColumn<?, ?> columPrecio;

    @FXML
    private TableColumn<?, ?> columTipoProducto;

    @FXML
    private ComboBox<TipoProducto> comboBoxTipoProducto;



    @FXML
    private ImageView imageViewPrevisualizacion;

    @FXML
    private Tab tabInformacionProducto;

    @FXML
    private Tab tabMisProductos;

    @FXML
    private TableView<?> tableViewProductos;
    @FXML
    private TextField txtCodigoProducto;


    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtValor;
    private App aplicacion;

    private Stage stage;

    MiCuentaViewController miCuentaViewController= new MiCuentaViewController();

    @FXML
    void initialize() {
    }

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;

    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void setInfoCuenta(Anunciante anunciante) {
        miCuentaViewController.setInfoCuentaAnunciante(anunciante);
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {

    }


    //-----------------------------------------------TAB MI CUENTA------------------------------------------------------


}


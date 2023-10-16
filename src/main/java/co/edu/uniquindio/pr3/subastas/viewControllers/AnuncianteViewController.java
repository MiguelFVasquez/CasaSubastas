package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.controllers.AnuncianteController;
import co.edu.uniquindio.pr3.subastas.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AnuncianteViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //---------------------BOTONES----------------------------
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
    //------TABLE VIEW----------------
    @FXML
    private TableView<Producto> tableViewProductos;
    @FXML
    private TableColumn<Producto, String> columTipoProducto;
    @FXML
    private TableColumn<Producto, Boolean> columnAnunciado;
    @FXML
    private TableColumn<Producto, String> columCodigo;
    @FXML
    private TableColumn<Producto, String> columPrecio;
    @FXML
    private TableColumn<Producto, String> columDescripcion;
    @FXML
    private TableColumn<Producto, String> columNombreProducto;


    @FXML
    private ImageView imageViewPrevisualizacion;

    @FXML
    private Tab tabInformacionProducto;

    @FXML
    private Tab tabMisProductos;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtValor;
    @FXML
    private TextArea txtDescripcion;

    private App aplicacion;

    private Stage stage;
    private InicioSesionViewController inicioSesionViewController;
    MiCuentaViewController miCuentaViewController= new MiCuentaViewController();
    AnuncianteController anuncianteController;

    ObservableList<Producto> listaProductos= FXCollections.observableArrayList();

    public void setInfoCuenta(Anunciante anunciante) {
        miCuentaViewController.setInfoCuentaAnunciante(anunciante);
    }

    public String getNombreUsuario(){
        return inicioSesionViewController.getNombreIniciado();

    }
    public String getPassword(){
        return inicioSesionViewController.getPasswordIniciada();
    }

    private ObservableList<Producto> getListaProductos() {
        String nombreUsuario= anuncianteController.mfm.getNombreUsuario();
        String password=  anuncianteController.mfm.getPassword();

        Anunciante anunciante= anuncianteController.mfm.obtenerAnunciante(nombreUsuario,password);
        listaProductos.addAll(anunciante.getListaProductos());

        return listaProductos;
    }



    //-------------Funciones para el inicio de la ventana--------------------------------
    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;

    }
    public void init(Stage stage2, InicioSesionViewController inicioSesionViewController) {
        this.inicioSesionViewController=inicioSesionViewController;
        this.stage = stage2;
    }

    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        anuncianteController= new AnuncianteController();
        anuncianteController.mfm.initAnuncianteViewController(this);

    }

    //-----------------------------------------------TAB MI CUENTA------------------------------------------------------


}


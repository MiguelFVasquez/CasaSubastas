package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.application.App;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AnuncianteViewController {

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
    private ComboBox<?> comboBoxTipoProducto;

    @FXML
    private ComboBox<TipoUsuario> comboBoxTipoUsuario;

    @FXML
    private ImageView imageViewPrevisualizacion;

    @FXML
    private Tab tabInformacionProducto;

    @FXML
    private Tab tabMisProductos;

    @FXML
    private TableView<?> tableViewProductos;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtValor;
    private App aplicacion;

    private Stage stage;

    MiCuentaViewController miCuentaViewController= new MiCuentaViewController();
    public  void setInfoCuentaComprador(Comprador comprador) {
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
    void actualizarProducto(ActionEvent event) {

    }
    @FXML
    void guardarCambiosActualizar(ActionEvent event) {

    }

    @FXML
    void actualizarUsuario(ActionEvent event) {

    }

    @FXML
    void aniadirImagenProducto(ActionEvent event) {

    }

    @FXML
    void aniadirProducto(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

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

    public void setInfoCuenta(Anunciante anunciante) {
        miCuentaViewController.setInfoCuentaAnunciante(anunciante);
    }


    //-----------------------------------------------TAB MI CUENTA------------------------------------------------------


}


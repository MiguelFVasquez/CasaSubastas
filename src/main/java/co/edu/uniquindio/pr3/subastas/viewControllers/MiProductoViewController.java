package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MiProductoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab tabMisProductos;

    @FXML
    private TableColumn<?, ?> columCodigo;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private ImageView imageViewPrevisualizacion;

    @FXML
    private Tab tabInformacionProducto;

    @FXML
    private TableColumn<?, ?> columPrecio;

    @FXML
    private TableColumn<?, ?> columDescripcion;

    @FXML
    private Button btnAniadirProducto;

    @FXML
    private TableColumn<?, ?> columNombreProducto;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private Button btnAniadirImagen;

    @FXML
    private TextField txtValor;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private ComboBox<?> comboBoxTipoProducto;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private TableView<?> tableViewProductos;

    @FXML
    private TableColumn<?, ?> columTipoProducto;

    @FXML
    void aniadirImagenProducto(ActionEvent event) {

    }

    @FXML
    void aniadirProducto(ActionEvent event) {

    }

    @FXML
    void actualizarProducto(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.MiProductoController;
import co.edu.uniquindio.pr3.subastas.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastas.model.TipoProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MiProductoViewController implements Initializable {

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
    private ComboBox<TipoProducto> comboBoxTipoProducto;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private TableView<?> tableViewProductos;

    @FXML
    private TableColumn<?, ?> columTipoProducto;

    private String nombreUsu;
    private String password;

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    MiProductoController miProductoController = new MiProductoController();
    MiCuentaViewController miCuentaViewController = new MiCuentaViewController();

    @FXML
    void aniadirImagenProducto(ActionEvent event) {

        Stage stage = (Stage) btnAniadirImagen.getScene().getWindow();
        // Cerrar la ventana

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        java.io.File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageViewPrevisualizacion.setImage( image );

        }
    }

    @FXML
    void aniadirProducto(ActionEvent event) throws ProductoException {
        String nombre = txtNombreProducto.getText();
        String codigo = txtCodigoProducto.getText();
        String valor = txtValor.getText();
        String descrp = txtDescripcion.getText();
        TipoProducto tipoProducto = comboBoxTipoProducto.getSelectionModel().getSelectedItem();
        Image image  = imageViewPrevisualizacion.getImage();

        if(validarDatos(nombre, codigo, valor, descrp, tipoProducto, image)){
            if(crearProducto(nombre, codigo, valor, descrp, tipoProducto, image)){
                System.out.println("asd");

            }
        }


    }

    private boolean crearProducto(String nombre , String codigo , String valor , String descrp , TipoProducto tipoProducto , Image image) throws ProductoException {
        boolean flag = miProductoController.mfm.crearProducto(nombre, codigo, valor, descrp, tipoProducto, image);
        return flag;
    }

    private boolean validarDatos(String nombre , String codigo , String valor , String descrp , TipoProducto tipoProducto , Image image) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


        if ( nombre == null || nombre.isEmpty() ) {
            notificacion += "Ingrese el nombre del producto\n";
        }

        if ( codigo == null || codigo.isEmpty() ) {
            notificacion += "Ingrese el código del producto\n";
        }
        if ( valor == null || valor.isEmpty() ) {
            notificacion += "Ingrese el valor inicial del producto\n";
        }
        else {
            if ( !esNumero( valor ) ) {
                notificacion += "El valor inicial ingresado debe ser numérico\n";
            }
        }
        if ( descrp == null || descrp.isEmpty() ) {
            notificacion += "Ingrese la descripción del producto\n";
        }
        if ( tipoProducto == null) {
            notificacion += "Seleccione un tipo de producto\n";
        }
        if ( image == null) {
            notificacion += "Seleccione una imagen\n";
        }

        if ( !notificacion.isEmpty() ) {
            mostrarMensaje( "Notificación" , "Inicio fallido" ,
                    notificacion
                    , Alert.AlertType.WARNING );
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

    private boolean esNumero(String string) {
        try {Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
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

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        comboBoxTipoProducto.getItems().setAll( TipoProducto.values() );

    }
}

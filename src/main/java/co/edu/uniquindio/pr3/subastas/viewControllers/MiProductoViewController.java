package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.MiProductoController;
import co.edu.uniquindio.pr3.subastas.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastas.model.Producto;
import co.edu.uniquindio.pr3.subastas.model.TipoProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Producto, String> columCodigo;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private ImageView imageViewPrevisualizacion;

    @FXML
    private Tab tabInformacionProducto;

    @FXML
    private TableColumn<Producto, String> columPrecio;

    @FXML
    private TableColumn<Producto, String> columDescripcion;

    @FXML
    private Button btnAniadirProducto;

    @FXML
    private TableColumn<Producto, String> columNombreProducto;

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
    private Button btnActualizarProducto1;

    @FXML
    private TableView<Producto> tableViewProductos;

    @FXML
    private TableColumn<Producto, String> columTipoProducto;
    @FXML
    private TableColumn<Producto, Boolean> columnAnunciado;
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    private String nombreUsu;
    private String password;

    private Producto productoSeleccionado;

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
                mostrarMensaje( "Notificación", "Producto creado", "El producto ha sido creado y agregado a tu cuenta", Alert.AlertType.INFORMATION );
                limpiarCampos();
                refrescarTableView();

            }else{
                mostrarMensaje( "Notificación", "Producto no creado", "Al parecer ya existe un producto similar", Alert.AlertType.INFORMATION );
            }

        }
    }

    void limpiarCampos(){
        txtNombreProducto.clear();
        txtCodigoProducto.clear();
        txtValor.clear();
        txtDescripcion.clear();
        comboBoxTipoProducto.setValue( null );
        imageViewPrevisualizacion.setImage( null );

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
    @FXML
    void actualizarProductoInfo(ActionEvent event) {

    }

    private ObservableList<Producto> getListaProductos() {
        listaProductos.clear();
        listaProductos.addAll(miProductoController.mfm.getListaProductosAnunciante());
        return listaProductos;
    }


    void refrescarTableView(){
        tableViewProductos.setItems( getListaProductos() );
    }


    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        comboBoxTipoProducto.getItems().setAll( TipoProducto.values() );
        refrescarTableView();

        this.columNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columPrecio.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));
        this.columDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.columTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));


        btnActualizarProducto1.setVisible( false );
        btnEliminarProducto.setVisible( false );

        tableViewProductos.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                btnActualizarProducto1.setVisible( true );
                btnEliminarProducto.setVisible( true );
                productoSeleccionado = newSelection;
                txtNombreProducto.setText( productoSeleccionado.getNombre() );
                txtCodigoProducto.setText( productoSeleccionado.getCodigo() );
                txtValor.setText( productoSeleccionado.getValorInicial() );
                txtDescripcion.setText( productoSeleccionado.getDescripcion() );
                comboBoxTipoProducto.setValue( productoSeleccionado.getTipoProducto() );

                imageViewPrevisualizacion.setImage( productoSeleccionado.getImagen() );
            }

        });

        btnActualizarProducto1.setOnMouseEntered(event -> {
            btnActualizarProducto1.setStyle("-fx-background-color:  #0697AE; -fx-text-fill: #ffffff;-fx-cursor:  hand;");
        });

        // Evento para cuando el ratón sale del botón
        btnActualizarProducto1.setOnMouseExited(event -> {
            btnActualizarProducto1.setStyle("-fx-background-color:  white; -fx-text-fill: black;-fx-border-color:  #0697AE;-fx-text-fill: #0697AE; -fx-cursor:  hand;");
        });


    }
}

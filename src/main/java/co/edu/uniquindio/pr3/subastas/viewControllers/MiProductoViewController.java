package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.MiProductoController;
import co.edu.uniquindio.pr3.subastas.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastas.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
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
    private Tab tabInformacionProducto;

    //---------Table view-------------------
    @FXML
    private TableView<Producto> tableViewProductos;
    @FXML
    private TableColumn<Producto, String> columTipoProducto;
    //@FXML
    //private TableColumn<Producto, Boolean> columnAnunciado;
    @FXML
    private TableColumn<Producto, String> columCodigo;
    @FXML
    private TableColumn<Producto, String> columPrecio;
    @FXML
    private TableColumn<Producto, String> columDescripcion;
    @FXML
    private TableColumn<Producto, String> columNombreProducto;
    //----------------TXTFIELDS---------------------
    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtCodigoProducto;
    @FXML
    private TextField txtValor;
    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Button btnAniadirProducto;

    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnActualizarProducto;
    @FXML
    private Button btnActualizarProducto1;

    @FXML
    private Button btnAniadirImagen;

    @FXML
    private ComboBox<TipoProducto> comboBoxTipoProducto;

    @FXML
    private ImageView imageViewPrevisualizacion;
    private String nombreUsu;
    private String password;

    private Producto productoSeleccionado;

    private InicioSesionViewController inicioSesionViewController;
    MiProductoController miProductoController;
    MiCuentaViewController miCuentaViewController;
    AnuncianteViewController anuncianteViewController;
    ObservableList<Producto> listaProductos= FXCollections.observableArrayList();

//--------------------Funciones utilitarias----------------------------------
    void limpiarCampos(){
        txtNombreProducto.clear();
        txtCodigoProducto.clear();
        txtValor.clear();
        txtDescripcion.clear();
        comboBoxTipoProducto.setValue( null );
        imageViewPrevisualizacion.setImage( null );

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
    private ObservableList<Producto> getListaProductos() {
        String nombreUsuario= miProductoController.mfm.getNombreUsuario();
        String password=  miProductoController.mfm.getPassword();

        Anunciante anunciante= miProductoController.mfm.obtenerAnunciante(nombreUsuario,password);
        listaProductos.addAll(anunciante.getListaProductos());
        System.out.println("Lista de productos del anunciante: "+anunciante.getListaProductos());
        return listaProductos;
    }

    void refrescarTableView() throws AnuncianteException {
        tableViewProductos.setItems( getListaProductos() );
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

//---------ACCIONES DE LOS BOTONES(EVENT)-----------------------------------

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
    void aniadirProducto(ActionEvent event) throws ProductoException, AnuncianteException {
        String nombre = txtNombreProducto.getText();
        String codigo = txtCodigoProducto.getText();
        String valor = txtValor.getText();
        String descrp = txtDescripcion.getText();
        TipoProducto tipoProducto = comboBoxTipoProducto.getSelectionModel().getSelectedItem();
        Image image  = imageViewPrevisualizacion.getImage();
        System.out.println("Valores capturados: "+nombre+codigo+valor+descrp+tipoProducto+image.getUrl());
        String nombreUsuario= miProductoController.mfm.getNombreUsuario();
        String password=  miProductoController.mfm.getPassword();

        if(validarDatos(nombre, codigo, valor, descrp, tipoProducto, image)){
            crearProducto(nombreUsuario, password,nombre, codigo, valor, descrp, tipoProducto, image);

                //limpiarCampos();
                tableViewProductos.getItems().clear();
                tableViewProductos.setItems(getListaProductos());
        }
    }
    private void crearProducto(String nombreUsuario, String password, String nombre , String codigo , String valor , String descrp , TipoProducto tipoProducto , Image image) throws ProductoException, AnuncianteException {
        try{
            if (miProductoController.mfm.crearProducto(nombreUsuario, password, nombre, codigo, valor, descrp, tipoProducto, image)){
                tableViewProductos.getItems().clear();
                tableViewProductos.setItems(getListaProductos());
                System.out.println("Lista de productos:\n"+getListaProductos().toString());
                mostrarMensaje( "Notificación", "Producto creado", "El producto ha sido creado y agregado a tu cuenta", Alert.AlertType.INFORMATION );
            }
        }catch (ProductoException productoException){
            mostrarMensaje( "Notificación", "Producto no creado", productoException.getMessage(), Alert.AlertType.INFORMATION );
        }

    }

    @FXML
    void actualizarProducto(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) throws AnuncianteException {
        String nombreUsuario= miProductoController.mfm.getNombreUsuario();
        String password=  miProductoController.mfm.getPassword();

        if (productoSeleccionado!=null){
            try {
                if (confirmacionAlert()){
                    if (miProductoController.mfm.eliminarProducto(nombreUsuario,password,productoSeleccionado)){
                        listaProductos.remove(productoSeleccionado);
                        mostrarMensaje("Elimininación de producto", "Producto eliminado", "El producto ha sido eliminado con exito",Alert.AlertType.INFORMATION);
                    }
                }
            }catch (ProductoException productoException){
                mostrarMensaje("Elimininación de producto", "Producto eliminado", productoException.getMessage(),Alert.AlertType.INFORMATION);
            }
        }else {
            mostrarMensaje("Selección de producto", "Producto no seleccionado", "Seleccione primero un producto para poder eliminarlo",Alert.AlertType.WARNING);
        }
    }

    @FXML
    void actualizarProductoInfo(ActionEvent event) {

    }
    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        //Hace la instancia del controlador
        miProductoController= new MiProductoController();
        miProductoController.mfm.initMiProductoViewController(this);

        comboBoxTipoProducto.getItems().setAll(TipoProducto.values());//Añade los elementos al comboBox

        //Inicia los valores de la tableView para que puedan mostrar la informacón
        this.columNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.columPrecio.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));
        this.columTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        //this.columnAnunciado.setCellValueFactory(new PropertyValueFactory<>("estaAnunciado"));

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

        tableViewProductos.getItems().clear();
        tableViewProductos.setItems(getListaProductos());

        btnActualizarProducto1.setOnMouseEntered(event -> {
            btnActualizarProducto1.setStyle("-fx-background-color:  #0697AE; -fx-text-fill: #ffffff;-fx-cursor:  hand;");
        });

        // Evento para cuando el ratón sale del botón
        btnActualizarProducto1.setOnMouseExited(event -> {
            btnActualizarProducto1.setStyle("-fx-background-color:  white; -fx-text-fill: black;-fx-border-color:  #0697AE;-fx-text-fill: #0697AE; -fx-cursor:  hand;");
        });


    }
}

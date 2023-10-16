package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.MiAnuncioController;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import co.edu.uniquindio.pr3.subastas.model.Producto;
import co.edu.uniquindio.pr3.subastas.model.Puja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MiAnuncioViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //-----------------Table view Anuncios--------------------------
    @FXML
    private TableView<Anuncio> tableViewAnuncios;
    @FXML
    private TableColumn<Anuncio, String> columNombreUsuario;
    @FXML
    private TableColumn<Anuncio, String> columCodigoAnuncio;
    @FXML
    private TableColumn<Anuncio, Producto> columProducto;
    @FXML
    private TableColumn<Anuncio, String> columFechaInicio;
    @FXML
    private TableColumn<Anuncio, String> columFechaFinal;
    @FXML
    private TableColumn<Anuncio, List<Puja>> columPujas;
    //---------------Campos de texto/Fechas--------------------------
    @FXML
    private TextField txtNombreAnunciante;
    @FXML
    private TextField txtCodigoAnuncio;
    @FXML
    public TextArea txtProducto;
    @FXML
    private DatePicker txtFechaInicio;
    @FXML
    private DatePicker txtFechaFinal;
    //---------------BOTONES---------------------------------
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnAnunciar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnActualizar;
    //--------------------Variables auxiliares--------------------

    MiAnuncioController miAnuncioController;
    private Anuncio anuncioSeleccionado;

    private ObservableList<Anuncio> listaAnuncios= FXCollections.observableArrayList();


    //-------------------Funciones utilitarias--------------------
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    /**
     * Lista que sera mostrada en la tableView
     * @return
     */
    private ObservableList<Anuncio> getListaAnuncios(){
        String nombreUsuario= miAnuncioController.mfm.getNombreUsuario();
        String password= miAnuncioController.mfm.getPassword();
        Anunciante anunciante= miAnuncioController.mfm.obtenerAnunciante(nombreUsuario,password);
        listaAnuncios.addAll(anunciante.getListaAnuncios());
        return listaAnuncios;
    }

    private void configurarEventos() {

        //Animacion del boton de añadir
        btnAnunciar.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // El botón está enfocado
                btnAnunciar.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill: #0697AE;-fx-cursor: hand");
            } else {
                // El botón ha perdido el foco
                btnAnunciar.setStyle("-fx-background-color:   #0697AE; -fx-text-fill:WHITE");
            }
        });
        //Evento cuando el mouse está sobre el boton
        btnAnunciar.setOnMouseEntered(event -> {
            btnAnunciar.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
        });

        // Evento para cuando el ratón sale del botón
        btnAnunciar.setOnMouseExited(event -> {
            btnAnunciar.setStyle("-fx-background-color:   #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

        //Animacion del boton "Nuevo"

        btnNuevo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // El botón está enfocado
                btnNuevo.setStyle("-fx-background-color:   #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
            } else {
                // El botón ha perdido el foco
                btnNuevo.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
            }
        });

        //Evento cuando el mouse está sobre el boton
        btnNuevo.setOnMouseEntered(event -> {
            btnNuevo.setStyle("-fx-background-color:   #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

        // Evento para cuando el ratón sale del botón
        btnNuevo.setOnMouseExited(event -> {
            btnNuevo.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
        });


        btnActualizar.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // El botón está enfocado
                btnActualizar.setStyle("-fx-background-color:   #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
            } else {
                // El botón ha perdido el foco
                btnActualizar.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
            }
        });

        //Evento cuando el mouse está sobre el boton
        btnActualizar.setOnMouseEntered(event -> {
            btnActualizar.setStyle("-fx-background-color:   #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
        });

        // Evento para cuando el ratón sale del botón
        btnActualizar.setOnMouseExited(event -> {
            btnActualizar.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
        });


        //Animacion del boton "Eliminar"
        btnEliminar.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // El botón está enfocado
                btnEliminar.setStyle("-fx-background-color: white; -fx-cursor: hand; -fx-text-fill:red; -fx-border-color: red");
            } else {
                // El botón ha perdido el foco
                btnEliminar.setStyle("-fx-background-color: red; -fx-text-fill:  white");
            }
        });

        //Evento cuando el mouse está sobre el boton
        btnEliminar.setOnMouseEntered(event -> {
            btnEliminar.setStyle("-fx-background-color: white; -fx-cursor: hand; -fx-text-fill:red; -fx-border-color: red");
        });

        // Evento para cuando el ratón sale del botón
        btnEliminar.setOnMouseExited(event -> {
            btnEliminar.setStyle("-fx-background-color: red; -fx-text-fill:  white");
        });
    }


    private void mostrarInformacionAnuncio(){
        if (anuncioSeleccionado!=null){
            txtNombreAnunciante.setText(anuncioSeleccionado.getNombreAnunciante());
            txtCodigoAnuncio.setText(anuncioSeleccionado.getCodigo());
            txtProducto.setText(anuncioSeleccionado.getProducto().toString());
            //Fechas
            String fechaInicio= anuncioSeleccionado.getFechaInicio();
            String fechaFinal= anuncioSeleccionado.getFechaFinal();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            // Convertir la cadena de fecha a LocalDate
            LocalDate fechaInicial = LocalDate.parse(fechaInicio, formato);
            LocalDate fechaFin = LocalDate.parse(fechaFinal, formato);

            txtFechaInicio.setValue(fechaInicial);
            txtFechaFinal.setValue(fechaFin);


        }
    }

    private boolean validarDatos(String nombreUsuario, String codigo, String fechaInicial, String fechaFinal, String producto){
        String notificacion="";
        if (nombreUsuario==null || nombreUsuario.isEmpty()){
            notificacion+="Por favor, ingrese el nombre del usuario\n";
        }
        if (codigo==null || codigo.isEmpty()){
            notificacion+="Por favor, ingrese un código para el anuncio\n";
        }
        if (fechaInicial==null || fechaInicial.isEmpty()){
            notificacion+="Por favor, seleccione la fecha de inicio para el anuncio\n";
        }
        if (fechaFinal==null || fechaFinal.isEmpty()){
            notificacion+="Por favor, seleccione la fecha de fin para el anuncio\n";
        }
        if (producto==null || producto.isEmpty()){
            notificacion+="Por favor, seleccione un producto para anunciar\n";
        }

        return true;
    }

    //--------------------Evento de los botones------------------------
    @FXML
    void limpiarCampos(ActionEvent event) {
        txtNombreAnunciante.clear();
        txtCodigoAnuncio.clear();
        txtFechaInicio.setValue(null);
        txtFechaFinal.setValue(null);
        txtProducto.clear();
    }

    @FXML
    void limpiarCamposTecla(ActionEvent event) {
        limpiarCampos(event);
    }


    @FXML
    void anunciarProducto(ActionEvent event) {

    }

    @FXML
    void anunciarProductoTecla(ActionEvent event) {

    }

    @FXML
    void actualizarAnuncio(ActionEvent event) {

    }

    @FXML
    void actualizarAnuncioTecla(ActionEvent event) {

    }

    @FXML
    void eliminarAnuncio(ActionEvent event) {

    }

    @FXML
    void eliminarAnuncioTecla(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miAnuncioController= new MiAnuncioController();
        miAnuncioController.mfm.initMiAnuncioViewController(this);

        txtNombreAnunciante.setText(miAnuncioController.mfm.getNombreUsuario());
        txtNombreAnunciante.setEditable(false);

        //Inicializamos la tableView para poder mostrar los valores
        this.columNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
        this.columCodigoAnuncio.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        this.columFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        this.columPujas.setCellValueFactory(new PropertyValueFactory<>("listaPujas"));
        //Obtener un elemento seleccionado de la tabla

        tableViewAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                anuncioSeleccionado= newSelection;
                mostrarInformacionAnuncio();
            }
        });

        tableViewAnuncios.getItems().clear();
        tableViewAnuncios.setItems(getListaAnuncios());


        configurarEventos();
    }
}


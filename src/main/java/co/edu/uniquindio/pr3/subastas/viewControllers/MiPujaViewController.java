package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.uniquindio.pr3.subastas.Hilos.HiloGuardarXML;
import co.edu.uniquindio.pr3.subastas.controllers.MiPujaController;
import co.edu.uniquindio.pr3.subastas.exceptions.*;
import co.edu.uniquindio.pr3.subastas.model.*;
import co.edu.uniquindio.pr3.subastas.persistencia.Persistencia;
import co.edu.uniquindio.pr3.subastas.utils.ArchivoUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class MiPujaViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //-----------------TableView------------------------------
    @FXML
    private TableView<Puja> tableViewPuja;
    @FXML
    private TableColumn<Puja, String> columCodigo;
    @FXML
    private TableColumn<Puja, LocalDate> columFecha;
    @FXML
    private TableColumn<Puja, Anuncio> columAnuncio;
    @FXML
    private TableColumn<Puja, Double> columValor;
    //---------------CAMPOS DE TEXTO----------------
    @FXML
    public TextArea txtAnuncio;
    @FXML
    private TextField txtNombreComprador;
    @FXML
    public TextField txtCodigo;
    @FXML
    public TextField txtValor;
    @FXML
    public DatePicker datePickerFecha;
    //--------------Botones----------------
    @FXML
    private Button btnAgregarPuja;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnEliminar;

    //----------Variables auxiliares-------------
    MiPujaController miPujaController;
    private Puja pujaSeleccionada;
    private ObservableList<Puja> listaPujas = FXCollections.observableArrayList();
    String nombreUsuario;
    String password;

    //----------Funciones utilitarias-----------------------
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean validarDatos(String codigo, LocalDate fecha , Anuncio anuncio, Double valor){
        String notificacion="";
        if (codigo==null || codigo.isEmpty()){
            notificacion+="Por favor, ingrese un código\n";
        }
        if (fecha==null){
            notificacion+="Por favor, seleccione la fecha\n";
        }
        if (anuncio==null){
            notificacion+="Por favor, seleccione el anuncio\n";
        }
        if (valor<0){
            notificacion+="El valor ingresado no es valido";
        }
        return true;
    }

    private ObservableList<Puja> getListaPujas(){
        String nombreUsuario= miPujaController.mfm.getNombreUsuario();
        String password= miPujaController.mfm.getPassword();

        Comprador comprador= miPujaController.mfm.obtenerComprador(nombreUsuario,password);
        listaPujas.addAll(comprador.getListaPujas());
        return listaPujas;
    }
    public void setListaPujas(ObservableList<Puja> listaPujas){
        this.listaPujas = listaPujas;
        this.tableViewPuja.setItems(this.listaPujas);
    }
    private void configurarEventos() {

        //Animacion del boton de añadir
        btnAgregarPuja.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // El botón está enfocado
                btnAgregarPuja.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill: #0697AE;-fx-cursor: hand");
            } else {
                // El botón ha perdido el foco
                btnAgregarPuja.setStyle("-fx-background-color:   #0697AE; -fx-text-fill:WHITE");
            }
        });
        //Evento cuando el mouse está sobre el boton
        btnAgregarPuja.setOnMouseEntered(event -> {
            btnAgregarPuja.setStyle("-fx-background-color: white; -fx-border-color:  #0697AE; -fx-text-fill:  #0697AE");
        });

        // Evento para cuando el ratón sale del botón
        btnAgregarPuja.setOnMouseExited(event -> {
            btnAgregarPuja.setStyle("-fx-background-color:   #0697AE; -fx-cursor: hand; -fx-text-fill:WHITE");
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

    private void asociarProductosBotones(){
        //Asociacion de los botones a las teclas
        btnAgregarPuja.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    agregarPujaTecla(new ActionEvent()); // Llama a tu método actual
                } catch (AnuncioException e) {
                    throw new RuntimeException(e);
                } catch (CompradorException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnEliminar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    eliminarTecla(new ActionEvent()); // Llama a tu método actual
                } catch (CompradorException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void mostrarInformacionPuja(){
        if (pujaSeleccionada!=null){
            txtCodigo.setText(pujaSeleccionada.getCodigo());
            txtValor.setText(pujaSeleccionada.getValor()+"");
            txtAnuncio.setText(pujaSeleccionada.getAnuncio()+"");
        }
    }
    private void limpiarCampos(){
        txtCodigo.clear();
        txtAnuncio.clear();
        txtValor.clear();
        datePickerFecha.setValue(null);
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
    //---------------RABBIT------------------
    public void manejoMultiAplicacion() throws IOException {
        HiloGuardarXML guardarXMLThread = new HiloGuardarXML();
        guardarXMLThread.start();
        try {
            guardarXMLThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Se obtiene el mensaje que se va a enviar a la cola
        String mensajeProductor =  String.valueOf(Persistencia.cargarRecursoCasaSubastaXML());
        //Se manda el mensaje a la cola
        miPujaController.producirMensaje(mensajeProductor);
        miPujaController.mfm.consumirMensajes();
    }

    //.............EVENTOS DE LOS BOTONES.......................
    @FXML
    void agregarPuja(ActionEvent event) throws AnuncioException, CompradorException {
        //Informacion de la puja
        String codigo= txtCodigo.getText();
        String anuncio = txtAnuncio.getText();
        LocalDate fecha= datePickerFecha.getValue();
        Double valor= Double.parseDouble(txtValor.getText());
        Anuncio anuncioAux= miPujaController.mfm.getAnuncioPujar();

        if (validarDatos(codigo,fecha, anuncioAux,valor)){
            crearPuja(nombreUsuario,password,codigo,fecha,anuncioAux,valor);
            limpiarCampos();
            tableViewPuja.getItems().clear();
            tableViewPuja.setItems(getListaPujas());
        }
    }

    private void crearPuja(String nombreUsuario, String password, String codigo, LocalDate fecha , Anuncio anuncio, Double valor) throws AnuncioException, CompradorException {
        try{
            if(miPujaController.mfm.crearPuja(nombreUsuario,password,anuncio,valor,fecha,codigo)){
                miPujaController.mfm.guardarResourceXML();
                mostrarMensaje("Notificación puja", "Puja por producto", "Puja por producto realizada", Alert.AlertType.INFORMATION);
                manejoMultiAplicacion();
                Persistencia.guardaRegistroLog("Creación de puja", 1, "Se ha generado la puja de un producto");
            }
        }catch (PujaException pujaException){
            mostrarMensaje("Notifación puja","Puja no realizada", pujaException.getMessage(), Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void agregarPujaTecla(ActionEvent event) throws AnuncioException, CompradorException {
        agregarPuja(event);
    }

    @FXML
    void eliminarPuja(ActionEvent event) throws CompradorException {
        if (pujaSeleccionada!=null){
            try {
                if(confirmacionAlert()){
                    if (miPujaController.mfm.eliminarPuja(nombreUsuario,password,pujaSeleccionada)){
                        listaPujas.remove(pujaSeleccionada);
                        miPujaController.mfm.guardarResourceXML();
                        mostrarMensaje("Elimininación de puja", "Puja eliminada", "La puja ha sido eliminada con exito",Alert.AlertType.INFORMATION);
                        manejoMultiAplicacion();
                        Persistencia.guardaRegistroLog("Eliminación de puja", 1, "Se ha eliminado una puja");
                    }
                }
            }catch (PujaException pujaException){
                mostrarMensaje("Elimininación de puja", "Puja no eliminada", pujaException.getMessage(),Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            mostrarMensaje("Selección de Puja", "Puja no seleccionada", "Seleccione primero una puja para poder eliminarla",Alert.AlertType.WARNING);
        }
    }


    @FXML
    void eliminarTecla(ActionEvent event) throws CompradorException {
        eliminarPuja(event);
    }


    @FXML
    void limpiarCampos(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miPujaController= new MiPujaController();
        miPujaController.mfm.initMiPujaViewController(this);

        nombreUsuario= miPujaController.mfm.getNombreUsuario();
        password= miPujaController.mfm.getPassword();


        txtNombreComprador.setText(miPujaController.mfm.getNombreUsuario());
        txtNombreComprador.setEditable(false);

        this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columAnuncio.setCellValueFactory(new PropertyValueFactory<>("anuncio"));
        this.columFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.columValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableViewPuja.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                pujaSeleccionada= newSelection;
                mostrarInformacionPuja();
            }
        });

        tableViewPuja.getItems().clear();
        tableViewPuja.setItems(getListaPujas());
        asociarProductosBotones();
        configurarEventos();
        try {
            manejoMultiAplicacion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.MiPujaController;
import co.edu.uniquindio.pr3.subastas.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.PujaException;
import co.edu.uniquindio.pr3.subastas.model.*;
import co.edu.uniquindio.pr3.subastas.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Button btnEliminar;

    //----------Variables auxiliares-------------
    MiPujaController miPujaController;
    private Puja pujaSeleccionada;
    private ObservableList<Puja> listaPujas = FXCollections.observableArrayList();

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

    private Producto obtenerProducto(String producto){
        // Proceso de parsing
        String[] lineas = producto.split("\n");
        String codigo = lineas[1].split(":")[1].trim().replace("'", "");
        String nombre = lineas[2].split(":")[1].trim().replace("'", "");
        String descripcion = lineas[3].split(":")[1].trim().replace("'", "");
        String valorInicial = lineas[4].split(":")[1].trim().replace("'", "");
        TipoProducto tipoProducto = TipoProducto.valueOf(lineas[5].split(":")[1].trim().replace("'", ""));
        boolean estaAnunciado = Boolean.parseBoolean(lineas[6].split(":")[1].trim());

        // Crear instancia de Producto
        Producto productoAux = new Producto(codigo, nombre, descripcion, valorInicial, tipoProducto, estaAnunciado);
        return productoAux;
    }


    public Anuncio obtenerAnuncio(String anuncio) {
        // Obtén el texto del TextField

        // Dividir la cadena en líneas
        String[] lineas = anuncio.split("\n");

        // Extraer los valores de las líneas
        String codigo = extraerValor(lineas[1], "codigo='");
        String fechaInicio = extraerValor(lineas[2], "fechaInicio:'");
        String fechaFinal = extraerValor(lineas[3], "fechaFinal:'");
        String nombreAnunciante = extraerValor(lineas[4], "nombreAnunciante:'");
        String producto = extraerValor(lineas[5], "producto:");
        Producto productoAux = obtenerProducto(producto);//Obtengo el producto a partir del texto
        // Crear instancia de la clase Anuncio
        Anuncio anuncioAux = new Anuncio(codigo, fechaInicio, fechaFinal, nombreAnunciante, productoAux);

        // Hacer algo con la instancia de Anuncio (por ejemplo, mostrar en la consola)
        return anuncioAux;
    }

    private String extraerValor(String linea, String prefijo) {
        // Eliminar el prefijo y las comillas
        return linea.substring(prefijo.length(), linea.length() - 1);
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

    private void mostrarInformacionPuja(){
        if (pujaSeleccionada!=null){
            txtCodigo.setText(pujaSeleccionada.getCodigo());
            txtValor.setText(pujaSeleccionada.getValor()+"");
            txtAnuncio.setText(pujaSeleccionada.getAnuncio()+"");
        }
    }


    @FXML
    void agregarPuja(ActionEvent event) throws AnuncioException, CompradorException {
        //Informacion de la puja
        String codigo= txtCodigo.getText();
        String anuncio = txtAnuncio.getText();
        LocalDate fecha= datePickerFecha.getValue();
        Double valor= Double.parseDouble(txtValor.getText());
        Anuncio anuncioAux= obtenerAnuncio(anuncio);//Obtengo el anuncio a partir del texto
        //Credenciales del usuario
        String nombreUsuario= miPujaController.mfm.getNombreUsuario();
        String password= miPujaController.mfm.getPassword();

        if (validarDatos(codigo,fecha, anuncioAux,valor)){
            crearPuja(nombreUsuario,password,codigo,fecha,anuncioAux,valor);
            tableViewPuja.getItems().clear();
            tableViewPuja.setItems(getListaPujas());
        }

    }

    private void crearPuja(String nombreUsuario, String password, String codigo, LocalDate fecha , Anuncio anuncio, Double valor) throws AnuncioException, CompradorException {
        try{
            if(miPujaController.mfm.crearPuja(nombreUsuario,password,anuncio,valor,fecha,codigo)){
                mostrarMensaje("Notificación puja", "Puja por producto", "Puja por producto realizada", Alert.AlertType.INFORMATION);
                Persistencia.guardaRegistroLog("Creación de puja", 1, "Se ha generado la puja de un producto");
            }
        }catch (PujaException pujaException){
            mostrarMensaje("Notifación puja","Puja no realizada", pujaException.getMessage(), Alert.AlertType.INFORMATION);
        }

    }


    @FXML
    void agregarPujaTecla(ActionEvent event) throws AnuncioException, CompradorException {
        agregarPuja(event);
    }

    @FXML
    void eliminarPuja(ActionEvent event) {

    }


    @FXML
    void eliminarTecla(ActionEvent event) {
        eliminarPuja(event);
    }

    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miPujaController= new MiPujaController();
        miPujaController.mfm.initMiPujaViewController(this);

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

        configurarEventos();
    }
}

package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.Hilos.HiloGuardarXML;
import co.edu.uniquindio.pr3.subastas.controllers.MiAnuncioController;
import co.edu.uniquindio.pr3.subastas.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastas.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastas.exceptions.ProductoException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class MiAnuncioViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //--------------TABSA----------------------

    @FXML
    public TabPane tabPaneAnuncios;
    @FXML
    public Tab tabMisAnuncios;
    @FXML
    public Tab tabInfoAnuncio;

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
    //------------------Table view Pujas-----------------------
    @FXML
    private TableView<Puja> tableViewPujas;
    @FXML
    private TableColumn<Puja, String> columCodigoPuja;
    @FXML
    private TableColumn<Puja, Comprador> columComprador;

    @FXML
    private TableColumn<Puja, String> columFechapuja;

    @FXML
    private TableColumn<Puja, Double> columValor;

    //---------------Campos de texto/Fechas--------------------------
    @FXML
    private TextField txtNombreAnunciante;
    @FXML
    public TextField txtCodigoAnuncio;
    @FXML
    public TextArea txtProducto;
    @FXML
    public DatePicker txtFechaInicio;
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
    @FXML
    private Button btnAceptar;

    @FXML
    public ImageView ImageViewProductoSeleccionado;
    //--------------------Variables auxiliares--------------------

    MiAnuncioController miAnuncioController;
    private Anuncio anuncioSeleccionado;
    private Puja pujaSeleccionada;
    private ObservableList<Anuncio> listaAnuncios= FXCollections.observableArrayList();
    private ObservableList <Puja> listaPujas= FXCollections.observableArrayList();


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
    private ObservableList<Puja> getListaPujas(Anuncio anuncioSeleccion){
        listaPujas.addAll(anuncioSeleccion.getListaPujas());
        return listaPujas;
    }
    public void setListaAnuncios(ObservableList<Anuncio> listaAnuncios){
        this.listaAnuncios = listaAnuncios;
        this.tableViewAnuncios.setItems(this.listaAnuncios);
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
            //tableView
            tableViewPujas.getItems().clear();
            tableViewPujas.setItems(getListaPujas(anuncioSeleccionado));
            System.out.println("Lista pujas: "+getListaPujas(anuncioSeleccionado));

            //Fechas
            //String fechaInicio= anuncioSeleccionado.getFechaInicio();
            //String fechaFinal= anuncioSeleccionado.getFechaFinal();
            //DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Convertir la cadena de fecha a LocalDate
            //LocalDate fechaInicial = LocalDate.parse(fechaInicio, formato);
            //LocalDate fechaFin = LocalDate.parse(fechaFinal, formato);
            //txtFechaInicio.setValue(fechaInicial);
            //txtFechaFinal.setValue(fechaFin);

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
        if (!notificacion.isEmpty()){
            mostrarMensaje( "Notificación" , "Datos Erroneos" ,
                    notificacion
                    , Alert.AlertType.WARNING );
            return false;
        }

        return true;
    }

    public Producto obtenerProducto(String producto){
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

    private boolean confirmarPuja(){
        // Crear una alerta de tipo CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Al aceptar la puja, el anuncio sera eliminado, ¿desea continuar con está acción?");

        // Configurar los botones
        ButtonType buttonTypeContinuar = new ButtonType("Continuar");
        ButtonType buttonTypeCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeContinuar, buttonTypeCancelar);

        // Mostrar la alerta y esperar a que el usuario haga clic en un botón
        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.filter(buttonType -> buttonType == buttonTypeContinuar).isPresent();
    }
    private void asosiacionBotonesTecla(){
        btnAnunciar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    anunciarProductoTecla(new ActionEvent()); // Llama a tu método actual
                } catch (ProductoException | AnuncioException | AnuncianteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnNuevo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                limpiarCamposTecla(new ActionEvent()); // Llama a tu método actual
            }
        });

        btnEliminar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    eliminarAnuncioTecla(new ActionEvent()); // Llama a tu método actual
                } catch (AnuncioException | AnuncianteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Metodo para obtener el estado del producto, si está anunciado no se puede volver a anunciar
     * @param productoAnunciar
     * @return
     */

    public boolean verificarEstadoProducto(Producto productoAnunciar){
        return productoAnunciar.getEstaAnunciado();
    }

    //-----FUNCIONES PARA RABBIT---------
    public void manejoMultiAplicacion() throws IOException {
        HiloGuardarXML guardarXMLThread = new HiloGuardarXML();
        guardarXMLThread.start();
        try {
            guardarXMLThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Se obtiene el mensaje que se va a enviar a la cola
        String mensajeProductor = String.valueOf(Persistencia.cargarRecursoCasaSubastaXML());
        //Se manda el mensaje a la cola
        miAnuncioController.producirMensaje(mensajeProductor);
    }

    //--------------------Evento de los botones------------------------
    @FXML
    void limpiarCampos(ActionEvent event) {
        //txtNombreAnunciante.clear();
        txtCodigoAnuncio.clear();
        txtFechaInicio.setValue(null);
        txtFechaFinal.setValue(null);
        txtProducto.clear();
        ImageViewProductoSeleccionado.setImage(null);
    }

    @FXML
    void limpiarCamposTecla(ActionEvent event) {
        limpiarCampos(event);
    }


    @FXML
    void anunciarProducto(ActionEvent event) throws ProductoException, AnuncioException, AnuncianteException {
        //Información del anuncio
        String nombreUsuarioAnuncio= txtNombreAnunciante.getText();
        String codigo= txtCodigoAnuncio.getText();
        String fechaInicial= txtFechaFinal.getValue().toString();
        String fechaFinal= txtFechaFinal.getValue().toString();
        String producto= txtProducto.getText();
        //Obtenemos la intancia del producto a anunciar;
        Producto productoAnunciar= obtenerProducto(producto);
        //Informacion del anunciante
        String nombreUsuario= miAnuncioController.mfm.getNombreUsuario();
        String password= miAnuncioController.mfm.getPassword();
        //Lista vacia para la creacion del anuncio

        List<Puja> pujasAnunciadas= new ArrayList<>();
        if (validarDatos(nombreUsuarioAnuncio,codigo,fechaInicial,fechaFinal,producto)){
            if (!productoAnunciar.getEstaAnunciado()){
                crearAnuncio(nombreUsuario,password,nombreUsuarioAnuncio,codigo,fechaInicial,fechaFinal,productoAnunciar,pujasAnunciadas);
                limpiarCampos(event);
                tableViewAnuncios.getItems().clear();
                tableViewAnuncios.setItems(getListaAnuncios());
                miAnuncioController.mfm.actualizarTableView();
                //Añadimos el anuncio a la table view del comprador
                //miAnuncioController.mfm.setTableView(getListaAnuncios());
            }else {
                mostrarMensaje("Anuncio de producto", "Producto no anunciado","El producto ya se encuentra anunciado, por lo que no se puede volver a anunciar", Alert.AlertType.WARNING);
            }
        }

    }

    @FXML
    void anunciarProductoTecla(ActionEvent event) throws ProductoException, AnuncioException, AnuncianteException {
        anunciarProducto(event);
    }

    private void crearAnuncio(String usuario, String password,String nombreUsuario, String codigo, String fechaInicial, String fechaFinal, Producto producto, List<Puja> pujasAnuncio) throws ProductoException, AnuncioException, AnuncianteException {
       try{
           if (miAnuncioController.mfm.crearAnuncio(usuario,password,codigo,fechaInicial,fechaFinal,nombreUsuario,producto,pujasAnuncio)) {
               miAnuncioController.mfm.guardarResourceXML();
               mostrarMensaje( "Notificación Anuncio", "Anuncio realizado", "El anuncio se ha realizado con exito", Alert.AlertType.INFORMATION );
               Persistencia.guardaRegistroLog("Creación de anuncio", 1, "Se ha anunciado un producto");
               manejoMultiAplicacion();
           }
       }catch (AnuncioException anuncioException){
           mostrarMensaje( "Notificación Anuncio", "Anuncio no realizado", anuncioException.getMessage(), Alert.AlertType.INFORMATION );
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }

    @FXML
    void actualizarAnuncio(ActionEvent event) {

    }

    @FXML
    void actualizarAnuncioTecla(ActionEvent event) {

    }

    @FXML
    void eliminarAnuncio(ActionEvent event) throws AnuncioException, AnuncianteException {
        String nombreUsuario= miAnuncioController.mfm.getNombreUsuario();
        String password= miAnuncioController.mfm.getPassword();
        
        if (anuncioSeleccionado!=null){
            try{
                if (confirmacionAlert()){
                    if (miAnuncioController.mfm.eliminarAnuncio(nombreUsuario,password,anuncioSeleccionado)){
                        listaAnuncios.remove(anuncioSeleccionado);
                        mostrarMensaje("Elimininación de anuncio", "Anuncio eliminado", "El anuncio ha sido eliminado con exito",Alert.AlertType.INFORMATION);
                        miAnuncioController.mfm.guardarResourceXML();
                        anuncioSeleccionado.getProducto().setEstaAnunciado(false);
                        manejoMultiAplicacion();
                        Persistencia.guardaRegistroLog("Eliminación de anuncio", 1, "Se ha eliminado un anuncio");
                    }
                }
            }catch (AnuncioException anuncioException){
                mostrarMensaje("Elimininación de anuncio", "Anuncio no eliminado", anuncioException.getMessage(),Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{
            mostrarMensaje("Selección de anuncio", "Anuncio no seleccionado", "Seleccione primero un anuncio para poder eliminarlo",Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarAnuncioTecla(ActionEvent event) throws AnuncioException, AnuncianteException {
        eliminarAnuncio(event);
    }

    @FXML
    void aceptarPuja(ActionEvent event) throws AnuncioException, AnuncianteException {
        String nombreUsuario= miAnuncioController.mfm.getNombreUsuario();
        String password= miAnuncioController.mfm.getPassword();
        if (pujaSeleccionada!=null){
            Anuncio anuncioAceptado= pujaSeleccionada.getAnuncio();
            Producto productoSeleccionado= pujaSeleccionada.getAnuncio().getProducto();
            try {
                if (confirmarPuja()){
                    if (miAnuncioController.mfm.eliminarAnuncio(nombreUsuario,password,anuncioAceptado) && miAnuncioController.mfm.eliminarProducto(nombreUsuario,password,productoSeleccionado)){
                        listaAnuncios.remove(anuncioAceptado);
                        miAnuncioController.mfm.getListaProductos().remove(productoSeleccionado);
                        tableViewPujas.getItems().clear();
                        miAnuncioController.mfm.guardarResourceXML();
                        mostrarMensaje("Producto vendido","Puja aceptada", "La puja por anuncio ha sido aceptada, contacte con el comprador y termine el proceso de compra.", Alert.AlertType.INFORMATION);
                        manejoMultiAplicacion();
                        Persistencia.guardaRegistroLog("Puja aceptada", 1, "La puja ha sido aceptada y el producto ha sido vendido");
                    }
                }
            }catch (AnuncioException anuncioException){
                mostrarMensaje("Elimininación de anuncio", "Anuncio no eliminado", anuncioException.getMessage(),Alert.AlertType.INFORMATION);
            } catch (ProductoException productoException) {
                mostrarMensaje("Elimininación de Producto", "Producto no eliminado", productoException.getMessage(),Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{
            mostrarMensaje("Selección de puja","Puja no seleccionada","Seleccione primero una puja antes de aceotarla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void aceptarPujaTecla(ActionEvent event) {

    }


    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miAnuncioController = new MiAnuncioController();
        miAnuncioController.mfm.initMiAnuncioViewController(this);
        txtNombreAnunciante.setText(miAnuncioController.mfm.getNombreUsuario());
        txtNombreAnunciante.setEditable(false);

        //Inicializamos la tableView para poder mostrar los valores
        this.columNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
        this.columCodigoAnuncio.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        this.columFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));

        //Obtener un elemento seleccionado de la tabla

        tableViewAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                anuncioSeleccionado= newSelection;
                mostrarInformacionAnuncio();
            }
        });

        tableViewAnuncios.getItems().clear();
        tableViewAnuncios.setItems(getListaAnuncios());

        //Inicializacion de la tableView de las pujas
        this.columCodigoPuja.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columComprador.setCellValueFactory(new PropertyValueFactory<>("comprador"));
        this.columFechapuja.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.columValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableViewPujas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                pujaSeleccionada= newSelection;

            }
        });

        //Asociacion de los botones a las teclas
        asosiacionBotonesTecla();
        configurarEventos();
    }
}


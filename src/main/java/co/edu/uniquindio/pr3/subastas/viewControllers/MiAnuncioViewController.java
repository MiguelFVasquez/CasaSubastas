package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.MiAnuncioController;
import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import co.edu.uniquindio.pr3.subastas.model.Producto;
import co.edu.uniquindio.pr3.subastas.model.Puja;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private TextField txtProducto;
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


    //-------------------Funciones utilitarias--------------------
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
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
    }
}


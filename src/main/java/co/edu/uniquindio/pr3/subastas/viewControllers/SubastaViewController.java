package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.Hilos.HiloGuardarXML;
import co.edu.uniquindio.pr3.subastas.controllers.SubastaController;
import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import co.edu.uniquindio.pr3.subastas.persistencia.Persistencia;
import co.edu.uniquindio.pr3.subastas.utils.ArchivoUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SubastaViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public TableView<Anuncio> tableViewAnuncios;
    @FXML
    public TableColumn<Anuncio, String> columFechaLimite;

    @FXML
    public TableColumn<Anuncio, String> columCodigoAnuncio;

    @FXML
    public TableColumn<Anuncio, String> columNombreAnunciante;

    @FXML
    public TableColumn<Anuncio, String> columFechaPublicacion;

    @FXML
    public TableColumn<Anuncio, String> columProducto;

    @FXML
    public Button btnPuja;
    //---------------------Variables auxiliares----------------
    SubastaController subastaController;
    public Anuncio anuncioSeleccionado;

    private ObservableList<Anuncio> listaAnuncios= FXCollections.observableArrayList();
    //-----------------------FUNCIONES UTILITARIAS-----------------------------

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private ObservableList<Anuncio> getListaAnuncios(){
        listaAnuncios.addAll(subastaController.mfm.getMiCasa().getListaAnuncios());
        return listaAnuncios;
    }
    public void setListaAnuncios(ObservableList<Anuncio> listaAnuncios){
        this.listaAnuncios = listaAnuncios;
        this.tableViewAnuncios.setItems(this.listaAnuncios);
    }

    private  String mezclarPalabras(String palabra1, String palabra2) {
        // Concatenar las dos palabras
        String palabraConcatenada = palabra1 + palabra2;

        // Convertir la palabra concatenada a un array de caracteres
        char[] caracteres = palabraConcatenada.toCharArray();

        // Usar el algoritmo de Fisher-Yates para mezclar los caracteres
        Random rand = new Random();
        for (int i = caracteres.length - 1; i > 0; i--) {
            int indiceAleatorio = rand.nextInt(i + 1);

            // Intercambiar caracteres
            char temp = caracteres[i];
            caracteres[i] = caracteres[indiceAleatorio];
            caracteres[indiceAleatorio] = temp;
        }
        return new String(caracteres);
    }

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
        subastaController.producirMensaje(mensajeProductor);
    }

    //---------------------Eventos de los botones--------------

    @FXML
    void pujarPorProducto(ActionEvent event) {
        if (anuncioSeleccionado!=null) {
            String codigo = mezclarPalabras(anuncioSeleccionado.getCodigo(), anuncioSeleccionado.getNombreAnunciante());
            subastaController.mfm.setInfoMiPujaView(anuncioSeleccionado, codigo);
            AnchorPane anchorPane = subastaController.mfm.getAnchorPane();
            TabPane tabPane = subastaController.mfm.getTabePaneComprador(anchorPane);
            // Obtener la referencia al tab "Mis anuncios"
            Tab misAnunciosTab = subastaController.mfm.encontrarTabPorNombreComprador("Anuncios disponibles");
            if (misAnunciosTab != null) {
                tabPane.getSelectionModel().select(misAnunciosTab);
            }
            mostrarMensaje("Finalizar puja","Finalice la puja","Para finalizar la puja dirijase a la pestaña mis pujas y termine de llenar la información", Alert.AlertType.INFORMATION);
        }else {
            mostrarMensaje("Selección de anuncio", "Anuncio no seleccionado","Seleccione un anuncio por el cual pujar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void pujarPorProductoTecla(ActionEvent event) {
        pujarPorProducto(event);
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subastaController= new SubastaController();
        subastaController.mfm.initSubastaViewController(this);

        //Inicializamos la table view

        this.columNombreAnunciante.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
        this.columCodigoAnuncio.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columFechaPublicacion.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        this.columFechaLimite.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        this.columProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));

        tableViewAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                anuncioSeleccionado= newSelection;
                //mostrarInformacionAnuncio();
            }
        });
        tableViewAnuncios.getItems().clear();
        tableViewAnuncios.setItems(getListaAnuncios());
        tableViewAnuncios.refresh();

    }
}

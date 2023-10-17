package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.controllers.SubastaController;
import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SubastaViewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Anuncio> tableViewAnuncios;
    @FXML
    private TableColumn<Anuncio, String> columFechaLimite;

    @FXML
    private TableColumn<Anuncio, String> columCodigoAnuncio;

    @FXML
    private TableColumn<Anuncio, String> columNombreAnunciante;

    @FXML
    private TableColumn<Anuncio, String> columFechaPublicacion;

    @FXML
    private TableColumn<Anuncio, String> columProducto;

    @FXML
    private Button btnPuja;
    //---------------------Variables auxiliares----------------
    SubastaController subastaController;
    private Anuncio anuncioSeleccionado;

    //---------------------Eventos de los botones--------------

    @FXML
    void pujarPorProducto(ActionEvent event) {

    }

    @FXML
    void pujarPorProductoTecla(ActionEvent event) {

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
    }
}

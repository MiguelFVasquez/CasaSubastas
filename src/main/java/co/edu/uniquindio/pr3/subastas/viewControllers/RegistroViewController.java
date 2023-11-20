package co.edu.uniquindio.pr3.subastas.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.pr3.subastas.Hilos.HiloGuardarXML;
import co.edu.uniquindio.pr3.subastas.controllers.RegistroController;
import co.edu.uniquindio.pr3.subastas.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;
import co.edu.uniquindio.pr3.subastas.persistencia.Persistencia;
import co.edu.uniquindio.pr3.subastas.utils.ArchivoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistroViewController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnInico;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtApellido;

    @FXML
    private ComboBox<TipoUsuario> comboBoxTipoUsuario;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContrasenia;

    RegistroController registroController = new RegistroController();



    @FXML
    void aniadirUsuario(ActionEvent event) throws UsuarioException, AnuncianteException, CompradorException {
        TipoUsuario tipoUsuario = comboBoxTipoUsuario.getValue();
        String nombre = txtNombre.getText();
        String apellidos = txtApellido.getText();
        String id = txtIdentificacion.getText();
        String edad = txtEdad.getText();
        String usuario = txtUsuario.getText();
        String correo = txtCorreo.getText();
        String password = txtContrasenia.getText();

        if(validarDatos( nombre, apellidos, id, edad, usuario, correo, password, tipoUsuario)){
            if ( tipoUsuario.equals( TipoUsuario.ANUNCIANTE ) ) {
                if(crearAnunciante(nombre, apellidos, id, edad, usuario, correo, password)){
                    mostrarMensaje( "Notificación", "Usuario registrado" ,
                            "El usuario ha sido registrado con éxito" + "\n" + "Ahora inicie sesión", Alert.AlertType.INFORMATION );
                    registroController.mfm.guardarResourceXML();
                    Persistencia.guardaRegistroLog("Registro de anunciante en la plataforma",1,"Registro cliente");
                    limpiarCampos();
                }
                else{
                    mostrarMensaje( "Notificación", "Usuario no registrado",
                            "Un usuario con sus mismas credenciales se encuentra registrado", Alert.AlertType.INFORMATION );
                }

            }
            else{
                if(crearComprador( nombre, apellidos, id, edad, usuario, correo, password )){
                    mostrarMensaje( "Notificación", "Usuario registrado" ,
                            "El usuario ha sido registrado con éxito" + "\n" + "Ahora inicie sesión", Alert.AlertType.INFORMATION );
                    limpiarCampos();
                    registroController.mfm.guardarResourceXML();
                    Persistencia.guardaRegistroLog("Registro de comprador en la plataforma",1,"Registro cliente");

                }
                else{
                    mostrarMensaje( "Notificación", "Usuario no registrado",
                            "Un usuario con sus mismas credenciales se encuentra registrado", Alert.AlertType.INFORMATION );
                }
            }
        }
    }

    private boolean crearComprador(String nombre , String apellidos , String id , String edad , String usuario ,
                                   String correo , String password) throws UsuarioException, CompradorException {
        return registroController.mfm.crearComprador( nombre , apellidos , id , edad , usuario , correo , password );
    }

    private boolean crearAnunciante(String nombre , String apellidos , String id , String edad , String usuario ,
                                    String correo , String password) throws UsuarioException, AnuncianteException {
        return registroController.mfm.crearAnunciante(nombre, apellidos, id, edad, usuario, correo, password);
    }


    //---------------------------------FUNCIONES UTILITARIAS------------------------------------------------------------
    private boolean validarDatos(String nombre , String contraseña , String id , String edad , String usuario , String correo ,
                                 String password , TipoUsuario tipoUsuario) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


        if ( nombre == null || nombre.isEmpty() ) {
            notificacion += "Ingrese su nombre\n";
        }

        if ( contraseña == null || contraseña.isEmpty() ) {
            notificacion += "Ingrese su contraseña\n";
        }
        if ( id == null || id.isEmpty() ) {
            notificacion += "Ingrese su cédula\n";
        } else {
            if ( !esNumero( id ) ) {
                notificacion += "La identificación ingresada debe ser numérica\n";
            }
        }
        if ( edad == null || edad.isEmpty() ) {
            notificacion += "Ingrese su edad\n";
        } else if ( !esNumero( edad ) ) {
            notificacion += "La edad ingresada debe ser numérica\n";
        }
        if(Integer.parseInt(edad)<18){
            notificacion+="Para registrarse debe ser mayor de edad";
        }
        if ( usuario == null || usuario.isEmpty() ) {
            notificacion += "Ingrese como quiere ser llamado en la App\n";
        }
        if ( correo == null || correo.isEmpty() ) {
            notificacion += "Ingrese su correo\n";
        }
        if ( password == null || password.isEmpty() ) {
            notificacion += "Ingrese su contraseña\n";
        }
        if ( tipoUsuario == null) {
            notificacion += "Seleccione un tipo de Usuario\n";
        }

        if ( !notificacion.isEmpty() ) {
            mostrarMensaje( "Notificación" , "Registro fallido" ,
                    notificacion
                    , Alert.AlertType.WARNING );
            return false;
        }

        return true;
    }

    private boolean esNumero(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    void limpiarCampos(){
        comboBoxTipoUsuario.setValue( null );
        txtNombre.clear();
        txtApellido.clear();
        txtIdentificacion.clear();
        txtEdad.clear();
        txtUsuario.clear();
        txtCorreo.clear();
        txtContrasenia.clear();
    }



    @FXML
    void iniciarSesion(ActionEvent event) {

    }



    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        registroController.mfm.initRegistroViewController(this);
        comboBoxTipoUsuario.getItems().addAll(TipoUsuario.values());
    }
//---------------- RABBIT MQ---------------------------------

    //Acciones necesarias para el manejo multi-aplicacion con rabbitmq
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
        registroController.producirMensaje(mensajeProductor);
    }
}

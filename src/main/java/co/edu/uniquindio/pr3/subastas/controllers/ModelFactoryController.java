package co.edu.uniquindio.pr3.subastas.controllers;

import co.edu.uniquindio.pr3.subastas.Hilos.HiloCargaBinario;
import co.edu.uniquindio.pr3.subastas.Hilos.HiloCargarArchivos;
import co.edu.uniquindio.pr3.subastas.Hilos.HiloCargarXML;
import co.edu.uniquindio.pr3.subastas.controllers.Interfaces.IModelFactoryController;
import co.edu.uniquindio.pr3.subastas.exceptions.*;
import co.edu.uniquindio.pr3.subastas.mapping.mappers.SubastaMapper;
import co.edu.uniquindio.pr3.subastas.model.*;
import co.edu.uniquindio.pr3.subastas.persistencia.Persistencia;
import co.edu.uniquindio.pr3.subastas.utils.CasaSubastasUtil;
import co.edu.uniquindio.pr3.subastas.viewControllers.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelFactoryController implements IModelFactoryController {
    //Clase global de la subasta
    static CasaSubasta miCasa;
    //Para la creacion de un CRUD en la aplicacion
    //DTO
    //SubastaMapper mapper = SubastaMapper.INSTANCE;
    //Datos para el manejo de cada controlador
    private VentanaPrincipalViewController ventanaPrincipalViewController;
    private RegistroViewController registroViewController;
    private InicioSesionViewController inicioSesionViewController;

    private MiCuentaViewController miCuentaViewController;
    private MiAnuncioViewController miAnuncioViewController;
    private MiProductoViewController miProductoViewController;
    private SubastaViewController subastaViewController;
    private UsuarioViewController usuarioViewController;
    private MiPujaViewController miPujaViewController;
    private CompradorViewController compradorViewController;
    private AnuncianteViewController anuncianteViewController;
    private Comprador comprador;
    private Anunciante anunciante;

    public void setMiComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Comprador getMiComprador() {
        return comprador;
    }
    public void setMiAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public Anunciante getMiAnunciante() {
        return anunciante;
    }

    //Singleton (Garantiza instancia unica)
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }

    // Método para obtener la instancia de nuestra clase

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    //METODOS DE SERIALIZACION
    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos

        System.out.println("Invocacion clase singleton");
        try {
            inicializarDatos();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //salvarDatosPrueba();

        //2. Cargar los datos de los archivos
        //cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
        //cargarResourceBinario();
        //guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
        //cargarResourceXML();
        //guardarResourceXML();
        //Siempre se debe verificar si la raiz del recurso es null

        if(miCasa == null){
            cargarDatosBase();
            guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");

    }

    private void inicializarDatos() throws InterruptedException{
        //miCasa = CasaSubastasUtil.inicializarDatos();
        //Cargar datos desde archivos
        HiloCargarArchivos cargarArchivos= new HiloCargarArchivos();
        cargarArchivos.start();
        cargarArchivos.join();

        //Carga datos desde el xml
        HiloCargarXML cargarXML= new HiloCargarXML();
        cargarXML.start();
        cargarXML.join();
        /*
        //Carga datos desde el binario
        HiloCargaBinario cargaBinario= new HiloCargaBinario();
        cargaBinario.start();
        cargaBinario.join();*/


    }

    // Getter y setter de la casa de subastas
    public CasaSubasta getMiCasa() {
        return miCasa;
    }

    public void setMiCasa(CasaSubasta miCasa) {
        this.miCasa = miCasa;
    }

    //--------------Funciones de subastas para el singleton-------------------
    @Override
    public void initVentanaPrincipalViewController(VentanaPrincipalViewController ventanaPrincipalViewController) {
        this.ventanaPrincipalViewController = ventanaPrincipalViewController;
    }

    @Override
    public void initRegistroViewController(RegistroViewController registroViewController) {
        this.registroViewController = registroViewController;
    }

    @Override
    public void initInicioSesionViewController(InicioSesionViewController inicioSesionViewController) {
        this.inicioSesionViewController = inicioSesionViewController;

    }

    @Override
    public void initMiCuentaViewController(MiCuentaViewController miCuentaViewController) {
        this.miCuentaViewController = miCuentaViewController;
    }

    @Override
    public void initMiAnuncioViewController(MiAnuncioViewController miAnuncioViewController) {
        this.miAnuncioViewController = miAnuncioViewController;
    }

    @Override
    public void initMiProductoViewController(MiProductoViewController miProductoViewController) {
        this.miProductoViewController = miProductoViewController;
    }

    @Override
    public void initUsuarioViewController(UsuarioViewController usuarioViewController) {
        this.usuarioViewController = usuarioViewController;
    }

    @Override
    public void initCompradorViewController(CompradorViewController compradorViewController){
        this.compradorViewController=compradorViewController;
    }
    @Override
    public void initAnuncianteViewController(AnuncianteViewController anuncianteViewController){
        this.anuncianteViewController=anuncianteViewController;
    }
    @Override
    public void initSubastaViewController(SubastaViewController subastaViewController){
        this.subastaViewController=subastaViewController;
    }
    @Override
    public void initMiPujaViewController(MiPujaViewController miPujaViewController){
        this.miPujaViewController= miPujaViewController;
    }
    //------------------FUNCIONES DE NAVEGABILIDAD------------------------
    public TabPane getTabPane(){
        return miAnuncioViewController.tabPaneAnuncios;
    }

    public Tab encontrarTabPorNombre(String nombre) {
        for (Tab tab : miAnuncioViewController.tabPaneAnuncios.getTabs()) {
            if (tab.getText().equals(nombre)) {
                return tab;
            }
        }
        return null; // Devuelve null si no se encuentra el tab
    }
    //..............NAVEGABILIDAD DEL COMPRADOR.....................

    public AnchorPane getAnchorPane(){
        return compradorViewController.anchorPaneContenedor;
    }

    public Tab encontrarTabPorNombreComprador(String nombre) {
        for (Tab tab : compradorViewController.tabPaneCompador.getTabs()) {
            if (tab.getText().equals(nombre)) {
                return tab;
            }
        }
        return null; // Devuelve null si no se encuentra el tab
    }
    public TabPane getTabePaneComprador(AnchorPane anchorPane){
        Scene scene = anchorPane.getScene();
        if (scene != null) {
            for (javafx.scene.Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node instanceof TabPane) {
                    return (TabPane) node;
                }
            }
        }
        return null; // Devuelve null si no se encuentra el TabPane
    }


    //--------------------------FUNCIONES DE TAB INICIO SESION----------------------------------------------------------
    public void mover() {
        usuarioViewController.registroTab.setDisable(false);
    }


    public boolean verificarComprador(String nombre, String password){
        Comprador compra = miCasa.obtenerComprador( nombre, password );
        if(compra!=null){
            return true;
        }
        return false;
    }
    public boolean verificarAnunciante(String nombre, String password){
        return miCasa.verificarUsuario(nombre);
    }
    public boolean verifificarUsuario(String nombre) {
        boolean flag = miCasa.verificarUsuario( nombre );
        return flag;
    }
    /*Esto no estaba*/
    public Comprador obtenerComprador(String usuario, String contrasenia){
        return miCasa.obtenerComprador(usuario,contrasenia);
    }
    public Anunciante obtenerAnunciante(String usuario, String contrasenia){
        return miCasa.obtenerAnunciante(usuario,contrasenia);
    }
    public Comprador setInfoCuentaComprador(String nombre , String password) {
        return miCasa.obtenerComprador(nombre, password);
    }

    //------------------------------------FUNCIONES DE TAB DE REGISTRO--------------------------------------------------
    public boolean crearAnunciante(String nombre , String apellidos , String id ,
                                   String edad , String usuario , String correo , String password)
            throws UsuarioException, AnuncianteException {

        Anunciante anun = new Anunciante( nombre, apellidos,  id,  edad, usuario,  correo,
                password, 0);
        return miCasa.crearAnunciante( anun );
    }


    public boolean crearComprador(String nombre , String apellidos , String id , String edad , String usuario ,
                                  String correo , String password) throws UsuarioException, CompradorException {
        Comprador comprador = new Comprador(nombre, apellidos, id, edad, usuario, correo, password, TipoUsuario.COMPRADOR, false);
        return miCasa.crearComprador(comprador);
    }

    //-------------------------------------------MI CUENTA VIEW---------------------------------------------------------
    public void mostrarInfoComprador(Comprador comprador){
        miCuentaViewController.txtNombre.setText(comprador.getNombre());
        miCuentaViewController.txtApellidos.setText(comprador.getApellido());
        miCuentaViewController.txtIdentificacion.setText(comprador.getIdentificacion());
        miCuentaViewController.txtEdad.setText(comprador.getEdad());
        miCuentaViewController.txtCorreo.setText(comprador.getCorreo());
        miCuentaViewController.txtUsuario.setText(comprador.getNombreUsuario());
        miCuentaViewController.txtContrasenia.setText(comprador.getContrasenia());
        miCuentaViewController.comboBoxTipoUsuario.setValue( TipoUsuario.COMPRADOR );
    }
    public String getNombreUsuario(){
        return inicioSesionViewController.getNombreIniciado();

    }
    public String getPassword(){
        return inicioSesionViewController.getPasswordIniciada();
    }


    public void deshabilitarDatos(){

        miCuentaViewController.txtNombre.setEditable( false );
        miCuentaViewController.txtApellidos.setEditable( false);
        miCuentaViewController.txtEdad.setEditable( false);
        miCuentaViewController.txtIdentificacion.setEditable( false);
        miCuentaViewController.txtUsuario.setEditable( false);
        miCuentaViewController.txtCorreo.setEditable( false);
        miCuentaViewController.txtContrasenia.setEditable( false);

    }
    public void mostrarInfoAnunciante(Anunciante anuncianteAux){
        miCuentaViewController.txtNombre.setText(anuncianteAux.getNombre());
        miCuentaViewController.txtApellidos.setText(anuncianteAux.getApellido());
        miCuentaViewController.txtIdentificacion.setText(anuncianteAux.getIdentificacion());
        miCuentaViewController.txtEdad.setText(anuncianteAux.getEdad());
        miCuentaViewController.txtCorreo.setText(anuncianteAux.getCorreo());
        miCuentaViewController.txtUsuario.setText(anuncianteAux.getNombreUsuario());
        miCuentaViewController.txtContrasenia.setText(anuncianteAux.getContrasenia());
        miCuentaViewController.comboBoxTipoUsuario.setValue( TipoUsuario.ANUNCIANTE );

    }
    public boolean actualizarInforComprador(Comprador comprador , String nombre , String apellidos , String edad , String nombreUsu , String correo , String password) throws UsuarioException, CompradorException {
        return miCasa.actualizarComprador( comprador, nombre, apellidos, edad, nombreUsu, correo, password);
    }

    public void eliminarCuentaComprador(String nombreUsu , String password) throws UsuarioException, CompradorException {
        miCasa.eliminarComprador( obtenerComprador( nombreUsu, password ) );
    }

    public boolean actualizarInforAnunciante(Anunciante anunciante , String nombre , String apellidos ,
                                             String edad , String nombreUsu , String correo , String password) throws UsuarioException, AnuncianteException {
        return miCasa.actualizarAnunciante( anunciante, nombre, apellidos, edad, nombreUsu, correo, password);

    }
    public void eliminarCuentaAnunciante(String nombreUsu , String password) throws UsuarioException, AnuncianteException {
        miCasa.eliminarAnunciante( obtenerAnunciante( nombreUsu, password ) );

    }
    //----------------------------------------------METODOS DEL ANUNCIANTE----------------------------------------------

    //-----------------------------------------------PRODUCTO VIEW------------------------------------------------------
    public boolean crearProducto(String nombreUsuario, String password, String nombre , String codigo , String valor, String descrp, TipoProducto tipoProducto , Image image,boolean anunciado) throws ProductoException, AnuncianteException {
        Anunciante anuncianteAux= miCasa.obtenerAnunciante(nombreUsuario,password);
        Producto newProducto= new Producto(codigo, nombre, descrp, image, valor, tipoProducto, anunciado);
        return miCasa.crearProducto(anuncianteAux, newProducto);
    }

    public boolean eliminarProducto(String nombreUsuario, String password, Producto productoEliminar) throws ProductoException, AnuncianteException {
        return miCasa.eliminarProducto(nombreUsuario,password,productoEliminar);
    }
    public ObservableList<Producto> getListaProductos(){
        return miProductoViewController.listaProductos;
    }

    //----------------------------------------------ANUNCIO VIEW---------------------------------------------------

    public Producto obtenerProductoTexto(String productoTexto){
        return miAnuncioViewController.obtenerProducto(productoTexto);
    }

    public boolean crearAnuncio(String nombreUsuario, String password,String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, Producto producto, List<Puja> pujasAnuncio) throws ProductoException, AnuncioException, AnuncianteException {
        Anunciante anuncianteAux= miCasa.obtenerAnunciante(nombreUsuario, password);

        Anuncio newAnuncio= new Anuncio(codigo,fechaInicio,fechaFinal,nombreAnunciante,producto,pujasAnuncio);

        return miCasa.crearAnuncio(anuncianteAux,newAnuncio);
    }

    public boolean eliminarAnuncio(String nombreUsuario, String password, Anuncio anuncioEliminar) throws AnuncioException, AnuncianteException {
        Anunciante anuncianteAux= miCasa.obtenerAnunciante(nombreUsuario,password);
        return miCasa.eliminarAnuncio(anuncianteAux,anuncioEliminar);
    }

    public void setInfoAnuncioProducto(String producto, Image imagen, String codigo, LocalDate fecha){
        miAnuncioViewController.txtProducto.appendText(producto);
        miAnuncioViewController.txtProducto.setEditable(false);
        miAnuncioViewController.ImageViewProductoSeleccionado.setImage(imagen);
        miAnuncioViewController.txtCodigoAnuncio.setText(codigo);
        miAnuncioViewController.txtCodigoAnuncio.setEditable(false);
        miAnuncioViewController.txtFechaInicio.setValue(fecha);
        miAnuncioViewController.txtFechaInicio.setEditable(false);

    }
    //-----------------------------METODOS DEL COMPRADOR---------------------------------------------
    public void setTableView(ObservableList<Anuncio> listaAnuncios){
        subastaViewController.tableViewAnuncios.getItems().clear();
        subastaViewController.tableViewAnuncios.setItems(listaAnuncios);
    }

    public void actualizarTableView(){
        miProductoViewController.tableViewProductos.refresh();
    }

    //-----------------------------METODOS PARA LAS PUJAS-------------------------------
    public void setInfoMiPujaView(Anuncio anuncioSeleccionado, String codigo){
        miPujaViewController.datePickerFecha.setValue(LocalDate.now());
        miPujaViewController.datePickerFecha.setEditable(false);
        miPujaViewController.txtAnuncio.setText(anuncioSeleccionado.toString());
        miPujaViewController.txtAnuncio.setEditable(false);
        miPujaViewController.txtCodigo.setText(codigo);
        miPujaViewController.txtCodigo.setEditable(false);
    }

    public boolean crearPuja(String nombreUsuario, String password, Anuncio anuncio, Double valor, LocalDate fecha, String codigo) throws PujaException, AnuncioException, CompradorException {
        Comprador compradoAux= miCasa.obtenerComprador(nombreUsuario,password);
        Puja newPuja= new Puja(anuncio,compradoAux,valor,fecha,codigo);
        return miCasa.crearPuja(compradoAux, newPuja);
    }
    public boolean eliminarPuja(String nombreUsuario, String password, Puja pujaElimnar) throws PujaException, CompradorException {
        Comprador compradorAux= miCasa.obtenerComprador(nombreUsuario,password);
        return miCasa.eliminarPuja(compradorAux,pujaElimnar);
    }

    public Anuncio getAnuncioPujar(){
        return subastaViewController.anuncioSeleccionado;
    }



    //---------------SERIALIZACION----------------------------------------

    public void salvarDatosPrueba() {
        try {
            Persistencia.guardarUsuarios(getMiCasa().getListaUsuarios());
            System.out.println("Serializado de usuarios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        try{
            Persistencia.guardarAnuncios(getMiCasa().getListaAnuncios());
            System.out.println("Serializado de anuncios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }

    //PUNTO 2 METODO
    public static void cargarDatosDesdeArchivos() {
        miCasa = CasaSubastasUtil.inicializarDatos();
        try {
            Persistencia.cargarDatosArchivos(miCasa);
            System.out.println("Serializado de usuarios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //PUNTO 3
    public static void guardarResourceBinario() {
        Persistencia.guardarRecursoCasaSubastaBinario(miCasa);
    }

    public static void cargarResourceBinario() {
        miCasa= Persistencia.cargarRecursoCasaSubastaBinario();
    }

    //PUNTO 4
    public static void cargarResourceXML() {
        miCasa= Persistencia.cargarRecursoCasaSubastaXML();
    }

    public static void guardarResourceXML() {
        Persistencia.guardarRecursoCasaSubastaXML(miCasa);
    }

    public static void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    private void cargarDatosBase() {

    }

}




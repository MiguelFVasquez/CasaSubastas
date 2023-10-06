package co.edu.uniquindio.pr3.subastas.controllers;

import co.edu.uniquindio.pr3.subastas.controllers.Interfaces.IModelFactoryController;
import co.edu.uniquindio.pr3.subastas.mapping.mappers.SubastaMapper;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.CasaSubasta;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.viewControllers.*;

public class ModelFactoryController implements IModelFactoryController {
    //Clase global de la subasta
    CasaSubasta miCasa;
    //Para la creacion de un CRUD en la aplicacion
    //DTO
    SubastaMapper mapper = SubastaMapper.INSTANCE;
    //Datos para el manejo de cada controlador
    private VentanaPrincipalViewController ventanaPrincipalViewController;
    private RegistroViewController registroViewController;
    private InicioSesionViewController inicioSesionViewController;

    private MiCuentaViewController miCuentaViewController;
    private MiAnuncioViewController miAnuncioViewController;
    private MiProductoViewController miProductoViewController;
    private UsuarioViewController usuarioViewController;


    public ModelFactoryController() {
        System.out.println("Invocacion clase singleton");
        inicializarDatos();
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

    private void inicializarDatos() {
        miCasa = new CasaSubasta("Subastas UQ");
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
        Anunciante anun = miCasa.obtenerAnunciante( nombre, password );
        if(anun!=null){
            return true;
        }
        return false;
    }
    public boolean verifificarUsuario(String nombre) {
        boolean flag = miCasa.verificarUsuario( nombre );
        return flag;
    }
}




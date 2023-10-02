package co.edu.uniquindio.pr3.subastas.controllers;

import co.edu.uniquindio.pr3.subastas.mapping.mappers.SubastaMapper;
import co.edu.uniquindio.pr3.subastas.model.CasaSubasta;
import co.edu.uniquindio.pr3.subastas.viewControllers.VentanaPrincipalViewController;

public class ModelFactoryController {

    CasaSubasta miCasa;
    SubastaMapper mapper = SubastaMapper.INSTANCE;
    private VentanaPrincipalViewController ventanaPrincipalViewController;

    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
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


    public CasaSubasta getMiCasa() {
        return miCasa;
    }

    public void setMiCasa(CasaSubasta miCasa) {
        this.miCasa = miCasa;
    }

    //Funciones de subastas para el singleton
    public void initVentanaPrincipalViewController(VentanaPrincipalViewController ventanaPrincipalViewController) {
        this.ventanaPrincipalViewController = ventanaPrincipalViewController;
    }



}

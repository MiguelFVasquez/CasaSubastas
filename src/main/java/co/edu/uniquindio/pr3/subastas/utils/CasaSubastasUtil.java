package co.edu.uniquindio.pr3.subastas.utils;

import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.CasaSubasta;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;


public class CasaSubastasUtil {

    public static CasaSubasta inicializarDatos()
    {
        CasaSubasta miCasa = new CasaSubasta("Subastas UQ");

        Comprador compra = new Comprador("sasd", "asdasd", "2323", "34", "sa", "sasdsd",
                "12", TipoUsuario.COMPRADOR, false);
        Anunciante anunciante = new Anunciante("sasd", "asdasd", "2323", "34", "sa", "sasdsd",
                "23",  0 );
        miCasa.getListaCompradores().add(compra);
        miCasa.getListaAnunciantes().add( anunciante );
        return miCasa;
        }
}


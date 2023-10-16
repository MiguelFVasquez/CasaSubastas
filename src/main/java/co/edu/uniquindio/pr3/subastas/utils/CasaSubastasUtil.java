package co.edu.uniquindio.pr3.subastas.utils;

import co.edu.uniquindio.pr3.subastas.model.*;


public class CasaSubastasUtil {

    public static CasaSubasta inicializarDatos() {
        CasaSubasta miCasa = new CasaSubasta("Subastas UQ");

        Comprador compra = new Comprador("Jose", "Florez", "2323", "34", "sa", "jose@gmail.com",
                "12", TipoUsuario.COMPRADOR, false);

        Anunciante anunciante = new Anunciante("santiago","Ovalle","01","19","sa","san@gmial","23",TipoUsuario.ANUNCIANTE,false,0);

        Comprador compradorAux= new Comprador("juan","Florez", "1010","19","juan","juan@gmail.com","1234",TipoUsuario.COMPRADOR,true);
        miCasa.getListaUsuarios().add(compra);
        miCasa.getListaUsuarios().add(anunciante);

        miCasa.getListaCompradores().add(compra);
        miCasa.getListaAnunciantes().add(anunciante);
        miCasa.getListaCompradores().add(compradorAux);
        return miCasa;
        }
}


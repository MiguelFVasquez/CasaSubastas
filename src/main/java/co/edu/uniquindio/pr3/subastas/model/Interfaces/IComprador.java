package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import co.edu.uniquindio.pr3.subastas.model.Comprador;

public interface IComprador {
    //Metodos de compra o puja

    public boolean crearPuja(String codigoAnuncio, Comprador comprador, Double valor, String fecha);
    public boolean eliminarPuja(String codigoPuja);
}

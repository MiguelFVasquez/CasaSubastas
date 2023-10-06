package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastas.exceptions.PujaException;
import co.edu.uniquindio.pr3.subastas.model.Anuncio;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.Puja;

public interface IComprador {
    //Metodos de compra o puja

    public boolean crearPuja(Puja newPuja) throws PujaException, AnuncioException;
    public boolean eliminarPuja(Puja pujaEliminar) throws PujaException;
}

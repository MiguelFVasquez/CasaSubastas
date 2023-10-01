package co.edu.uniquindio.pr3.subastas.model;

import java.util.List;

public class Comprador {
    private List<Puja> listaPujas;
    private List<Integer> cantidadDeVecesPujada;


    public Comprador() {
    }

    public Comprador(List<Puja> listaPujas, List<Integer> cantidadDeVecesPujada) {
        this.listaPujas = listaPujas;
        this.cantidadDeVecesPujada = cantidadDeVecesPujada;
    }

    public List<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    public List<Integer> getCantidadDeVecesPujada() {
        return cantidadDeVecesPujada;
    }

    public void setCantidadDeVecesPujada(List<Integer> cantidadDeVecesPujada) {
        this.cantidadDeVecesPujada = cantidadDeVecesPujada;
    }
}

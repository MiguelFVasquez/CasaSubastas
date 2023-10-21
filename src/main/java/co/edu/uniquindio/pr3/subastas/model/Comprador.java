package co.edu.uniquindio.pr3.subastas.model;

import co.edu.uniquindio.pr3.subastas.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastas.exceptions.PujaException;
import co.edu.uniquindio.pr3.subastas.model.Interfaces.IComprador;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Comprador extends Usuario implements IComprador, Serializable {
    private static final long serialVersionUID = 1l;
    private List<Puja> listaPujas;
    private List<Integer> cantidadDeVecesPujada;


    public Comprador() {
    }

    public Comprador(List<Puja> listaPujas, List<Integer> cantidadDeVecesPujada) {
        this.listaPujas = listaPujas;
        this.cantidadDeVecesPujada = cantidadDeVecesPujada;
    }

    public Comprador(String nombre , String apellidos , String id , String edad , String usuario ,
                     String correo , String password , TipoUsuario tipoUsuario , boolean autenticado , List<Puja> pujas , List<Integer> vecesPujas) {
        super(nombre, apellidos, id, edad, usuario, correo, password, tipoUsuario, autenticado);
        this.listaPujas = pujas;
        this.cantidadDeVecesPujada = vecesPujas;
    }

    public Comprador(String nombre , String apellidos , String id , String edad , String usuario , String correo , String password ,
                     TipoUsuario tipoUsuario , boolean autenticado) {
        super(nombre, apellidos, id, edad, usuario, correo, password, tipoUsuario, autenticado);
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



    //----------------Metodos del Comprador (PUJAS)-------------------------------------------

    /**
     *
     * @param codigo
     * @return
     */
    public boolean verificarPuja(String codigo){
        boolean encontrado= false;
        List<Puja> pujasIguales= this.listaPujas.stream()
                .filter(p->p.getCodigo().equals(codigo))
                .collect(Collectors.toList());

        if (!pujasIguales.isEmpty()){
            encontrado=true;
        }
        return encontrado;
    }

    /**
     *
     * @param codigo
     * @return
     */
    public Puja obtenerPuja(String codigo) {
        Optional<Puja> pujaOptional= listaPujas.stream()
                .filter(p->p.getCodigo().equals(codigo))
                .findFirst();
        return pujaOptional.orElse(null);
    }

    @Override
    public boolean crearPuja(Puja newPuja) throws PujaException, AnuncioException {
        boolean creado= false;
        if (verificarPuja(newPuja.getCodigo())){
            throw new PujaException("Ya existe una puja con el mismo códgo");
        }else if (newPuja.getAnuncio()==null){
            throw new AnuncioException("El anuncio por el que desea pujar ya no existe");
        }else {
            newPuja.getAnuncio().getListaPujas().add(newPuja);
            creado=true;
            listaPujas.add(newPuja);
        }
        return creado;
    }

    @Override
    public boolean eliminarPuja(Puja pujaEliminar) throws PujaException {
        boolean eliminado=false;
        Puja pujaAux= obtenerPuja(pujaEliminar.getCodigo());
        if (pujaAux==null){
            throw new PujaException("La puja que desea eliminar no ha sido encontrada");
        }else {
            pujaEliminar.getAnuncio().getListaPujas().remove(pujaEliminar);
            eliminado=true;
            listaPujas.remove(pujaAux);
        }

        return eliminado;
    }
}

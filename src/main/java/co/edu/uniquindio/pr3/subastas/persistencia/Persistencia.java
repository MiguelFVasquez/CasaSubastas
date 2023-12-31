package co.edu.uniquindio.pr3.subastas.persistencia;

import co.edu.uniquindio.pr3.subastas.model.*;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.utils.ArchivoUtil;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Persistencia {

    //RUTAS

    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/co/edu/uniquindio/pr3/subastas/persistencia/archivos/archivoUsuarios.txt";

    public static final String RUTA_ARCHIVO_ANUNCIOS= "src/main/resources/co/edu/uniquindio/pr3/subastas/persistencia/archivos/archivoAnuncios.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/co/edu/uniquindio/pr3/subastas/persistencia/log/casaSubastas_Log.txt";
    public static final String RUTA_ARCHIVO_MODELO_CASASUBASTA_BINARIO = "src/main/resources/co/edu/uniquindio/pr3/subastas/persistencia/model.dat";

    public static final String RUTA_ARCHIVO_MODELO_CASASUBASTA_XML = "src/main/resources/co/edu/uniquindio/pr3/subastas/persistencia/model.xml";

    public static void cargarDatosArchivos(CasaSubasta casaSubasta) throws FileNotFoundException, IOException {
        //Cargar archivo de usuarios
        ArrayList<Usuario> usuariosCargados = cargarUsuarios();
        if (usuariosCargados.size() > 0) {
            casaSubasta.getListaUsuarios().addAll(usuariosCargados);
        }
        /*
        //Cargar archivos de anuncios
        ArrayList<Anuncio> anunciosCargados= cargarAnuncios();
        if (anunciosCargados.size()>0){
            casaSubasta.getListaAnuncios().addAll(anunciosCargados);
        }*/
        //Cargar Archivos de anunciantes

        //Cargar Archivos de Compras

        //Cargar archivo de Productos

    }

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     *
     * @param
     * @param
     * @throws IOException
     */
    public static void guardarUsuarios(List<Usuario> listaUsuarios) throws IOException {
        String contenido = "";
        for (Usuario usuario : listaUsuarios) {
            contenido += usuario.getNombre() + "@@"
                    + usuario.getApellido() + "@@"
                    + usuario.getIdentificacion() + "@@"
                    + usuario.getEdad() + "@@"
                    + usuario.getNombreUsuario() + "@@"
                    + usuario.getCorreo() + "@@" +
                    usuario.getContrasenia() + "@@" +
                    usuario.getTipoUsuario() + "@@" +
                    usuario.isAutenticado()+ "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
    }
    public static void guardarAnuncios(List<Anuncio> listaAnuncios) throws IOException {
        String contenido = "";
        for (Anuncio anuncio : listaAnuncios) {
            contenido += anuncio.getNombreAnunciante() + "@@"
                    + anuncio.getCodigo() + "@@"
                    + anuncio.getFechaInicio() + "@@"
                    + anuncio.getFechaFinal() + "@@"
                    + anuncio.getProducto() + "@@"
                    + anuncio.getListaPujas() + "@@" ;
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIOS, contenido, false);
    }



//	----------------------LOADS------------------------

    private static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//
            Usuario usuario = new Usuario();
            usuario.setNombre(linea.split("@@")[0]);
            usuario.setApellido(linea.split("@@")[1]);
            usuario.setIdentificacion(linea.split("@@")[2]);
            usuario.setEdad(linea.split("@@")[3]);
            usuario.setNombreUsuario(linea.split("@@")[4]);
            usuario.setCorreo(linea.split("@@")[5]);
            usuario.setContrasenia(linea.split("@@")[6]);
            usuario.setTipoUsuario(TipoUsuario.valueOf(linea.split("@@")[7]));
            usuario.setAutenticado(Boolean.parseBoolean(linea.split("@@")[8]));

            usuarios.add(usuario);
        }
        return usuarios;
    }


    private static ArrayList<Anuncio> cargarAnuncios() throws IOException {
        ArrayList<Anuncio> anuncios= new ArrayList<>();
        ArrayList<String> contenido= ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//
            Anuncio anuncio= new Anuncio();
            anuncio.setNombreAnunciante(linea.split("@@")[0]);
            anuncio.setCodigo(linea.split("@@")[1]);
            anuncio.setFechaInicio(linea.split("@@")[2]);
            anuncio.setFechaFinal(linea.split("@@")[3]);
            anuncio.setProducto(linea.split("@@")[4]);
            //anuncio.setListaPujas(linea.split("@@")[5]);
            anuncios.add(anuncio);
        }

        return anuncios;
    }

    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioException {

        if (validarUsuario(usuario, contrasenia)) {
            return true;
        } else {
            throw new UsuarioException("Usuario no existe");
        }

    }

    private static boolean validarUsuario(String usuario, String contrasenia) throws IOException {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if (usuarioAux.getNombreUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
        String linea = "";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Usuario usuario = new Usuario();
            usuario.setNombre(linea.split("@@")[0]);
            usuario.setApellido(linea.split("@@")[1]);
            usuario.setIdentificacion(linea.split("@@")[2]);
            usuario.setEdad(linea.split("@@")[3]);
            usuario.setNombreUsuario(linea.split("@@")[4]);
            usuario.setCorreo(linea.split("@@")[5]);
            usuario.setContrasenia(linea.split("@@")[6]);
            usuario.setTipoUsuario(TipoUsuario.valueOf(linea.split("@@")[7]));
            usuario.setAutenticado(Boolean.parseBoolean(linea.split("@@")[8]));
        }
        return usuarios;
    }
//	----------------------SAVES------------------------

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

//------------------------------------SERIALIZACIÓN  y XML

    public static CasaSubasta cargarRecursoCasaSubastaBinario() {
        CasaSubasta casaSubasta = null;
            try {
                casaSubasta = (CasaSubasta)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASASUBASTA_BINARIO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return casaSubasta;
    }

    public static void guardarRecursoCasaSubastaBinario(CasaSubasta casaSubasta) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASASUBASTA_BINARIO, casaSubasta);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static CasaSubasta cargarRecursoCasaSubastaXML() {

        CasaSubasta casaSubasta = null;

        try {
            casaSubasta = (CasaSubasta) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_CASASUBASTA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return casaSubasta;

    }

    public static void guardarRecursoCasaSubastaXML(CasaSubasta casaSubasta) {
        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_CASASUBASTA_XML, casaSubasta);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static String leerArchivoXML(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }

    public static void escribirArchivoXML(String contenido, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

}


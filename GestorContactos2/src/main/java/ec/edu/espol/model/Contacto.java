package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 8799656478674716638L;
    protected String nombre;
    protected String perfil;
    protected LinkedList<String> fotos;
    protected LinkedList<String> correos;
    protected LinkedList<String> telefonos;
    protected LinkedList<Contacto> contactos;

    public Contacto(String nombre, String perfil, LinkedList<String> fotos, LinkedList<String> correos, LinkedList<String> telefonos, LinkedList<Contacto> contactos) {
        this.nombre = nombre;
        this.perfil = perfil;
        this.fotos = fotos;
        this.correos = correos;
        this.telefonos = telefonos;
        this.contactos = contactos;
    }
    
    
    //Aplicar clase atributo
    /*
    protected LinkedList<String> fotos;
    protected LinkedList<Atributo<String>> correos;
    protected LinkedList<Atributo<String>> telefonos;
    protected ArrayList<Atributo<Contacto>> contactosAsociados;
    
    public Contacto(String nombre, String perfil, LinkedList<String> fotos, LinkedList<Atributo<String>> telefonos, ArrayList<Atributo<Contacto>> contactosAsociados) {
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
        this.fotos = fotos;
        this.telefonos = telefonos;
        this.contactosAsociados = contactosAsociados;
    }*/
    
    //Pasar a utilitaria y comparar con saveFile========================================================
    public LinkedList<Contacto> leerContacto(){
        LinkedList<Contacto> lista = new LinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("nombre del archivo"))) {
            lista = (LinkedList<Contacto>) in.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException:" + ex.getMessage());
        }
        return lista;
    }
    //==================================================================================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public LinkedList<String> getFotos() {
        return fotos;
    }

    public void setFotos(LinkedList<String> fotos) {
        this.fotos = fotos;
    }

    public LinkedList<String> getCorreos() {
        return correos;
    }

    public void setCorreos(LinkedList<String> correos) {
        this.correos = correos;
    }

    public LinkedList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(LinkedList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public LinkedList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(LinkedList<Contacto> contactos) {
        this.contactos = contactos;
    }

}
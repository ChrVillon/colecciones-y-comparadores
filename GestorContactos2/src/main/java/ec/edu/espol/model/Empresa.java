package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Empresa extends Contacto{
    private String departamento;
    private String sitioWeb;

    public Empresa(String departamento, String sitioWeb, String nombre, String perfil, LinkedList<String> fotos, LinkedList<String> correos, LinkedList<String> telefonos, LinkedList<Contacto> contactos) {
        super(nombre, perfil, fotos, correos, telefonos, contactos);
        this.departamento = departamento;
        this.sitioWeb = sitioWeb;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

}

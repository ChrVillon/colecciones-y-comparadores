package ec.edu.espol.model;

import java.util.LinkedList;

public class Persona extends Contacto{
    private String apodo;
    private String apellido;

    public Persona(String apodo, String apellido, String nombre, String perfil, LinkedList<String> fotos, LinkedList<String> correos, LinkedList<String> telefonos, LinkedList<Contacto> contactos) {
        super(nombre, perfil, fotos, correos, telefonos, contactos);
        this.apodo = apodo;
        this.apellido = apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}

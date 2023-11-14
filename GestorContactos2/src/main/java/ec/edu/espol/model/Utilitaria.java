package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Utilitaria {
    
    public static <E> void saveFile(E e, String nomfile) {
        System.out.println("Guardando: " + e.toString());
        File archivo = new File(nomfile);
        if (archivo.exists()) {
            try (myObjectOutputStream out = new myObjectOutputStream(new FileOutputStream(nomfile, true))) {
                out.writeObject(e);
            } catch (Exception ex) {
            }
        } else {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))) {
                out.writeObject(e);
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }
        }
        //Lo que yo puedo leer
        File file = new File("Copy"+nomfile);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(e.toString());
            writer.close();
        } catch (IOException ex) {
        }
    }
    
    //Revisar para usar generics y return List
    public static ArrayList<Contacto> readFileContacto(String nomfile){
        ArrayList<Contacto> retorno = new ArrayList<>();
        Contacto e;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomfile))){
            while(true){
                e = (Contacto) in.readObject();
                System.out.println("Leyendo: "+e.toString());
                retorno.add(e);
                //retorno.addLast(e);
            }
        }catch(Exception ex){
        }
        return retorno;
    }
}

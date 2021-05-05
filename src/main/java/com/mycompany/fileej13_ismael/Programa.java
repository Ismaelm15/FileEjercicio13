/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author ismae
 */
public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException, IOException, ParseException {
        //creacion de variables
        ArrayList<App> lista = new ArrayList<>();
        //creacion de las apps, adicion a la lista y muestre por pantalla 
        for (int i = 0; i < 50; i++) {
            lista.add(new App());
        }
        for (App app : lista) {
            System.out.println(app.toString());
        }
        crearDirectorios(lista);//Creacion de directorios

    }

    public static void crearDirectorio(String ruta) {//funcion para crear directorios
        Path directory = Paths.get(ruta);
        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }

    }

    public static void crearDirectorios(ArrayList<App> lista) {
        ServicioFicheroTSV tsv = new ServicioFicheroTSV();
        ServicioFicheroXML xml = new ServicioFicheroXML();
        ServicioFicheroJSON json = new ServicioFicheroJSON();
        //Creacion de los directorios necesarios y de los ficheros para todas las apps y para cada app
        crearDirectorio("./appstsv");
        crearDirectorio("./appsxml");
        crearDirectorio("./appsjson");
        crearDirectorio("./copias");
        crearDirectorio("./aplicaciones");
        try {
            xml.generarFicheroXML("./appsxml/aplicaciones.xml", lista);
        } catch (JAXBException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        tsv.generarFicheroTSV("./appstsv/aplicaciones.tsv", lista);
        json.escribirJSON("./appsjson/aplicaciones.json", lista);
        for (App app : lista) {
            ServicioFicheroJSON.escribirJSON("./aplicaciones/" + app.getNombre() + ".json", app);
        }

    }
}

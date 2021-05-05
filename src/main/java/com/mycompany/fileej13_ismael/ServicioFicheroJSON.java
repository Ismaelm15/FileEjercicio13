/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.istack.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author ismae
 */
public class ServicioFicheroJSON {

    public void escribirJSON(String idFichero, ArrayList<App> lista) {//funcion para escribir un archivo con todas las apps

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            // Escribe en un fichero JSON el catálogo de muebles
            mapeador.writeValue(new File(idFichero), lista);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void escribirJSON(String idFichero, App app) {//funcion para escribir un archivo con una sola app

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            // Escribe en un fichero JSON el catálogo de muebles
            mapeador.writeValue(new File(idFichero), app);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<App> leerJSON(String idFichero){//funcion para leer un archivo de apps
        ObjectMapper mapeador = new ObjectMapper();

        ArrayList<App> catalogo = null;
        try {
            catalogo = mapeador.readValue(new File(idFichero),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class, App.class));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("---- Catálogo de apps ----");
        for (App app : catalogo) {
            System.out.println(app.toString());
        }
        return catalogo;

    }

    public App leerJSONUnaApp(String idFichero){//funcion para leer solo una app
        App aplicacion = new App();

        ObjectMapper mapeador = new ObjectMapper();
        try {
            aplicacion = mapeador.readValue(new File(idFichero), App.class);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aplicacion;

    }

}

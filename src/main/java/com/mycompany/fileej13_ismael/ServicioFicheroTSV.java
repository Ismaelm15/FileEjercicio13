/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ismae
 */
public class ServicioFicheroTSV {

    public void generarFicheroTSV(String idFichero, ArrayList<App> lista) {//funcion para la generacion de un fichero TSV

        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            for (App app : lista) {
                flujo.write(app.toString());
                flujo.newLine();
            }

            // Metodo flush() guarda cambios en disco 
            flujo.flush();

            System.out.println(
                    "Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author ismae
 */
public class PruebaEj13 {

    public static void main(String[] args) throws IOException {
        ejecucionJson();
        mapearBaseClave();
    }

    public static ArrayList<String> listarDirectorio(String ruta) {//funcion para listar los directorios
        ArrayList<String> lista = new ArrayList();
        File f = new File(ruta);
        if (f.exists()) {
            // Obtiene los ficheros y directorios dentro de f y los 
            // devuelve en un array
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
                lista.add(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }
        return lista;
    }

    public static void borrarElemento(String ruta) {//funcion para borrar los elementos
        Path file = Paths.get(ruta);
        try {
            Files.delete(file);
        } catch (NoSuchFileException nsfe) {
            System.out.println("No se puede borrar " + ruta + " porque no existe");
        } catch (DirectoryNotEmptyException dnee) {
            System.out.println("No se puede borrar el directorio porque no está vacío");
        } catch (IOException e) {
            System.out.println("Problema borrando el elemento " + ruta);
        }
    }

    public static void ejecucionJson() throws IOException {//funcion que lee una app de un fichero, la convierte en App y borra su fichero
        //Declaracion de variables

        Scanner scn = new Scanner(System.in);

        ArrayList<String> nombresApp = new ArrayList<>();
        String appBuscada = "", eleccion;
        App aplicacion = new App();

        ServicioFicheroJSON json = new ServicioFicheroJSON();
        nombresApp = listarDirectorio("./aplicaciones");//metodo que guarda todos los nombres de las apps en un ArrayList
        //metodo para la eleccion de app
        System.out.println("Que app desea leer de las de arriba");
        eleccion = scn.nextLine();
        for (String nombreApp : nombresApp) {
            if (nombreApp.equalsIgnoreCase(nombreApp)) {//comprobacion de que exista la app
                aplicacion = json.leerJSONUnaApp("./aplicaciones/" + eleccion + ".json");//lectura muestre por pantalla y borrado del fichero de la app
                System.out.println(aplicacion.toString());
                borrarElemento("./aplicaciones/" + eleccion + ".json");
                break;
            }
        }
        listarDirectorio("./aplicaciones");//listar el directorio para comprobar que la app ha sido borrada

    }

    public static void mapearBaseClave() throws IOException {
        
        ServicioFicheroJSON json = new ServicioFicheroJSON();
        ArrayList<App> listaApps = new ArrayList();
        listaApps = json.leerJSON("./appsjson/aplicaciones.json");//metodo que lee el fichero con todas las apps
        System.out.println("");
        Map<String, LocalDate> listaPeso = listaApps.stream()
                .filter(p -> p.getTamañoKb() > 200 && p.getTamañoKb() < 500)//filtrado por tamaño
                .sorted((v1,v2)->v1.getNombre().compareTo(v2.getNombre()))
                .collect(Collectors.toMap(p -> p.getNombre(), p -> p.getFechaCreacion()));//mapeo por nombre(key) y fecha(Value)
        for (Map.Entry<String, LocalDate> entry : listaPeso.entrySet()) {//mostrar por pnatalla el map
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

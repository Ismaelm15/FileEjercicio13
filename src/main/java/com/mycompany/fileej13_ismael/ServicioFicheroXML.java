/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ismae
 */
public class ServicioFicheroXML {

    public void generarFicheroXML(String idFichero, ArrayList<App> lista) throws JAXBException {
        // Se preparan los objetos a utilizar, en esta caso un catálogo

        // Se preparan los objetos a utilizar, en esta caso un catálogo
        CatalogoApps catalogo = new CatalogoApps();
        catalogo.setLista(lista);
        catalogo.setDescripcion("Mi catalogo");
       
        // Crea el contexto JAXB. Se encarga de definir los objetos 
        // que vamos a guardar. En nuestro caso sólo el tipo CatalogoApps
        JAXBContext contexto = JAXBContext.newInstance(CatalogoApps.class);
        
        // El contexto JAXB permite crear un objeto Marshaller, que sirve para
        // generar la estructura del fichero XML 
        // El proceso de pasar objetos Java (CatalogoApps) a ficheros XML 
        // se conoce como "marshalling" o "serialización"
        Marshaller serializador = contexto.createMarshaller();
        
        // Especificamos que la propiedad del formato de salida
        // del serializador sea true, lo que implica que el formato se 
        // realiza con indentación y saltos de línea
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Llamando al método de serialización marshal (sobrecargado) se pueden
        // serializar objetos java en formato XML y volcarlos donde necesitemos:
        // consola, ficheros. El proceso consiste en que el contexto es el 
        // encargo de leer los objetos java, pasarlos al serializador y éste 
        // crear la salida de serialización
        
        // Serialización y salida por consola
        serializador.marshal(catalogo, System.out);

        // Volcado al fichero xml
        serializador.marshal(catalogo, new File(idFichero));
    }

    public void leerFicheroXML(String idFichero) {

        // Crea el contexto JAXB 
        JAXBContext contexto = null;
        try {
            contexto = JAXBContext.newInstance(CatalogoApps.class);
        } catch (JAXBException ex) {
            Logger.getLogger(ServicioFicheroXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Crea el objeto Unmarshaller
        Unmarshaller um = null;
        try {
            um = contexto.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(ServicioFicheroXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Llama al método de unmarshalling
        CatalogoApps catalogo = null;
        try {
            catalogo = (CatalogoApps) um.unmarshal(new File(idFichero));
        } catch (JAXBException ex) {
            Logger.getLogger(ServicioFicheroXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<App> listaMuebles = catalogo.getListaApps();

        listaMuebles.forEach(System.out::println);
    }
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import javax.xml.bind.JAXBException;

/**
 *
 * @author ismae
 */
public class PruebaEj12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException {//funcion para leer los ficheros XML
        ServicioFicheroXML xml= new ServicioFicheroXML();
        
        xml.leerFicheroXML("./appsxml/aplicaciones.xml");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ismae
 */


@XmlRootElement(namespace = "ejemploJAXB")
public class CatalogoApps {
    
    // XmLElementWrapper define un contenedor para la lista 
    // de muebles
    @XmlElementWrapper(name = "catalogoApp")

    // XmlElement establece el nombre de los elementos
    // Cada elemento de la lista llevará esta etiqueta en el fichero xml
    @XmlElement(name = "app")
    private ArrayList<App> listaApps;

    private String descripcion;

    public ArrayList<App> getListaApps() {
        return listaApps;
    }

    public void setLista(ArrayList<App> lista) {
        this.listaApps = lista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

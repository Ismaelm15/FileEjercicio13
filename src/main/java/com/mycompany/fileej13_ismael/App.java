/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * @author ismae
 */

@XmlRootElement(name = "App")
@XmlAccessorType(XmlAccessType.FIELD)

public class App {

    private static int codigo = 0;
    private String nombre, descripcion;
    private double tamañoKb;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate fechaCreacion=LocalDate.now();

    

    public App() {//genera una app aleatoria
        generarApp();
    }

    public App(String nombre, String descripcion, double tamañoKb, LocalDate fechaCreacion) {//constructor parametrizado
        codigo++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamañoKb = tamañoKb;
        this.fechaCreacion = fechaCreacion;
    }

    private String generarDescripcion(int random) {//genera una descripcion en un string entre 10 que existen
        switch (random) {
            case 0: {
                return "Esta bueno";
            }

            case 1: {
                return "Esta regular";
            }

            case 2: {
                return "Esta malo";
            }

            case 3: {
                return "Tiene fallos";
            }

            case 4: {
                return "Funciona perfectamente";
            }

            case 5: {
                return "Tiene bugs";
            }

            case 6: {
                return "No funciona para android 11";
            }

            case 7: {
                return "Edicion limitada";
            }

            case 8: {
                return "Falla al inicio";
            }

            default: {
                return "No tiene compatibilidad con Samsung";
            }
        }
    }
    
    private void generarFecha(){//genera una fecha en formato LocalDate aleatoria
    Random rnd = new Random();
    int dia,mes,anio;
    anio=rnd.nextInt(21)+2000;
    mes=rnd.nextInt(12)+1;
    if (mes==1||mes==3||mes==5||mes==7||mes==8||mes==10||mes==12){
        dia=rnd.nextInt(31)+1;
    }else if (mes==2){
        if (anio % 4 == 0 && anio % 100 != 0 || anio % 400 == 0){
        dia=rnd.nextInt(29)+1;
        }else{
            dia=rnd.nextInt(28)+1;}
    }else{
        dia=rnd.nextInt(30)+1;
    }
    this.fechaCreacion=LocalDate.of(anio,mes,dia);
    }
    


    public String getNombre() {//getters y setters
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTamañoKb() {
        return tamañoKb;
    }

    public void setTamañoKb(double tamañoKb) {
        this.tamañoKb = tamañoKb;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {//equals y hashcode
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Objects.hashCode(this.descripcion);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.tamañoKb) ^ (Double.doubleToLongBits(this.tamañoKb) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.fechaCreacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final App other = (App) obj;
        if (Double.doubleToLongBits(this.tamañoKb) != Double.doubleToLongBits(other.tamañoKb)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        return true;
    }





    @Override
    public String toString() {//to string
        return nombre +"\t"+descripcion +"\t"+ tamañoKb +"\t"+fechaCreacion;
    }
    private double generarTamanio() {
        double VALORMAX = 1024, VALORMIN = 100;
        Random rnd = new Random();
        
        return rnd.doubles(1, VALORMIN, VALORMAX).sum();
    }
    private App generarApp(){//metodo que genera una app con un nombre en formato App+codigo+letra aleatori, una descripcion un tamaño y una fecha aleatoria
        Random rnd = new Random();
        codigo++;
        this.nombre = "App" + codigo + (char) (rnd.nextInt(26) + 'a');
        this.descripcion = generarDescripcion(rnd.nextInt(10));
        this.tamañoKb=generarTamanio();
        generarFecha();
        return this;
    }
}

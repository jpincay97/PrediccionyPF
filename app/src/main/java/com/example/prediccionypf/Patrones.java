package com.example.prediccionypf;

import java.io.Serializable;
import java.util.ArrayList;

public class Patrones implements Serializable {
    private String algoritmo;
    private Double soporte, tiempo_ejecucion, consumo_memoria;
    private ArrayList<Double> soportes;
    private ArrayList<String> patrones_frecuentes;

    public Patrones() {
    }

    public Patrones(String algoritmo, Double soporte, Double tiempo_ejecucion, Double consumo_memoria, ArrayList<Double> soportes, ArrayList<String> patrones_frecuentes) {
        this.algoritmo = algoritmo;
        this.soporte = soporte;
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.consumo_memoria = consumo_memoria;
        this.soportes = soportes;
        this.patrones_frecuentes = patrones_frecuentes;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public Double getSoporte() {
        return soporte;
    }

    public void setSoporte(Double soporte) {
        this.soporte = soporte;
    }

    public Double getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempo_ejecucion(Double tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public Double getConsumo_memoria() {
        return consumo_memoria;
    }

    public void setConsumo_memoria(Double consumo_memoria) {
        this.consumo_memoria = consumo_memoria;
    }

    public ArrayList<Double> getSoportes() {
        return soportes;
    }

    public void setSoportes(ArrayList<Double> soportes) {
        this.soportes = soportes;
    }

    public ArrayList<String> getPatrones_frecuentes() {
        return patrones_frecuentes;
    }

    public void setPatrones_frecuentes(ArrayList<String> patrones_frecuentes) {
        this.patrones_frecuentes = patrones_frecuentes;
    }
}

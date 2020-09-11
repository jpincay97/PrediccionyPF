package com.example.prediccionypf;

public class PatronesSoportes {
    private String patron;
    private Double soporte;

    public PatronesSoportes() {
    }

    public PatronesSoportes(String patron, Double soporte) {
        this.patron = patron;
        this.soporte = soporte;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public Double getSoporte() {
        return soporte;
    }

    public void setSoporte(Double soporte) {
        this.soporte = soporte;
    }
}

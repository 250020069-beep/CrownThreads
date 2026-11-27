package com.example.crowthreads.modelo.entity;

public class Atuendo {
    private int id;
    private String nombreDiseno;
    private String personaje;
    private int vestidoId;
    private int zapatillasId;
    private int sombreroId;
    private int aretesId;
    private int peloId;

    public Atuendo() {
    }

    public Atuendo(int id, String nombreDiseno, String personaje, int vestidoId,
                   int zapatillasId, int sombreroId, int aretesId, int peloId) {
        this.id = id;
        this.nombreDiseno = nombreDiseno;
        this.personaje = personaje;
        this.vestidoId = vestidoId;
        this.zapatillasId = zapatillasId;
        this.sombreroId = sombreroId;
        this.aretesId = aretesId;
        this.peloId = peloId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreDiseno() { return nombreDiseno; }
    public void setNombreDiseno(String nombreDiseno) { this.nombreDiseno = nombreDiseno; }

    public String getPersonaje() { return personaje; }
    public void setPersonaje(String personaje) { this.personaje = personaje; }

    public int getVestidoId() { return vestidoId; }
    public void setVestidoId(int vestidoId) { this.vestidoId = vestidoId; }

    public int getZapatillasId() { return zapatillasId; }
    public void setZapatillasId(int zapatillasId) { this.zapatillasId = zapatillasId; }

    public int getSombreroId() { return sombreroId; }
    public void setSombreroId(int sombreroId) { this.sombreroId = sombreroId; }

    public int getAretesId() { return aretesId; }
    public void setAretesId(int aretesId) { this.aretesId = aretesId; }

    public int getPeloId() { return peloId; }
    public void setPeloId(int peloId) { this.peloId = peloId; }
}
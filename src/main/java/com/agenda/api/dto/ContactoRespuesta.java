package com.agenda.api.dto;

import java.util.List;

public class ContactoRespuesta {
    private List<ContactoDTO> contenido;
    private int numeroPag;
    private int tamanioPag;
    private long totalElementos;
    private int totalPag;
    private boolean ultima;

    public ContactoRespuesta() {
    }

    public List<ContactoDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<ContactoDTO> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroPag() {
        return numeroPag;
    }

    public void setNumeroPag(int numeroPag) {
        this.numeroPag = numeroPag;
    }

    public int getTamanioPag() {
        return tamanioPag;
    }

    public void setTamanioPag(int tamanioPag) {
        this.tamanioPag = tamanioPag;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPag() {
        return totalPag;
    }

    public void setTotalPag(int totalPag) {
        this.totalPag = totalPag;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }
}

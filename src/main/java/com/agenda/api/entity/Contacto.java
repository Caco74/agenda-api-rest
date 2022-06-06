package com.agenda.api.entity;

import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;
import com.agenda.api.recursos.utils.AppConstantes;

import javax.persistence.*;

import java.util.stream.IntStream;

@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String telefono;

    public Contacto() {}

    public Contacto(Long id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ContactoInvalidDataException {
        guardar(telefono);
    }

    public void guardar(String telefono) throws ContactoInvalidDataException {
        String buenosAires = telefono.substring(0,2);
        String codigoCiudadGrande = telefono.substring(0,3);
        String codigoRestoCiudades = telefono.substring(0,4);

        if (buenosAires.equals("11")) {
            this.telefono = "0" + formatoTelefono(telefono, 2, 7);
        } else if (esCiudadGrande(AppConstantes.CIUDADES_GRANDES, codigoCiudadGrande)){
            this.telefono = "0" + formatoTelefono(telefono, 3, 7);
        } else if (esRestoCiudades(AppConstantes.RESTO_CIUDADES, codigoRestoCiudades)){
            this.telefono = "0" + formatoTelefono(telefono, 4, 8);
        } else {
            throw new ContactoInvalidDataException("Código de área incorrecto.");
        }
    }

    public String formatoTelefono(String telefono, int espacio, int guion) {
        StringBuilder formato = new StringBuilder();
        int digitos = 0;

        for (int i =0; i<telefono.length();i++) {
            if (digitos==espacio) {
                formato.append(" ");
            }
            formato.append(telefono.charAt(i));
            digitos++;
        }

        StringBuilder numero = new StringBuilder();
        digitos=0;

        for (int i = 0; i<formato.length();i++){
            if (digitos==guion) {
                numero.append("-");
            }
            numero.append(formato.charAt(i));
            digitos++;
        }
        return numero.toString();
    }

    public boolean esCiudadGrande(int[] prefijos, String codigoDeArea){
        return IntStream.of(prefijos).anyMatch(p -> p == Integer.parseInt(codigoDeArea));
    }

    public boolean esRestoCiudades(int[] prefijos, String codigoDeArea) {
        return IntStream.of(prefijos).anyMatch(p -> p == Integer.parseInt(codigoDeArea));
    }

}

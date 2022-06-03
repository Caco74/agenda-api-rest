package com.agenda.api.entity;

import com.agenda.api.utils.AppConstantes;

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

    public void setTelefono(String telefono) {

        boolean esNumero = telefono.chars()
                .allMatch( Character::isDigit );

        if (!esNumero) {
            telefono = formatearNumero(telefono);
        }
        guardar(telefono);
    }

    public void guardar(String telefono) {
        int prefTresDigitos = Integer.parseInt(telefono.substring(0,3));

        if (telefono.charAt(0) == '1') {
            this.telefono = "0" + formatoTelefono(telefono, 2, 7);
        } else if (esCiudadGrande(AppConstantes.CIUDADES_GRANDES, prefTresDigitos)){
            this.telefono = "0" + formatoTelefono(telefono, 3, 7);
        } else {
            this.telefono = "0" + formatoTelefono(telefono, 4, 8);
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

    public boolean esCiudadGrande(int[] prefijos, int codigoDeArea){
        return IntStream.of(prefijos).anyMatch(p -> p == codigoDeArea);
    }

    public static String formatearNumero(String telefono) {
        StringBuilder numeroDecodificado = new StringBuilder();

        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                numeroDecodificado.append(decodificarLetras(String.valueOf(telefono.charAt(i))));
            } else {
                numeroDecodificado.append(telefono.charAt(i));
            }
        }

        return numeroDecodificado.toString();
    }

    public static String decodificarLetras(String letra) {//throws IllegalArgumentException
        switch (letra) {
            case "a":
            case "b":
            case "c":
                letra = "2";
                break;
            case "d":
            case "e":
            case "f":
                letra = "3";
                break;
            case "g":
            case "h":
            case "i":
                letra = "4";
                break;
            case "j":
            case "k":
            case "l":
                letra = "5";
                break;
            case "m":
            case "n":
            case "o":
                letra = "6";
                break;
            case "p":
            case "q":
            case "r":
            case "s":
                letra = "7";
                break;
            case "t":
            case "u":
            case "v":
                letra = "8";
                break;
            case "w":
            case "x":
            case "y":
            case "z":
                letra = "9";
                break;
            default:
                System.out.println("Caracter desconocido.");
                //throw new IllegalArgumentException("No permitido")
        }

        return letra;
    }

}

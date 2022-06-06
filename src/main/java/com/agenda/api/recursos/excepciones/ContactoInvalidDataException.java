package com.agenda.api.recursos.excepciones;

public class ContactoInvalidDataException extends Exception {
    public static final String DESCRIPCION = "Error en los datos del contacto";

    public ContactoInvalidDataException() {
        super(DESCRIPCION);
    }

    public ContactoInvalidDataException(String detalle) {
        super(DESCRIPCION + ". " + detalle);
    }
}

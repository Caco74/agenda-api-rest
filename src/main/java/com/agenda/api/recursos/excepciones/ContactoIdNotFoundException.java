package com.agenda.api.recursos.excepciones;

public class ContactoIdNotFoundException extends Exception {

    public static final String DESCRIPCION = "ID de contacto no encontrado";

    public ContactoIdNotFoundException() {
        super(DESCRIPCION);
    }

    public ContactoIdNotFoundException(String detalle) {
        super(DESCRIPCION + ". " + detalle);
    }
}

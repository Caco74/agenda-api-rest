package com.agenda.api.recursos.excepciones;

public class ErrorMensaje {

    private String exception;
    private String mensaje;
    private String path;

    public ErrorMensaje(Exception exception, String path) {
        this(exception.getClass().getSimpleName(), exception.getMessage(), path);
    }

    public ErrorMensaje(String exception, String mensaje, String path) {
        this.exception = exception;
        this.mensaje = mensaje;
        this.path = path;
    }

    public String getException() {
        return exception;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getPath() {
        return path;
    }
}

package com.agenda.api.recursos;

import com.agenda.api.recursos.excepciones.ContactoIdNotFoundException;
import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;
import com.agenda.api.recursos.excepciones.ErrorMensaje;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ContactoIdNotFoundException.class})
    @ResponseBody
    public ErrorMensaje notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMensaje(exception, request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ContactoInvalidDataException.class})
    @ResponseBody
    public ErrorMensaje badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMensaje(exception, request.getRequestURI());
    }
}

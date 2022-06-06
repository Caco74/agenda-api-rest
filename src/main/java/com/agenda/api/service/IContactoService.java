package com.agenda.api.service;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;
import com.agenda.api.recursos.excepciones.ContactoIdNotFoundException;
import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;

import java.util.List;

public interface IContactoService {

    public ContactoRespuesta obtenerTodosLosContactos(int numeroPag, int tamanioPag, String ordenarPor, String sortDir);
    public ContactoDTO crearContacto(ContactoDTO contacto) throws ContactoInvalidDataException;
    public ContactoDTO obtenerContactoPorId(Long id) throws ContactoIdNotFoundException;

    public ContactoDTO actualizarContacto(ContactoDTO contacto, Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException;

    public void eliminarContacto(Long id) throws ContactoIdNotFoundException;

    public List<ContactoDTO> buscarPorNombre(String nombre);

}

package com.agenda.api.service;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;
import com.agenda.api.recursos.excepciones.ContactoIdNotFoundException;
import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;

import java.util.List;

public interface IContactoService {
    public List<ContactoDTO> obtenerTodosLosContactosV1();

    public ContactoRespuesta obtenerTodosLosContactosV2(int numeroPag, int tamanioPag, String ordenarPor, String sortDir);

    public ContactoDTO crearContactoV1(ContactoDTO contacto) throws ContactoInvalidDataException;

    public ContactoDTO crearContactoV2(ContactoDTO contacto) throws ContactoInvalidDataException;

    public ContactoDTO actualizarContactoV1(ContactoDTO contacto, Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException;

    public ContactoDTO actualizarContactoV2(ContactoDTO contacto, Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException;

    public ContactoDTO obtenerContactoPorId(Long id) throws ContactoIdNotFoundException;

    public void eliminarContacto(Long id) throws ContactoIdNotFoundException;

    public List<ContactoDTO> buscarPorNombre(String nombre);

}

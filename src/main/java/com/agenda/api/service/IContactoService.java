package com.agenda.api.service;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;

public interface IContactoService {

    public ContactoRespuesta obtenerTodosLosContactos(int numeroPag, int tamanioPag, String ordenarPor, String sortDir);
    public ContactoDTO crearContacto(ContactoDTO contacto);
    public ContactoDTO obtenerContactoPorId(Long id);

    public ContactoDTO actualizarContacto(ContactoDTO contacto, Long id);

    public void eliminarContacto(Long id);

}

package com.agenda.api.service;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.entity.Contacto;

import java.util.List;

public interface IContactoService {

    public List<ContactoDTO> obtenerTodosLosContactos();
    public ContactoDTO crearContacto(ContactoDTO contacto);

}
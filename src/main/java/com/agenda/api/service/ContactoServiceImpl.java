package com.agenda.api.service;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.entity.Contacto;
import com.agenda.api.repository.ContactoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactoServiceImpl implements IContactoService{

    ContactoRepository repository;

    public ContactoServiceImpl(ContactoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper mapper;

    @Override
    public ContactoDTO crearContacto(ContactoDTO contactoDto) {
        Contacto contacto = mapearEntidad(contactoDto);

        Contacto contactoNuevo = repository.save(contacto);

        return mapearDTO(contactoNuevo);
    }

    @Override
    public List<ContactoDTO> obtenerTodosLosContactos() {
        List<Contacto> contactos = repository.findAll();

        return contactos.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    // Convertir de Entidad a DTO
    private ContactoDTO mapearDTO(Contacto contacto) {
        return mapper.map(contacto, ContactoDTO.class);
    }

    // Convertir de DTO a Entidad
    private Contacto mapearEntidad(ContactoDTO contactoDto) {
        return mapper.map(contactoDto,Contacto.class);
    }
}

package com.agenda.api.service;

import com.agenda.api.controller.ContactoController;
import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.entity.Contacto;
import com.agenda.api.excepciones.ResourseNotFoundException;
import com.agenda.api.repository.ContactoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactoServiceImpl implements IContactoService{

    private final Logger log = LoggerFactory.getLogger(ContactoController.class);

    ContactoRepository repository;

    public ContactoServiceImpl(ContactoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper mapper;

    @Override
    public ContactoDTO crearContacto(ContactoDTO contactoDto) {
        Contacto contacto = mapearEntidad(contactoDto);

        return mapearDTO(repository.save(contacto));
    }

    @Override
    public List<ContactoDTO> obtenerTodosLosContactos() {
        List<Contacto> contactos = repository.findAll();

        return contactos.stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    @Override
    public ContactoDTO obtenerContactoPorId(Long id) {
        Contacto contacto = repository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Contacto", "id",id));
        return mapearDTO(contacto);
    }

    @Override
    public ContactoDTO actualizarContacto(ContactoDTO contactoDto, Long id) {
        Contacto contacto = repository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Contacto","id",id));

        contacto.setNombre(contactoDto.getNombre());
        contacto.setTelefono(contactoDto.getTelefono());


        return mapearDTO(repository.save(contacto));
    }

    @Override
    public void eliminarContacto(Long id) {
        Contacto contacto = repository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Contacto","id",id));
        repository.delete(contacto);
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

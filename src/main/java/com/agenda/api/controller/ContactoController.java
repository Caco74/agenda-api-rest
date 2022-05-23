package com.agenda.api.controller;

import com.agenda.api.entity.Contacto;
import com.agenda.api.repository.ContactoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactoController {

    ContactoRepository repository;

    public ContactoController(ContactoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/contactos")
    public List<Contacto> getContacts() {
        return repository.findAll();
    }




}

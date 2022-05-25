package com.agenda.api.controller;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.entity.Contacto;
import com.agenda.api.service.IContactoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contactos")
public class ContactoController {

    private final Logger log = LoggerFactory.getLogger(ContactoController.class);


    private IContactoService service;

    public ContactoController(IContactoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ContactoDTO>> listarContactos() {
        List<ContactoDTO> list = service.obtenerTodosLosContactos();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ContactoDTO> guardarContacto(@RequestBody ContactoDTO contacto) {
        if (contacto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.crearContacto(contacto));
    }

}

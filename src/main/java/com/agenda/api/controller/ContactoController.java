package com.agenda.api.controller;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;
import com.agenda.api.recursos.excepciones.ContactoIdNotFoundException;
import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;
import com.agenda.api.service.IContactoService;
import com.agenda.api.recursos.utils.AppConstantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    private final Logger log = LoggerFactory.getLogger(ContactoController.class);

    private IContactoService service;

    public ContactoController(IContactoService service) {
        this.service = service;
    }

    @GetMapping("/v1")
    public ResponseEntity<List<ContactoDTO>> listarContactos() {
        List<ContactoDTO> list = service.obtenerTodosLosContactosV1();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/v2")
    public ResponseEntity<ContactoRespuesta> listarContactos(
            @RequestParam(value="numeroPag", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPag,
            @RequestParam(value="tamanioPag", defaultValue = AppConstantes.TAMANIO_DE_PAGINA_POR_DEFECTO, required = false) int tamanioPag,
            @RequestParam(value="sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value="sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        ContactoRespuesta list = service.obtenerTodosLosContactosV2(numeroPag, tamanioPag, ordenarPor, sortDir);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<ContactoDTO> obtenerPorId(@PathVariable Long id) throws ContactoIdNotFoundException {
        return ResponseEntity.ok(service.obtenerContactoPorId(id));
    }

    @PostMapping("/v1")
    public ResponseEntity<ContactoDTO> guardarContactoV1(@RequestBody ContactoDTO contactoDto) throws ContactoInvalidDataException {
        return ResponseEntity.ok(service.crearContactoV1(contactoDto));
    }

    @PostMapping("/v2")
    public ResponseEntity<ContactoDTO> guardarContactoV2(@RequestBody ContactoDTO contactoDto) throws ContactoInvalidDataException {
        return ResponseEntity.ok(service.crearContactoV2(contactoDto));
    }

    @PutMapping("/v1/{id}")
    public ResponseEntity<ContactoDTO> actualizarContactoV1(@RequestBody ContactoDTO contactoDto, @PathVariable Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException {
        return ResponseEntity.ok(service.actualizarContactoV1(contactoDto, id));
    }

    @PutMapping("/v2/{id}")
    public ResponseEntity<ContactoDTO> actualizarContactoV2(@RequestBody ContactoDTO contactoDto, @PathVariable Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException {
        return ResponseEntity.ok(service.actualizarContactoV2(contactoDto, id));
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ContactoIdNotFoundException {
        service.eliminarContacto(id);
        return new ResponseEntity<>("Contacto eliminado correctamente.", HttpStatus.OK);
    }

    @GetMapping("/v1/busqueda")
    public ResponseEntity<List<ContactoDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }


}

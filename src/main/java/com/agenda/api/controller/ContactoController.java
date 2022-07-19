package com.agenda.api.controller;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;
import com.agenda.api.recursos.excepciones.ContactoIdNotFoundException;
import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;
import com.agenda.api.service.IContactoService;
import com.agenda.api.recursos.utils.AppConstantes;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactoController {

    private final Logger log = LoggerFactory.getLogger(ContactoController.class);

    private IContactoService service;

    public ContactoController(IContactoService service) {
        this.service = service;
    }

    @GetMapping("/v1/contactos")
    @ApiOperation(value = "Listar todos los contactos", notes = "Name search by %name% format", tags = { "api/v1/contactos" })
    public ResponseEntity<List<ContactoDTO>> listarContactos() {
        return ResponseEntity.ok(service.obtenerTodosLosContactosV1());
    }

    @GetMapping("/v2/contactos")
    @ApiOperation(value = "Listar todos los contactos usando paginación", tags = { "api/v2/contactos" })
    public ResponseEntity<ContactoRespuesta> listarContactos(
            @ApiParam(value = "Número de página")@RequestParam(value="numeroPag", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPag,
            @ApiParam(value = "Tamaño de página")@RequestParam(value="tamanioPag", defaultValue = AppConstantes.TAMANIO_DE_PAGINA_POR_DEFECTO, required = false) int tamanioPag,
            @ApiParam(value = "Ordenar respuesta por id")@RequestParam(value="sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @ApiParam(value = "Ordenar de forma ascendente o descendente")@RequestParam(value="sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return ResponseEntity.ok(service.obtenerTodosLosContactosV2(numeroPag, tamanioPag, ordenarPor, sortDir));
    }

    @ApiOperation(value = "Buscar contacto por id", tags = { "api/v1/contactos" })
    @GetMapping("/v1/contactos/{id}")
    public ResponseEntity<ContactoDTO> obtenerPorId(@PathVariable Long id) throws ContactoIdNotFoundException {
        return ResponseEntity.ok(service.obtenerContactoPorId(id));
    }

    @ApiOperation(value = "Crear contacto", tags = { "api/v1/contactos" })
    @PostMapping("/v1/contactos")
    public ResponseEntity<ContactoDTO> crearContactoV1(@RequestBody ContactoDTO contactoDto) throws ContactoInvalidDataException {
        return ResponseEntity.ok(service.crearContactoV1(contactoDto));
    }

    @ApiOperation(value = "Crear contacto", tags = { "api/v2/contactos" })
    @PostMapping("/v2/contactos")
    public ResponseEntity<ContactoDTO> crearContactoV2(@RequestBody ContactoDTO contactoDto) throws ContactoInvalidDataException {
        return ResponseEntity.ok(service.crearContactoV2(contactoDto));
    }
    @ApiOperation(value = "Actualizar contacto", tags = { "api/v1/contactos" })
    @PutMapping("/v1/contactos/{id}")
    public ResponseEntity<ContactoDTO> actualizarContactoV1(@RequestBody ContactoDTO contactoDto, @PathVariable Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException {
        return ResponseEntity.ok(service.actualizarContactoV1(contactoDto, id));
    }
    @ApiOperation(value = "Actualizar contacto", tags = { "api/v2/contactos" })
    @PutMapping("/v2/contactos/{id}")
    public ResponseEntity<ContactoDTO> actualizarContactoV2(@RequestBody ContactoDTO contactoDto, @PathVariable Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException {
        return ResponseEntity.ok(service.actualizarContactoV2(contactoDto, id));
    }

    @ApiOperation(value = "Eliminar contacto", tags = { "api/v1/contactos" })
    @DeleteMapping("/v1/contactos/{id}")
    public ResponseEntity<ContactoDTO> eliminar(@PathVariable Long id) throws ContactoIdNotFoundException {
        service.eliminarContacto(id);   
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Buscar contacto por nombre", tags = { "api/v1/contactos" })
    @GetMapping("/v1/contactos/busqueda")
    public ResponseEntity<List<ContactoDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }


}

package com.agenda.api.controller;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;
import com.agenda.api.entity.Contacto;
import com.agenda.api.service.IContactoService;
import com.agenda.api.utils.AppConstantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/contactos")
public class ContactoController {

    private final Logger log = LoggerFactory.getLogger(ContactoController.class);


    private IContactoService service;

    public ContactoController(IContactoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ContactoRespuesta> listarContactos(
            @RequestParam(value="numeroPag", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPag,
            @RequestParam(value="tamanioPag", defaultValue = AppConstantes.TAMANIO_DE_PAGINA_POR_DEFECTO, required = false) int tamanioPag,
            @RequestParam(value="sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value="sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        ContactoRespuesta list = service.obtenerTodosLosContactos(numeroPag, tamanioPag, ordenarPor, sortDir);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoDTO> obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(service.obtenerContactoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContactoDTO> guardarContacto(@RequestBody ContactoDTO contactoDto) {
        if (contactoDto.getId() != null) {
            log.warn("No puedes crear un contacto con id.");
            return ResponseEntity.badRequest().build();
        }

        if (contactoDto.getNombre() == null || contactoDto.getNombre().isEmpty()) {
            log.warn("No puedes crear un contacto sin nombre.");
            return ResponseEntity.badRequest().build();
        }

        if (contactoDto.getNombre().length() <= 3 || contactoDto.getNombre().length() >= 20) {
            log.warn("El nombre de contacto debe tener mínimo 3 caracteres");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.crearContacto(contactoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoDTO> actualizarContacto(@RequestBody ContactoDTO contactoDto, @PathVariable Long id) {

        return ResponseEntity.ok(service.actualizarContacto(contactoDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminarContacto(id);
        return new ResponseEntity<>("Contacto eliminado correctamente.", HttpStatus.OK);
    }

//    @GetMapping("/buscar/{nombre}")
//    public ResponseEntity<List<ContactoDTO>> buscarPorNombre(@RequestParam(value = "nombre", required = false) String nombre) {
//        List<ContactoDTO> contactoDto = service.buscarPorNombre(nombre);
//        return ResponseEntity.ok(contactoDto);
//    }

    @GetMapping("/busqueda")
    public ResponseEntity<List<ContactoDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }


}

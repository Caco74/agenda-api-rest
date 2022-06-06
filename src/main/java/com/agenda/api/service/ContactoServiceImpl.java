package com.agenda.api.service;

import com.agenda.api.controller.ContactoController;
import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.dto.ContactoRespuesta;
import com.agenda.api.entity.Contacto;
import com.agenda.api.recursos.excepciones.ContactoIdNotFoundException;
import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;
import com.agenda.api.recursos.utils.AppConstantes;
import com.agenda.api.repository.ContactoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public ContactoRespuesta obtenerTodosLosContactos(int numeroPag, int tamanioPag, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPag, tamanioPag, sort);

        Page<Contacto> contactos = repository.findAll(pageable);

        List<Contacto> listaDeContactos = contactos.getContent();

        List<ContactoDTO> contenido = listaDeContactos.stream().map(this::mapearDTO).collect(Collectors.toList());

        ContactoRespuesta contactoRespuesta = new ContactoRespuesta();
        contactoRespuesta.setContenido(contenido);
        contactoRespuesta.setNumeroPag(contactos.getNumber());
        contactoRespuesta.setTamanioPag(contactos.getSize());
        contactoRespuesta.setTotalElementos(contactos.getTotalElements());
        contactoRespuesta.setTotalPag(contactos.getTotalPages());
        contactoRespuesta.setUltima(contactos.isLast());

        return contactoRespuesta;
    }

    @Override
    public ContactoDTO crearContacto(ContactoDTO contactoDto) throws ContactoInvalidDataException {

        excepciones(contactoDto);

        comprobarCodigoDeArea(contactoDto);

        Contacto contacto = mapearEntidad(contactoDto);

        comprobarSiTieneLetraYGuardarNumero(contactoDto,contacto);

        return mapearDTO(repository.save(contacto));
    }

    @Override
    public ContactoDTO obtenerContactoPorId(Long id) throws ContactoIdNotFoundException {
        Contacto contacto = repository.findById(id)
                .orElseThrow(() -> new ContactoIdNotFoundException());
        return mapearDTO(contacto);
    }

    @Override
    public ContactoDTO actualizarContacto(ContactoDTO contactoDto, Long id) throws ContactoIdNotFoundException, ContactoInvalidDataException {

        Contacto contacto = repository.findById(id)
                .orElseThrow(() -> new ContactoIdNotFoundException("El id: " + id + " no corresponde a un contacto existente."));

        excepciones(contactoDto);

        comprobarCodigoDeArea(contactoDto);

        comprobarSiTieneLetraYGuardarNumero(contactoDto, contacto);

        return mapearDTO(repository.save(contacto));
    }

    @Override
    public void eliminarContacto(Long id) throws ContactoIdNotFoundException {
        Contacto contacto = repository.findById(id)
                .orElseThrow(() -> new ContactoIdNotFoundException("Contacto no encontrado"));
        repository.delete(contacto);
    }

    @Override
    public List<ContactoDTO> buscarPorNombre(String name) {

            //List<Contacto> contactbuscado = repository.findByNombreContainingIgnoreCase(name);
            //List<Contacto> contactbuscado = repository.findByNombreContainsIgnoreCase(name);
            List<Contacto> contactbuscado = repository.findByNombreIsContainingIgnoreCase(name);

            return contactbuscado.stream()
                    .map(this::mapearDTO)
                    .collect(Collectors.toList() );
    }

    // Convertir de Entidad a DTO
    private ContactoDTO mapearDTO(Contacto contacto) {
        return mapper.map(contacto, ContactoDTO.class);
    }

    // Convertir de DTO a Entidad
    private Contacto mapearEntidad(ContactoDTO contactoDto) {
        return mapper.map(contactoDto,Contacto.class);
    }

    public void excepciones(ContactoDTO contactoDto) throws ContactoInvalidDataException {
        if (contactoDto.getNombre() == null || contactoDto.getNombre().isEmpty()) {
            throw new ContactoInvalidDataException("El nombre de contacto no puede estar vacío.");
        }

        if (contactoDto.getTelefono().contains(" ") || contactoDto.getTelefono().contains("-")) {
            throw new ContactoInvalidDataException("El número de teléfono no puede contener espacios ni guiones.");
        }

        if (contactoDto.getNombre().length() < 3 || contactoDto.getNombre().length() > 20) {
            log.warn("El nombre de contacto debe tener entre 3 y 20 caracteres.");
            throw new ContactoInvalidDataException("El nombre de contacto debe tener entre 3 y 20 caracteres.");
        }

        if (contactoDto.getTelefono().equals("")) {
            throw new ContactoInvalidDataException("El número de teléfono es nulo.");
        }

        if (contactoDto.getTelefono().length() != 10) {
            throw new ContactoInvalidDataException("El número de teléfono es incorrecto.");
        }
    }

    public void comprobarCodigoDeArea(ContactoDTO contactoDto) throws ContactoInvalidDataException {
        boolean esNumero = contactoDto.getTelefono().substring(0,4).chars()
                .allMatch( Character::isDigit );
        String numero = contactoDto.getTelefono();

        if (!esNumero) {
            numero = formatearNumero(numero);
        }
        contactoDto.setTelefono(numero);

        String buenosAires = numero.substring(0,2);
        String codigoCiudadGrande = numero.substring(0,3);
        String codigoRestoCiudades = numero.substring(0,4);

        if (buenosAires.equals("11")) {
            log.warn("Buenos aires");
        } else if (esCiudadGrande(AppConstantes.CIUDADES_GRANDES, codigoCiudadGrande)){
            log.warn("Es ciudad grande.");
        } else if (esRestoCiudades(AppConstantes.RESTO_CIUDADES, codigoRestoCiudades)){
            log.warn("Es resto ciudades.");
        } else {
            throw new ContactoInvalidDataException("Código de área incorrecto.");
        }
    }

    public boolean esCiudadGrande(int[] prefijos, String codigoDeArea){
        return IntStream.of(prefijos).anyMatch(p -> p == Integer.parseInt(codigoDeArea));
    }

    public boolean esRestoCiudades(int[] prefijos, String codigoDeArea) {
        return IntStream.of(prefijos).anyMatch(p -> p == Integer.parseInt(codigoDeArea));
    }

    public void comprobarSiTieneLetraYGuardarNumero(ContactoDTO contactoDto, Contacto contacto) throws ContactoInvalidDataException {
        boolean esNumero = contactoDto.getTelefono().chars()
                .allMatch( Character::isDigit );

        contacto.setNombre(contactoDto.getNombre());

        if (!esNumero) {
            contacto.setTelefono(formatearNumero(contactoDto.getTelefono()));
        } else {
            contacto.setTelefono(contactoDto.getTelefono());
        }
    }

    public static String formatearNumero(String telefono) {
        StringBuilder numeroDecodificado = new StringBuilder();

        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                numeroDecodificado.append(decodificarLetras(String.valueOf(telefono.charAt(i))));
            } else {
                numeroDecodificado.append(telefono.charAt(i));
            }
        }

        return numeroDecodificado.toString();
    }

    public static String decodificarLetras(String letra) {//throws IllegalArgumentException
        switch (letra) {
            case "a":
            case "b":
            case "c":
                letra = "2";
                break;
            case "d":
            case "e":
            case "f":
                letra = "3";
                break;
            case "g":
            case "h":
            case "i":
                letra = "4";
                break;
            case "j":
            case "k":
            case "l":
                letra = "5";
                break;
            case "m":
            case "n":
            case "o":
                letra = "6";
                break;
            case "p":
            case "q":
            case "r":
            case "s":
                letra = "7";
                break;
            case "t":
            case "u":
            case "v":
                letra = "8";
                break;
            case "w":
            case "x":
            case "y":
            case "z":
                letra = "9";
                break;
            default:
                System.out.println("Caracter desconocido.");
                //throw new IllegalArgumentException("No permitido")
        }

        return letra;
    }
}

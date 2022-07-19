package com.agenda.api.entity;

import com.agenda.api.recursos.excepciones.ContactoInvalidDataException;
import com.agenda.api.recursos.utils.AppConstantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.util.stream.IntStream;

@ApiModel(description = "Clase que representa un contacto en la aplicación.")
@Entity
@Table(name = "contactos")
public class Contacto {

    @ApiModelProperty(notes = "Identificador único de Contacto.",
                    example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(notes = "Nombre de contacto.",
                    example = "Franco Demetrio", required = true)
    @Column(nullable = false)
    private String nombre;
    @ApiModelProperty(notes = "Número de teléfono de contacto.",
                    example = "1112345678", required = true)
    @Column(nullable = false)
    private String telefono;

    public Contacto() {}

    public Contacto(Long id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}

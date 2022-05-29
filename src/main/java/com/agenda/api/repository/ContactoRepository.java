package com.agenda.api.repository;

import com.agenda.api.dto.ContactoDTO;
import com.agenda.api.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    public List<Contacto> findByNombreContainsIgnoreCase(String name);
    public List<Contacto> findByNombreContainingIgnoreCase(String name);

    public List<Contacto> findByNombreIsContainingIgnoreCase(String name);
}

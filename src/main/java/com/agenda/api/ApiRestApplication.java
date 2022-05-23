package com.agenda.api;

import com.agenda.api.entity.Contacto;
import com.agenda.api.repository.ContactoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiRestApplication.class, args);
		ContactoRepository repository = context.getBean(ContactoRepository.class);

		Contacto contacto1 = new Contacto(1L, "Franco", "3471622079");
		Contacto contacto2 = new Contacto(2L, "Ara", "3471645791");

		repository.save(contacto1);
		repository.save(contacto2);

		System.out.println(contacto1.getId() + " " + contacto1.getNombre() + " " + contacto1.getTelefono());
	}

}

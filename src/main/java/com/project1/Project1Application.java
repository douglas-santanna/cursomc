package com.project1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project1.domain.Categoria;
import com.project1.repositories.CategoriaRepository;

@SpringBootApplication
public class Project1Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		repositorioCategoria.saveAll(Arrays.asList(cat1, cat2));
		
	}

}

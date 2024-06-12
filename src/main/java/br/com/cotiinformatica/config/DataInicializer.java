package br.com.cotiinformatica.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Category;
import br.com.cotiinformatica.repositories.CategoryRepository;

@Component
public class DataInicializer implements CommandLineRunner {

	@Autowired CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<String> names = Arrays.asList("Eletronics", "Books", "Sports", "Games", "Toys");
		
		names.stream().forEach(categoryName -> {
			if(!categoryRepository.existsByName(categoryName)) {
				Category category = new Category();
				category.setName(categoryName);
				categoryRepository.save(category);
			}
		});
	}

}

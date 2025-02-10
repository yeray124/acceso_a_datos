package com.example.demo;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Practica121314Application implements CommandLineRunner{

	@Autowired
	private final TransactionRepository transactionRepository;
	
	
    public Practica121314Application(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Practica121314Application.class, args);
		
	}

	@Override
    public void run(String... args) throws Exception {
        SimpleDateFormat darFormato = new SimpleDateFormat("dd-MM-yyyy");

        
        Long totalEntitiesInDatabase = transactionRepository.count();
        transactionRepository.deleteAll();
        
        transactionRepository.save(new Transaction("1", "Descripcion", 234.0, true, darFormato.parse("22-10-2004")));
        transactionRepository.save(new Transaction("2", "Descripcion2", 134.0, true, darFormato.parse("12-04-2014")));
        transactionRepository.save(new Transaction("3", "Descripcion3", 34.0, true, darFormato.parse("02-05-2010")));
        transactionRepository.save(new Transaction("4", "Descripcion4", 14.0, true, darFormato.parse("18-01-1999")));
        transactionRepository.save(new Transaction("5", "Descripcion5", 4.0, true, darFormato.parse("14-04-2000")));
        
        transactionRepository.findByIncomeFalse();
        transactionRepository.findByIncomeTrue();
        transactionRepository.findByFechaBetween( darFormato.parse("14-04-2024"), darFormato.parse("14-04-1999"));
        
        transactionRepository.findByDescripcionContaining("Venta");
        transactionRepository.findByQuantityGreaterThan(204.0);
        
        
    }
}
package com.example.demo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
	
	List <Transaction> findByIncomeTrue();
	List <Transaction> findByIncomeFalse();
	
	List<Transaction> findByFechaBetween(Date minFecha, Date maxFecha);
	
	List <Transaction> findByQuantityGreaterThan(int Quantity,int Quantity2);
	List <Transaction> findByQuantityBetween(int minQuantity, int maxQuantity);
	
	List<Transaction> findByFecha2Between(Date minFecha, Date maxFecha);
	
	List <Transaction> findByQuantityCantidadGreaterThan(Date minCantidad);
	List <Transaction> findByQuantityCantidadLessThan(Date minCantidad);
	
}
package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByIncomeTrue();
    List<Transaction> findByIncomeFalse();

    List<Transaction> findByFechaBetween(Date minFecha, Date maxFecha);

    List<Transaction> findByQuantityBetween(int minQuantity, int maxQuantity);

    List<Transaction> findByQuantityGreaterThan(double quantity);
    List<Transaction> findByQuantityLessThan(double quantity);
    List<Transaction> findByDescripcionContaining(String descripcion);
}

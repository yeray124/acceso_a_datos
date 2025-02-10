package com.example.demo;

import java.util.Date;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document public class Transaction {
	@Id
	private String id;
	private String descripcion;
	private double quantity;
	private boolean income;
	private Date fecha;
	
	public Transaction(String id, String descripcion, double quantity, boolean income, Date fecha) {
	    this.id = id;
	    this.descripcion = descripcion;
	    this.quantity = quantity;
	    this.income = income;
	    this.fecha = fecha;
	}

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getDescripcion() {
	    return descripcion;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public double getQuantity() {
	    return quantity;
	}

	public void setQuantity(double quantity) {
	    this.quantity = quantity;
	}

	public boolean isIncome() {  
	    return income;
	}

	public void setIncome(boolean income) {
	    this.income = income;
	}

	public Date getFecha() {
	    return fecha;
	}

	public void setFecha(Date fecha) {
	    this.fecha = fecha;
	}
}
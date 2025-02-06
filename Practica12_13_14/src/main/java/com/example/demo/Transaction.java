package com.example.demo;

import java.util.Date;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document public class Transaction {
	@Id
	private String Id;
	private String Descripcion;
	private double Quantity;
	private boolean Income;
	private Date Fecha;
	
	
	public Transaction(String Id, String Descripcion, double Quantity, boolean Income, Date Fecha) {
		this.Id = Id;
		this.Descripcion = Descripcion;
		this.Quantity = Quantity;
		this.Income = Income;
		this.Fecha = Fecha;

	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public double getQuantity() {
		return Quantity;
	}

	public void setQuantity(double quantity) {
		this.Quantity = quantity;
	}

	public boolean getIncome() {
		return Income;
	}

	public void setIncome(boolean income) {
		Income = income;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		this.Fecha = fecha;
	}
}
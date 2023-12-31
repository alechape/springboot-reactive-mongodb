package com.juaro.springreactivemongocrud.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "productos")
public class Producto {

	@Id
	private String id;
	private String nombre;
	private int cantidad;
	private double precio;
}

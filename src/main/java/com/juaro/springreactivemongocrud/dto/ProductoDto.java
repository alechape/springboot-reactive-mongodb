package com.juaro.springreactivemongocrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

	private String id;
	private String nombre;
	private int cantidad;
	private double precio;
	
}

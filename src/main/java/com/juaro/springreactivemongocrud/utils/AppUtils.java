package com.juaro.springreactivemongocrud.utils;

import org.springframework.beans.BeanUtils;

import com.juaro.springreactivemongocrud.dto.ProductoDto;
import com.juaro.springreactivemongocrud.entity.Producto;

public class AppUtils {

	public static ProductoDto entityToDto(Producto producto) {
		ProductoDto productoDto=new ProductoDto();
		BeanUtils.copyProperties(producto,productoDto);
		return productoDto;
	}
	
	public static Producto dtoToEntity(ProductoDto productoDto) {
		Producto producto=new Producto();
		BeanUtils.copyProperties(productoDto,producto);
		return producto;
	}
	
}

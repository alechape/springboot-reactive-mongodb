package com.juaro.springreactivemongocrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juaro.springreactivemongocrud.dto.ProductoDto;
import com.juaro.springreactivemongocrud.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@GetMapping
	public Flux<ProductoDto> getProductos(){
		return service.getProductos();
	}
	
	@GetMapping("/{id}")
	public Mono<ProductoDto> getProducto(@PathVariable String id){
		return service.getProducto(id);
	}
	
	@GetMapping("/producto-rango")
	public Flux<ProductoDto> getProductoBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max){
		return service.getProductInRange(min, max);
	}
	
	@PostMapping
	public Mono<ProductoDto> saveProducto(@RequestBody Mono<ProductoDto> productoDtoMono){
		return service.saveProducto(productoDtoMono);
	}
	
	@PutMapping("/update/{id}")
	public Mono<ProductoDto> updateProducto(@RequestBody Mono<ProductoDto> productoDto, @PathVariable String id){
		return service.updateProducto(productoDto, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteProducto(@PathVariable String id){
		return service.deleteProducto(id);
	}
	
}

package com.juaro.springreactivemongocrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.juaro.springreactivemongocrud.dto.ProductoDto;
import com.juaro.springreactivemongocrud.repository.ProductoRepository;
import com.juaro.springreactivemongocrud.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repository;
	
	public Flux<ProductoDto> getProductos(){
		return repository.findAll().map(AppUtils::entityToDto);
	}
	
	public Mono<ProductoDto> getProducto(String id){
		return repository.findById(id).map(AppUtils::entityToDto);
	}
	
	public Flux<ProductoDto> getProductInRange(double min, double max){ 
		return repository.findByPrecioBetween(Range.closed(min,max));
	}

	public Mono<ProductoDto> saveProducto(Mono<ProductoDto> productoDtoMono){
		return productoDtoMono.map(AppUtils::dtoToEntity)
			.flatMap(repository::insert)
			.map(AppUtils::entityToDto);
			
	}
	
	public Mono<ProductoDto> updateProducto(Mono<ProductoDto> productoDtoMono, String id){
		return repository.findById(id)
			.flatMap(p->productoDtoMono.map(AppUtils::dtoToEntity)
						.doOnNext(e -> e.setId(id)))
			.flatMap(repository::save)
			.map(AppUtils::entityToDto);
		
	}
	
	public Mono<Void> deleteProducto(String id){
		return repository.deleteById(id);
	}
	
}

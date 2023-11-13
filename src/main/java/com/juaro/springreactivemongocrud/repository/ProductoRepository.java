package com.juaro.springreactivemongocrud.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.juaro.springreactivemongocrud.dto.ProductoDto;
import com.juaro.springreactivemongocrud.entity.Producto;

import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends ReactiveMongoRepository<Producto,String>{

	Flux<ProductoDto> findByPrecioBetween(Range<Double> priceRange);
	
}

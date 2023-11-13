package com.juaro.springreactivemongocrud;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.juaro.springreactivemongocrud.controller.ProductoController;
import com.juaro.springreactivemongocrud.dto.ProductoDto;
import com.juaro.springreactivemongocrud.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductoController.class)
class SpringReactiveMongoCrudApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ProductoService service;
	
	@Test
	public void addProductoTest() {
		Mono<ProductoDto> productoDtoMono = Mono.just(new ProductoDto("102","Cámara web LOGITECH", 10, 280));
		when(service.saveProducto(productoDtoMono)).thenReturn(productoDtoMono);
		
		webTestClient.post().uri("/productos")
				.body(Mono.just(productoDtoMono), ProductoDto.class)
				.exchange()
				.expectStatus().isOk(); //200
		
	}
	
	@Test
	public void getProductsTest() {
		Flux<ProductoDto> productoDtoFlux = Flux.just(new ProductoDto("102","Cámara web LOGITECH", 10, 280),
				new ProductoDto("103","Cámara web SAMSUNG", 11, 290));
		
		when(service.getProductos()).thenReturn(productoDtoFlux);
		
		Flux<ProductoDto> responseBody = webTestClient.get().uri("/productos")
			.exchange()
			.expectStatus().isOk()
			.returnResult(ProductoDto.class)
			.getResponseBody();
		
		
		StepVerifier.create(responseBody)
			.expectSubscription()
			.expectNext(new ProductoDto("102","Cámara web LOGITECH", 10, 280))
			.expectNext(new ProductoDto("103","Cámara web SAMSUNG", 11, 290))
			.verifyComplete();
		
	}
	
	@Test
	public void getProductoTest() {
		Mono<ProductoDto> productoDtoMono = Mono.just(new ProductoDto("102","Cámara web LOGITECH", 10, 280));
		when(service.getProducto(any())).thenReturn(productoDtoMono);

		Flux<ProductoDto> responseBody = webTestClient.get().uri("/productos/102")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductoDto.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectSubscription()
		.expectNextMatches(p -> p.getNombre().equals("Cámara web LOGITECH"))
		.verifyComplete();
		
	}
	
	@Test
	public void updateProductoTest() {
		Mono<ProductoDto> productoDtoMono = Mono.just(new ProductoDto("102","Cámara web LOGITECH", 9, 275));
		when(service.updateProducto(productoDtoMono, "102")).thenReturn(productoDtoMono);
		
		webTestClient.put().uri("/productos/update/102")
			.body(Mono.just(productoDtoMono), ProductoDto.class)
			.exchange()
			.expectStatus().isOk(); //200
	}

	@Test
	public void deleteProductTest() {
		given(service.deleteProducto(any())).willReturn(Mono.empty());
		
		webTestClient.delete().uri("/productos/delete/102")
			.exchange()
			.expectStatus().isOk(); //200
	}
	
}

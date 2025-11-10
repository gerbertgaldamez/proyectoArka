package com.enyoi.arkana.producto.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enyoi.arkana.producto.producto")
public class ProductoApplication {

	public static void main(String[] args) {

        SpringApplication.run(ProductoApplication.class, args);
	}

}

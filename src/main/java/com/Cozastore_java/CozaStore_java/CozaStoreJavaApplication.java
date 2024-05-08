package com.Cozastore_java.CozaStore_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CozaStoreJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CozaStoreJavaApplication.class, args);
	}

}

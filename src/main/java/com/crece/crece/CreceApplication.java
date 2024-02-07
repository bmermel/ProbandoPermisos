package com.crece.crece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.*;

@SpringBootApplication
@EnableAsync

public class CreceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreceApplication.class, args);


	}
}





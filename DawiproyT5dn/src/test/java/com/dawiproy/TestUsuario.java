package com.dawiproy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class TestUsuario {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void contextLoads() {
		System.out.println(encoder.encode("contabilidad"));
	}

}

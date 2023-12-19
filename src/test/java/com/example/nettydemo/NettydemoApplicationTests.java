package com.example.nettydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettydemoApplicationTests {

	@Test
	void contextLoads() {

		boolean b1=false;
		boolean b2=false;

		boolean res = b1 & b2;
		System.out.println(res);


	}




}

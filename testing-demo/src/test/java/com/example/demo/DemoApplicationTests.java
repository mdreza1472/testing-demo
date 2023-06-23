package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;

@TestInstance(Lifecycle.PER_METHOD)
class DemoApplicationTests {
	
	Calculator underTest = null;
	
	@BeforeAll
	static void beforeAll(TestReporter testReporter) {
		System.out.println("before all executed");
		testReporter.publishEntry("starting the overall testing");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("after all executed");
	}
	
	@BeforeEach
	void init() {
		underTest = new Calculator();
	}

	@Test
	@Disabled
	void itShouldAddNumbers() {
		// given
		int numberOne = 10;
		int numberTwo = 20;
		
		// when
		int result = underTest.add(numberOne, numberTwo);
		
		// then
		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}
	
	@Test
	@DisplayName("testing with zero")
	@Tag("sample-tag")
	void testForDivideByZero(TestInfo testInfo, TestReporter testReporter) {
		// given
		int numberOne = 10;
		int numberTwo = 0;
		
		System.out.println("tags : " + testInfo.getTags());
		System.out.println("test display name: "+ testInfo.getDisplayName());
		testReporter.publishEntry("starting the test");
				
		// when and then
		assertThrows(
				ArithmeticException.class,
				() -> underTest.divide(numberOne, numberTwo),
				"Divide by zero should throw an exception");
	}
	
	@Test
	@Disabled
	void testServerConnection() {
		//call a heart beat method to check if the server is up
		boolean isServerUp = true;//call a heart beat method here
		
		assumeTrue(isServerUp);
		
		//continue your assert statements here for testing		
		
	}
	
	class Calculator{
		int add(int a,int b) {
			return a+b;
		}
		
		int divide(int a, int b) {
			return a/b;
		}
	}

}

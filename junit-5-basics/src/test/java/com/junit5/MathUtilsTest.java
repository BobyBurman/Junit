 package com.junit5;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD) //PER_CLASS--->NOT NEED TO WRITE STATIC KEYWORD.
class MathUtilsTest {
	
	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter restReporter;
	
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before All");
	}
	 
	@AfterAll
	static void afterAll() {
		System.out.println("After all");
	}
	  
	@BeforeEach
	void init(TestInfo testInfo,TestReporter testReporter) {
		this.testInfo=testInfo;
		this.restReporter=testReporter;
		
		mathUtils=new MathUtils();
		System.out.println("Before Each");
		restReporter.publishEntry("Running  "+ testInfo.getDisplayName()+ "  with tags"+ testInfo.getTags());
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After Each");
	}
	
	@Nested
	@Tag("math")
	class AddMethod{
		@Test
		@DisplayName("POSITIVE METHOD")
		void testAddPositive() {
			
			int expected=2;
			int actual=mathUtils.add(1,1);
			assertEquals(expected, actual,"The add method should add two numbers");
		}
		
		@Test
		@DisplayName("NEGATIVE METHOD")
		@RepeatedTest(3)
		void testAddNegative() {
			
			int expected=-2;
			int actual=mathUtils.add(-12,10);
			assertEquals(expected, actual,"The add method should add two numbers");
		}
		
		
	}
	
	@Tag("math")
	@Test
	@DisplayName("ADDTION METHOD")
	void testAdd() {
		
		int expected=2;
		int actual=mathUtils.add(1,1);
		assertEquals(expected, actual,"The add method should add two numbers");
	}
	
	@Tag("math")
	@Test
	@DisplayName("Multiply")
	//@Disabled
	//@EnabledOnOs(OS.LINUX)
	@EnabledOnOs(OS.WINDOWS)
	void testMultiply() {
		
		//System.out.println("Running  "+ testInfo.getDisplayName()+ "  with tags"+ testInfo.getTags());
		//restReporter.publishEntry("Running  "+ testInfo.getDisplayName()+ "  with tags"+ testInfo.getTags());
		assertAll(
				()-> assertEquals(4,mathUtils.multiply(2, 2)),
				()-> assertEquals(0,mathUtils.multiply(2, 0)),
				()-> assertEquals(-2,mathUtils.multiply(2, -1))
				);
		
	}
	
	
	
	
	@Tag("math")
	@Test
	@DisplayName("Division Method")
	@Disabled
	void testDivision() {
		try {
			
			assertEquals(1,mathUtils.division(1,1),"Divide result not maching");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dive by zero");
		}
	}
	
	@Tag("circle")
	@Test
	@DisplayName("AREA CALCULATING METHOD")
	void testArea() {
		assertEquals(314.1592653589793, mathUtils.areaOfCircle(10),"sould return right circle area");
		
	}
	
	
	
	@Test
	@DisplayName("ALWAYS DISABLED METHOD")
	@Disabled
	void testDisabled() {
		fail("This test should be disabled");
	}
	
	
	@Tag("math")
	@Test 
	@DisplayName("Division using lamda")
	@RepeatedTest(2)
	void testDivide() {
	 assertThrows(ArithmeticException.class,()->mathUtils.division(1,0),"Division by zero throws exceptions"); 
	}
	 
}

package com.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class BookServiceTest {
	
	@Test
	public void dummyTest() {
		
		BookRepository bookRepository=new BookRepositoryTest();
		EmailRepository emailRepository=new EmailRepositoryTest();
		BookService bookService=new BookService(bookRepository,emailRepository);
		
		bookService.addBook(new Book("1234","Mockito in action",250,LocalDate.now()));
		bookService.addBook(new Book("1235","Junit5 in action",200,LocalDate.now()));
		
		assertEquals(2,bookService.findNumberOfBooks(),"Do not match");
		
	}
}

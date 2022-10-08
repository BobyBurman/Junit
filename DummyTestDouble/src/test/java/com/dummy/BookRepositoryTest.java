package com.dummy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class BookRepositoryTest implements BookRepository{

	//In memory database(map,list etc.)
	
		Map<String, Book> bookStore=new HashMap<String, Book>();
		
		@Override
		public void save(Book book) {
			
			bookStore.put(book.getBookId(), book);
		}

		@Override
		public Collection<Book> findAll() {
			
			return bookStore.values();
		}
		


}

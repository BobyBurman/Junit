package com.dummy;



public class BookService {
	
	private BookRepository bookRepository;
	private EmailRepository emailRepository;


	
	public BookService(BookRepository bookRepository, EmailRepository emailRepository) {
		super();
		this.bookRepository = bookRepository;
		this.emailRepository = emailRepository;
	}


	public void addBook(Book book) {
		bookRepository.save(book); 
	}
	
	public int findNumberOfBooks() {
		return bookRepository.findAll().size();
	}
	
	//methods that are using email service
	
	 public void DoSendEmail(String message) { 
		 emailRepository.sendEmail(message);
	 }
	 
	
}
 
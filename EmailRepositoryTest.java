package com.dummy;

public class EmailRepositoryTest implements EmailRepository {

	

	@Override
	public void sendEmail(String message) {
		throw new AssertionError("Method not implemented !!!");
	}
	
}

package com.chainsys.jdbc;

public class BookValidator {
	public void bookValidator(Book book) throws Exception {
		if (book.name == null || book.name.equalsIgnoreCase("null")
				|| book.name.isEmpty()) {
			throw new Exception("Invalid Name");
		}
		if (book.price <= 0) {
			throw new Exception("Invalid Price!Price cannot be less than 0");
		}
	}
}

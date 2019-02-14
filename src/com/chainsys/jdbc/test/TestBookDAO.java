package com.chainsys.jdbc.test;

import java.util.Scanner;

import com.chainsys.jdbc.Book;
import com.chainsys.jdbc.BookDAO;
import com.chainsys.jdbc.BookValidator;

public class TestBookDAO {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		BookDAO bookDAO = new BookDAO();
		System.out.println("1.Add,2.Update,3.Delete,4.FindById,5.FindAll");
		int input = scanner.nextInt();
		Book book = new Book();
		switch (input) {
		case 1:
			System.out.println("Enter name,price to add");
			System.out.println("Enter Book Name");
			book.name = scanner.next();
			System.out.println("Enter Book Price");
			book.price = scanner.nextInt();
			BookValidator bookvalidator = new BookValidator();
			try {
				bookvalidator.bookValidator(book);
				bookDAO.addBook(book);
				bookDAO.findAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("Enter name,price and id to Update");
			System.out.println("Enter Book Name");
			book.name = scanner.next();
			System.out.println("Enter Book Price");
			book.price = scanner.nextInt();
			System.out.println("Enter Book id");
			book.id = scanner.nextInt();
			bookDAO.updateBook(book);
			System.out.println("Book Name and Price Update Sucessfully");
			break;
		case 3:
			System.out.println("Enter Book id to Delete");
			System.out.println("Enter Book id");
			int id2 = scanner.nextInt();
			bookDAO.deleteBook(id2);
			System.out.println("Book Deleted Sucessfully");
			break;
		case 4:
			System.out.println("Enter Book id to Find Book");
			System.out.println("Enter Book id");
			book.id = scanner.nextInt();
			Book books = bookDAO.findById(book);
			if (books != null) {
				System.out.println("Book Id " + books.id);
				System.out.println("Name " + books.name);
				System.out.println("Price " + books.price);
			} else {
				System.out.println("No Record");
			}
			break;
		case 5:
			System.out.println("View All Books");
			bookDAO.findAll();
			break;
		default:
			break;
		}

		scanner.close();
	}

}

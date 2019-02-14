package com.chainsys.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @throws Exception
	 *             pre condition id,name,price must be valid
	 */
	public void addBook(Book book) throws Exception {
		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "Insert into book(id,name,price) values(book_id_seq.NEXTVAL,?,?)";
			PreparedStatement preparedstatement = connection
					.prepareStatement(sql);
			preparedstatement.setString(1, book.name);
			preparedstatement.setInt(2, book.price);
			int row = preparedstatement.executeUpdate();
			System.out.println("Rows Affected :" + row);
			ConnectionUtil.close(connection, preparedstatement, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to insert book");
		}
	}

	/**
	 * Update Book
	 * 
	 * @param name
	 * @param price
	 * @param id
	 * @throws SQLException
	 */
	public void updateBook(Book book) throws SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE book set name=?,price=? where id=?";
		PreparedStatement preparedstatement = connection.prepareStatement(sql);
		preparedstatement.setString(1, book.name);
		preparedstatement.setInt(2, book.price);
		preparedstatement.setInt(3, book.id);
		int row = preparedstatement.executeUpdate();
		System.out.println("Update :" + row);
		ConnectionUtil.close(connection, preparedstatement, null);

	}

	/**
	 * Delete Book
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteBook(int id) throws SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String sqldelete = "Delete from book where id=?";
		PreparedStatement preparedstatement = connection
				.prepareStatement(sqldelete);
		preparedstatement.setInt(1, id);
		int row = preparedstatement.executeUpdate();
		System.out.println("Delete record sucessfully :" + row);
		ConnectionUtil.close(connection, preparedstatement, null);
	}

	public void findAll() throws SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String sqlselect = "select id,name,price from book";
		PreparedStatement preparedstatement = connection
				.prepareStatement(sqlselect);
		ResultSet resultset = preparedstatement.executeQuery();
		while (resultset.next()) {
			System.out.println("----");
			System.out.print("book id " + resultset.getInt("id"));
			System.out.print("book name " + resultset.getString("name"));
			System.out.print("Price " + resultset.getInt("price"));
		}
		ConnectionUtil.close(connection, preparedstatement, resultset);
	}

	public Book findById(Book book) throws SQLException {
		Book books = null;
		Connection connection = ConnectionUtil.getConnection();
		String sqlselect = "select id,name,price from book where id=?";
		PreparedStatement preparedstatement = connection
				.prepareStatement(sqlselect);
		preparedstatement.setInt(1, book.id);
		ResultSet resultset = preparedstatement.executeQuery();
		if (resultset.next()) {
			System.out.println("----");
			books = new Book();
			books.id = resultset.getInt("id");
			books.name = resultset.getString("name");
			books.price = resultset.getInt("price");
		}
		ConnectionUtil.close(connection, preparedstatement, resultset);
		return books;
	}
}

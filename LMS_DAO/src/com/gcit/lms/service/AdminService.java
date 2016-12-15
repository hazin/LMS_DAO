package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AdminService {
	
	public void addAuthor(Author author) throws SQLException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthor(author);
			BookDAO bdao = new BookDAO(conn);
			//insert bookAuthors table
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
	}
	
	public void addBook(Book book) throws SQLException{ 
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			Integer bookId = bdao.addBookWithID(book);
			for(Author a: book.getAuthors()){
				//bdao.addBookAuthors(book.getBookId(), a.getAuthorId());
			}
			//insert bookAuthors table
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
	}


}

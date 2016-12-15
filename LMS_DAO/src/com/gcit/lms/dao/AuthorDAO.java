package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;

public class AuthorDAO extends BaseDAO{

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws SQLException{
		save("insert into tbl_author (authorName) values (?)", new Object[]{author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws SQLException{
		save("update tbl_author set authorName = ? where authorId = ?", new Object[]{author.getAuthorName(), author.getAuthorId()});
	}
	
	
	public void deleteAuthor(Author author) throws SQLException{
		save("delete from tbl_author where authorId = ?", new Object[]{author.getAuthorId()});
	}
	
	public List<Author> readAllAuthors() throws SQLException{
		return readAll("select * from tbl_author", null);
	}

	@Override
	public List<Author> extractData(ResultSet rs) {
		List<Author> authors = new ArrayList<Author>();
		BookDAO bdao = new BookDAO(conn);
		try {
			while(rs.next()){
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				a.setBooks(bdao.readAllFirstLevel("select * from tbl_book where bookId IN (Select bookId from tbl_book_authors where authorId = ?)", new Object[] {a.getAuthorId()}));
				authors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return authors;
	}

	@Override
	public  List<Author> extractDataFirstLevel(ResultSet rs) {
		List<Author> authors = new ArrayList<Author>();
		try {
			while(rs.next()){
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				authors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return authors;
	}
}
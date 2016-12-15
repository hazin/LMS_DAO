package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO {
	public static Connection conn = null; 
	
	public BaseDAO(Connection conn){
		this.conn = conn;
	}
	
	public void save(String query, Object[] vals) throws SQLException{
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			if(vals!=null){
				int count=1;
				for(Object o: vals){
					pstmt.setObject(count, o);
					count++;
				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer saveWithID(String query, Object[] vals) throws SQLException{
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			if(vals!=null){
				int count=1;
				for(Object o: vals){
					pstmt.setObject(count, o);
					count++;
				}
			}
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			if(rs.next()){
				return rs.getInt(1);
			}else{
				return -1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(conn!=null)
				conn.rollback();
		} finally{
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return null;
	}
	
	public <T>List<T> readAll(String query, Object[] vals) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			if(vals!=null){
				int count=1;
				for(Object o: vals){
					pstmt.setObject(count, o);
					count++;
				}
			}
			ResultSet rs = pstmt.executeQuery();
			return (List<T>) extractData(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return null;
	}

	public abstract <T>List<T> extractData(ResultSet rs);
	
	public <T>List<T> readAllFirstLevel(String query, Object[] vals) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			if(vals!=null){
				int count=1;
				for(Object o: vals){
					pstmt.setObject(count, o);
					count++;
				}
			}
			ResultSet rs = pstmt.executeQuery();
			return (List<T>) extractData(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return null;
	}

	public abstract <T>List<T> extractDataFirstLevel(ResultSet rs);
}

package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO{
	
	public LibraryBranchDAO(Connection conn){
		super(conn);
	}
	
	@Override
	public <T> List<T> extractData(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}

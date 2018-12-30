package com.dvn.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.dvn.core.configuration.ConnectionDB;
import com.dvn.core.model.Account;
import com.dvn.core.util.Conversion;

public class AccountDAOPostgres implements AccountDAO{

	public List<Account> getAll() {
		String sql = "SELECT * FROM core.account;";
		List<Account> list = new ArrayList<Account>();
		
		Connection conn = ConnectionDB.getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account ac = new Account();
				
				ac.setId(rs.getLong("user_id"));
				ac.setName(rs.getString("name"));
				ac.setUsername(rs.getString("username"));
				ac.setPassword(rs.getString("password"));
				ac.setCreatedOn(Conversion.timestampToLocalDate(rs.getTimestamp("created_on")));
				ac.setLastLogin(Conversion.timestampToLocalDate(rs.getTimestamp("last_login")));
				
				list.add(ac);
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean set(Account ac) {
		String sql = "INSERT INTO core.account(username, name, password, created_on) VALUES (?, ?, ?, ?);";
		Connection conn = ConnectionDB.getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ac.getUsername());
			ps.setString(2, ac.getName());
			ps.setString(3, BCrypt.hashpw(ac.getPassword(), BCrypt.gensalt()));
			ps.setTimestamp(4, Conversion.localDateToTimestamp(ac.getCreatedOn()));
			
			ps.execute();
			ps.close();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public Account get(Long id) {
		String sql = "";
		Connection conn = ConnectionDB.getConn();
		Account ac = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ac = new Account();
				
				ac.setId(rs.getLong("user_id"));
				ac.setName(rs.getString("name"));
				ac.setUsername(rs.getString("username"));
				ac.setPassword(rs.getString("password"));
				ac.setCreatedOn(Conversion.timestampToLocalDate(rs.getTimestamp("created_on")));
				ac.setLastLogin(Conversion.timestampToLocalDate(rs.getTimestamp("last_login")));
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ac;
	}

	public boolean update(Account ac) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(Account ac) {
		// TODO Auto-generated method stub
		return false;
	}

}

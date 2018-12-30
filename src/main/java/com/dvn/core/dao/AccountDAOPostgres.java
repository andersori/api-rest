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
	
	private final static String TABLENAME = "core.account";
	
	private final static String COLUMNUSERID = "user_id";
	private final static String COLUMNNAME = "name";
	private final static String COLUMNUSERNAME = "username";
	private final static String COLUMNPASSWORD = "password";
	private final static String COLUMNCREATEDON = "created_on";
	private final static String COLUMNLASTLOGIN = "last_login";

	public List<Account> getAll() {
		String sql = String.format("SELECT * FROM %s;", TABLENAME);
		List<Account> list = new ArrayList<Account>();
		
		Connection conn = ConnectionDB.getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account ac = new Account();
				
				ac.setId(rs.getLong(COLUMNUSERID));
				ac.setName(rs.getString(COLUMNNAME));
				ac.setUsername(rs.getString(COLUMNUSERNAME));
				ac.setPassword(rs.getString(COLUMNPASSWORD));
				ac.setCreatedOn(Conversion.timestampToLocalDate(rs.getTimestamp(COLUMNCREATEDON)));
				ac.setLastLogin(Conversion.timestampToLocalDate(rs.getTimestamp(COLUMNLASTLOGIN)));
				
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
		String sql = String.format("INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?);", TABLENAME, COLUMNUSERNAME, COLUMNNAME, COLUMNPASSWORD, COLUMNCREATEDON);
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
		String sql = String.format("SELECT * FROM %s WHERE %s = ?;", TABLENAME, COLUMNUSERID);
		Connection conn = ConnectionDB.getConn();
		Account ac = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ac = new Account();
				
				ac.setId(rs.getLong(COLUMNUSERID));
				ac.setName(rs.getString(COLUMNNAME));
				ac.setUsername(rs.getString(COLUMNUSERNAME));
				ac.setPassword(rs.getString(COLUMNPASSWORD));
				ac.setCreatedOn(Conversion.timestampToLocalDate(rs.getTimestamp(COLUMNCREATEDON)));
				ac.setLastLogin(Conversion.timestampToLocalDate(rs.getTimestamp(COLUMNLASTLOGIN)));
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ac;
	}
	
	public Account get(String username) {
		String sql = String.format("SELECT * FROM %s WHERE %s = ?;", TABLENAME, COLUMNUSERNAME);
		Connection conn = ConnectionDB.getConn();
		Account ac = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ac = new Account();
				
				ac.setId(rs.getLong(COLUMNUSERID));
				ac.setName(rs.getString(COLUMNNAME));
				ac.setUsername(rs.getString(COLUMNUSERNAME));
				ac.setPassword(rs.getString(COLUMNPASSWORD));
				ac.setCreatedOn(Conversion.timestampToLocalDate(rs.getTimestamp(COLUMNCREATEDON)));
				ac.setLastLogin(Conversion.timestampToLocalDate(rs.getTimestamp(COLUMNLASTLOGIN)));
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ac;
	}

	public boolean update(Account ac) {
		String sql = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?;", TABLENAME, COLUMNUSERNAME, COLUMNNAME, COLUMNPASSWORD, COLUMNLASTLOGIN, COLUMNUSERID);
		Connection conn = ConnectionDB.getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ac.getUsername());
			ps.setString(2, ac.getName());
			ps.setString(3, ac.getPassword());
			ps.setTimestamp(4, Conversion.localDateToTimestamp(ac.getLastLogin()));
			ps.setLong(5, ac.getId());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean remove(Long id) {
		String sql = String.format("DELETE FROM %s WHERE %s=?;", TABLENAME, COLUMNUSERID);
		Connection conn = ConnectionDB.getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}

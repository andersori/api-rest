package com.dvn.core.dao;

import java.util.List;

import com.dvn.core.model.Account;

public interface AccountDAO {
	
	public List<Account> getAll();
	public boolean set(Account ac);
	public Account get(Long id);
	public boolean update(Account ac);
	public boolean remove(Account ac);
	
}

package com.dvn.core.test;

import java.util.List;

import com.dvn.core.configuration.ConnectionDB;
import com.dvn.core.dao.AccountDAOPostgres;
import com.dvn.core.model.Account;

public class CoreTest {
	public static void main(String[] args) {

		AccountDAOPostgres acp = new AccountDAOPostgres();
		
		/*
		Account ac = new Account();
		ac.setName("Anderson Soriano de Araújo");
		ac.setUsername("andersori");
		ac.setPassword("1234");
		acp.set(ac);
		*/
		
		List<Account> list = acp.getAll();
		
		for(Account a : list) {
			System.out.println(a.getName());
		}
		
		ConnectionDB.closeConn();
	}
}

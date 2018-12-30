package com.dvn.core.test;

import java.util.List;

import com.dvn.core.configuration.ConnectionDB;
import com.dvn.core.dao.AccountDAO;
import com.dvn.core.dao.AccountDAOPostgres;
import com.dvn.core.model.Account;

public class AccountDAOTest {
	public static void main(String[] args) {

		AccountDAO acp = new AccountDAOPostgres();
		
		//criar(acp);
		listar(acp);
		//pegar(acp);
		//atualizar(acp);
		//deletar(acp);
		
		ConnectionDB.closeConn();
	}
	
	public static void criar(AccountDAO acp) {
		Account ac = new Account();
		ac.setName("Anderson Soriano de Araújo");
		ac.setUsername("andersori");
		ac.setPassword("1234");
		acp.set(ac);
	}
	
	public static void listar(AccountDAO acp) {
		List<Account> list = acp.getAll();
		
		for(Account a : list) {
			System.out.println(a.getName());
		}
	}
	
	public static void pegar(AccountDAO acp) {
		Account ac = acp.get(1L);
		System.out.println(ac.getName());
	}
	
	public static void atualizar(AccountDAO acp) {
		Account ac = acp.get(1L);
		ac.setUsername("andersori7");
		
		acp.update(ac);
		
		ac = acp.get(1L);
		System.out.println(ac.getUsername());
	}

	public static void deletar(AccountDAO acp) {
		acp.remove(1L);
		Account ac = acp.get(1L);
		System.out.println(ac.getUsername());
	}
}

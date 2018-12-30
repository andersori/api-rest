package com.dvn.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvn.core.dao.AccountDAO;
import com.dvn.core.dao.AccountDAOPostgres;
import com.dvn.core.model.Account;

@RestController
@RequestMapping(value="/account")
public class AccountResource {
	
	private AccountDAO acp;
	
	public AccountResource() {
		this.acp = new AccountDAOPostgres();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Account> > getAll(){
		List<Account> res = this.acp.getAll();
		
		if(res.isEmpty()) {
			return new ResponseEntity<List<Account>>(new ArrayList<Account>(), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Account>>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		Account ac = this.acp.get(id);
		
		if(ac == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Account>(ac, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable(name="id") Long id){		
		if(this.acp.remove(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Account ac) {
		if(ac != null) {
			if(this.acp.set(ac)) {
				return new ResponseEntity<Account>(acp.get(ac.getUsername()), HttpStatus.CREATED);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Account ac){
		if(ac != null) {
			if(this.acp.update(ac)) {
				return new ResponseEntity<Account>(acp.get(id), HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
}

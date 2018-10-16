package com.laksh.Bean;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private int accountNumber;
	private String name;
	private String username;
	private String password;
	
	private List<Transaction> transactions = new ArrayList<Transaction>(0);
	 
	private Balance balance;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int accountNumber, String name, String username, String password) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

}

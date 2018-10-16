package com.laksh.Bean;

public class Balance {

	private int id;
	
	private String balance;
	
	private Account account;

	public Balance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Balance(int id, String balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}

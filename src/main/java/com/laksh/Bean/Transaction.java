package com.laksh.Bean;

public class Transaction {

	private int amount;
	
	private int account_number;
	
	private String type;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int account_number, int amount, String type) {
		super();
		this.amount = amount;
		this.type = type;
	    this.account_number = account_number;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}

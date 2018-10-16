package com.laksh.Model;

public class TransferRequest {

	private int fromAccount;
	private int toAccount;
	private int amount;
	
	
	public TransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransferRequest(int fromAccount, int toAccount, int amount) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}


	public int getFromAccount() {
		return fromAccount;
	}


	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}


	public int getToAccount() {
		return toAccount;
	}


	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}

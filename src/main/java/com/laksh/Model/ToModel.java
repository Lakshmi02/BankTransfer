package com.laksh.Model;

public class ToModel {

	private int id;
	private int balance;
	
	public ToModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToModel(int id, int balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public int getbalance() {
		return balance;
	}
	public void setbalance(int balance) {
		this.balance = balance;
	}
	
	
}

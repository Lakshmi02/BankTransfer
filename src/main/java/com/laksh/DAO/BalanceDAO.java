package com.laksh.DAO;

import com.laksh.Exception.TransferException;

public interface BalanceDAO {

	public int getBalance(int id) throws TransferException;
	
	public int withdraw(int id, int val) throws TransferException;
	
	public int deposit(int id, int val) throws TransferException;
}

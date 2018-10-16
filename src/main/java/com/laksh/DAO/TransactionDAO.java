package com.laksh.DAO;

import com.laksh.Bean.Transaction;
import com.laksh.Exception.TransferException;

public interface TransactionDAO {
	
	public int createTransaction(Transaction tran, int id) throws TransferException;
}

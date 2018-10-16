package com.laksh.DAO;

import com.laksh.Exception.TransferException;

public interface AccountDAO {

	public void checkAccountExists(int id) throws TransferException;

}

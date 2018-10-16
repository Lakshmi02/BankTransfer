package com.laksh.DAO;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.laksh.Exception.TransferException;


public class BalanceDAOImpl implements BalanceDAO {
	
	private DataSource dataSource;

	private Logger LOGGER = Logger.getLogger("BalanceDAOImpl.java");
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int getBalance(int id) throws TransferException {
		try {
		LOGGER.info("Inside BalanceDAO getBalance method");
		String query = "select amount from balance where account_number = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String val = jdbcTemplate.queryForObject(query, new Object[] {id}, String.class);
		return Integer.parseInt(String.valueOf(val));
		}
		catch(Exception e)
		{
			LOGGER.error(e);
			LOGGER.info("Throwing Custom Exception with message: Some exception Occured while fetching the balance so rolledback the Transaction");
			throw new TransferException("Some exception Occured while fetching the balance so rolledback the Transaction");
		}
	}

	private void updateBalance(int id, int val) throws TransferException {
		try {
		LOGGER.info("Inside BalanceDao updateBalance method");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "update balance set amount = ? where account_number = ?";
		jdbcTemplate.update(query, new Object[] {val, id});
		}
	    catch(Exception e)
	    {
	    	LOGGER.error(e);
	    	LOGGER.info("Throwing Custom Exception with message:Some exception Occured while updating balance so rolledback the Transaction");
		   throw new TransferException("Some exception Occured while updating balance so rolledback the Transaction");
	    }
	}
	
	@Override
	public synchronized int withdraw(int id, int val) throws TransferException {
		int newBalance;
		LOGGER.info("Inside BalanceDao updateBalance method");
			int balance = getBalance(id);
			newBalance = balance - val;
			updateBalance(id, newBalance);
		    return newBalance;
	}
	
	@Override
	public synchronized int deposit(int id, int val) throws TransferException {
		int newBalance;
		LOGGER.info("Inside BalanceDao deposit method");
			int balance = getBalance(id);
			newBalance = balance + val;
			updateBalance(id, newBalance);
		    return newBalance;
	}
}

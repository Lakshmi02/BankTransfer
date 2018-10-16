package com.laksh.DAO;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.laksh.Controller.TransferController;
import com.laksh.Exception.TransferException;


public class AccountDAOImpl implements AccountDAO {

	private Logger LOGGER = Logger.getLogger(AccountDAOImpl.class);
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void checkAccountExists(int id) throws TransferException {
		String query = "select name from account where account.account_number=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				try {
					jdbcTemplate.queryForObject(query, new Object[] { id }, String.class);
				} catch (EmptyResultDataAccessException e) {
					LOGGER.error(e);
					LOGGER.info("Throwing Custom Exception with message: The Account with id:" + id + " does not exist");
					throw new TransferException("The Account with id: " + id + " does not exist");
				}
	}

}

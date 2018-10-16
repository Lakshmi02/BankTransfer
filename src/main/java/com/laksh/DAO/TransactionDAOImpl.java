package com.laksh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.laksh.Bean.Transaction;
import com.laksh.Exception.TransferException;


public class TransactionDAOImpl implements TransactionDAO {

	private Logger LOGGER = Logger.getLogger("TransactionDAOImpl.java");
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Autowired
	AccountDAO accountDao;
	
	@Override
	public int createTransaction(Transaction tran, int id) throws TransferException {
		
		LOGGER.info("Inside TransactionDao createTransaction method");
		
		  KeyHolder keyHolder=new GeneratedKeyHolder();
		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	      try {
		  PreparedStatementCreator psc = new PreparedStatementCreator(){
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				    	PreparedStatement ps=connection.prepareStatement("insert into transaction (amount, account_number, description) values (?,?,?)",
				    		  Statement.RETURN_GENERATED_KEYS);
				      int index=1;
				      ps.setInt(index++,tran.getAmount());
				      ps.setInt(index++, id);
				      ps.setString(index++,tran.getType());
				      return ps;
				    }
				  };
		  
        	  jdbcTemplate.update(psc,keyHolder);
			  return keyHolder.getKey().intValue();
		  }
          catch (Exception e) {
        	  LOGGER.error(e);
        	  LOGGER.info("Thrwoing Custom Exception with message:Some Exception while updating the transaction Object so rolledBack.");
			  throw new TransferException("Some Exception while updating the transaction Object so rolledBack."); 
		  }
	}

}

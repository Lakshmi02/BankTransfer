package com.laksh.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laksh.Bean.Transaction;
import com.laksh.Controller.TransferController;
import com.laksh.DAO.AccountDAO;
import com.laksh.DAO.BalanceDAO;
import com.laksh.DAO.TransactionDAO;
import com.laksh.Exception.TransferException;
import com.laksh.Model.FromModel;
import com.laksh.Model.ToModel;
import com.laksh.Model.TransferRequest;
import com.laksh.Model.TransferResponse;

@Service
public class TransferServiceImpl implements TransferService {

	private Logger LOGGER = Logger.getLogger(TransferServiceImpl.class);
	@Autowired
	TransactionDAO transactionDao;
	
	@Autowired
	BalanceDAO balanceDao;

	@Autowired
	AccountDAO accountDao;
	
	/*
	 * (non-Javadoc)
	 * @request: Validate the Request object has valid ID's and the Source Account has sufficient
	 * balance.
	 */
	@Override
	@Transactional
	public void verify (TransferRequest request) throws TransferException
	{
    
		LOGGER.info("Inside TransferService class");
		
		int fromId = request.getFromAccount();
		LOGGER.info("fromId : " + fromId);
		int toId = request.getToAccount();
		LOGGER.info("toId : " + toId);
		int amount = request.getAmount();
		LOGGER.info("Amount : " + amount);
		
		accountDao.checkAccountExists(fromId);
		accountDao.checkAccountExists(toId);
		
		int fromBalance = balanceDao.getBalance(fromId);
		LOGGER.info("fromBalance : " + fromBalance);	
	
		if (fromBalance <= amount) {
			throw new TransferException("The amount to be transferred exceeds the Source Account Balance");
		}

		int toBalance = balanceDao.getBalance(toId);
		LOGGER.info("toBalance:" + toBalance);

	}
	
	/*
	 * (non-Javadoc)
	 * Update the transaction table and the Balance of respective accounts in the Balance Table.
	 * Construct and Return the Response Payload after updating the DB
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public TransferResponse transfer(TransferRequest request) throws TransferException{
		
		Transaction fromTran = new Transaction(request.getAmount(), request.getFromAccount(), "Debited");
		int res1 = transactionDao.createTransaction(fromTran, request.getFromAccount());
		Transaction toTran = new Transaction(request.getAmount(), request.getToAccount(), "Credited");
		transactionDao.createTransaction(toTran, request.getToAccount());

		int fromNewBalance = balanceDao.withdraw(request.getFromAccount(), request.getAmount());
		int toNewBalance = balanceDao.deposit(request.getToAccount(), request.getAmount());
		
		TransferResponse response = new TransferResponse();
		FromModel from = new FromModel(request.getFromAccount(),fromNewBalance);
		ToModel to = new ToModel(request.getFromAccount(),toNewBalance);
		response.setTransactionId(res1);
		response.setFromModel(from);
		response.setToModel(to);
		response.setTransfered(request.getAmount());
		
		return response;
	}
}

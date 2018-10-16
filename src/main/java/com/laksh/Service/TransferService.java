package com.laksh.Service;

import com.laksh.Exception.TransferException;
import com.laksh.Model.TransferRequest;
import com.laksh.Model.TransferResponse;

public interface TransferService {

	public TransferResponse transfer(TransferRequest request) throws TransferException;
	
	public void verify (TransferRequest request) throws TransferException;
}

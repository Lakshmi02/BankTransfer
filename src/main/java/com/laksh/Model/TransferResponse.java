package com.laksh.Model;

public class TransferResponse {

    private int transactionId;
	
	private FromModel from;
	
	private ToModel to;
	
	private int transfered;

	public TransferResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public FromModel getFrom() {
		return from;
	}

	public void setFromModel(FromModel from) {
		this.from = from;
	}

	public ToModel getTo() {
		return to;
	}

	public void setToModel(ToModel to) {
		this.to = to;
	}

	public int gettransfered() {
		return transfered;
	}

	public void setTransfered(int transfered) {
		this.transfered = transfered;
	}
	
	
}

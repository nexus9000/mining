package edu.itstep.blockchain.mining;

public class TransactionInput {
	
	private String transactionOutputId;
	// unspent transaction output
	private TransactionOutput UTXO;

	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;

	}

	public String getTransactionOutputId() {
		return transactionOutputId;
	}

	public void setTransactionOutputId(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}

	public TransactionOutput getUTXO() {
		return UTXO;
	}

	public void setUTXO(TransactionOutput uTXO) {
		UTXO = uTXO;
	}

}

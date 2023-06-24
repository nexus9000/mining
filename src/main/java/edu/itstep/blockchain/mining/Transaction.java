package edu.itstep.blockchain.mining;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Transaction {
	//id of the transaction
   private String transactionId;
   private PublicKey  sender;
   private PublicKey receiver;
   private double amount;
   //transaction must be signed
   private byte[] signature;
   public List<TransactionInput> inputs;
   public List<TransactionOutput> outputs;
   
   public Transaction(PublicKey sender, PublicKey receiver, 
		   double amount, List<TransactionInput> inputs) {
	   this.inputs = new ArrayList<>();
	   this.outputs = new ArrayList<>();
	   this.sender = sender;
	   this.receiver = receiver;
	   this.inputs = inputs;
	   this.amount = amount;
	   calculateHash();
   }
   
   public void generateSignature(PrivateKey privateKey) {
	   String data = sender.toString() +
			   receiver.toString() + Double.toString(amount);
	   signature = CryptographyHelper.sign(privateKey, data);
   }
   public boolean verifySignature() {
	   String data = sender.toString() +
			   receiver.toString() + Double.toString(amount);
	   return CryptographyHelper.verify(sender, data, signature);
   }
   
   private final void calculateHash() {
	   String hashData = sender.toString() +
			   receiver.toString() + Double.toString(amount);
	   this.transactionId = CryptographyHelper.generateHash(hashData);
   }
   

   public boolean verifyTransaction() {
      
      if(!verifySignature()) {
         System.out.println("Invalid transaction because of invalid signature...");
         return false;
      }
      
      // let's gather unspent transactions (we have to consider the inputs)
      for(TransactionInput transactionInput : inputs)
         transactionInput.setUTXO(Blockchain.UTXOs.get(transactionInput.getTransactionOutputId()));
      
      //transactions have 2 part: send an amount to the receiver + send the (balance-amount)
      // back to the sender
      //send value to recipient
      outputs.add(new TransactionOutput( this.receiver, amount, transactionId));
      //send the left over 'change' back to sender      
      outputs.add(new TransactionOutput( this.sender, getInputsSum() - amount, transactionId)); 
         
      // WE HAVE TO UPDATE THE UTXOs
      //the outputs will be inputs for other transactions (so put them in the blockchain's UTXOs)
      for(TransactionOutput transactionOutput : outputs)
         Blockchain.UTXOs.put(transactionOutput.getId() , transactionOutput);
         
      // remove transaction inputs from blockchain's UTXOs list because they've been spent
      for(TransactionInput transactionInput : inputs) 
         if(transactionInput.getUTXO() != null)   
            Blockchain.UTXOs.remove(transactionInput.getUTXO().getId());
      
      return true;
   }

   // this is how we calculate that how much money the sender has
   // we have to consider transactions in the past
   private double getInputsSum() {
      double sum = 0;
      
      for(TransactionInput transactionInput : inputs)
         if(transactionInput.getUTXO() != null) 
            sum += transactionInput.getUTXO().getAmount();
      
      return sum;
   }

public String getTransactionId() {
	return transactionId;
}

public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}

public PublicKey getSender() {
	return sender;
}

public void setSender(PublicKey sender) {
	this.sender = sender;
}

public PublicKey getReceiver() {
	return receiver;
}

public void setReceiver(PublicKey receiver) {
	this.receiver = receiver;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public byte[] getSignature() {
	return signature;
}

public void setSignature(byte[] signature) {
	this.signature = signature;
}

public List<TransactionInput> getInputs() {
	return inputs;
}

public void setInputs(List<TransactionInput> inputs) {
	this.inputs = inputs;
}

public List<TransactionOutput> getOutputs() {
	return outputs;
}

public void setOutputs(List<TransactionOutput> outputs) {
	this.outputs = outputs;
}

@Override
public String toString() {
	return "Transaction [transactionId=" + transactionId + ", sender=" + sender + ", receiver=" + receiver + ", amount="
			+ amount + ", signature=" + Arrays.toString(signature) + "]";
}
   
}

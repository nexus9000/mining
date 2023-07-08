package edu.itstep.blockchain.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.itstep.blockchain.mining.Block;
import edu.itstep.blockchain.mining.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="blocks")
@Getter@Setter@NoArgsConstructor
public class BlockPersistent {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
	   private int nonce;
	   
	   @Column(name="time_stamp")
	   private long timeStamp;
	   
	   private String hash;
	   
	   @Column(name="previous_hash")
	   private String previousHash;
	   @Column(name="is_block_genesis")
	   private boolean isBlockGenesis;
	   //public List<Transaction> transactions;
	   public BlockPersistent(Block block ) {
		      //this.transactions = new ArrayList<>();
		      this.previousHash = block.getPreviousHash();
		      this.hash = block.getHash();
		      this.nonce = block.getNonce();
		      this.timeStamp = block.getTimeStamp();
		      this.isBlockGenesis = true; 
		   }
}

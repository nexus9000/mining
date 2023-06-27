package edu.itstep.blockchain;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.itstep.blockchain.domain.BlockPersistent;
import edu.itstep.blockchain.mining.Block;
import edu.itstep.blockchain.mining.Blockchain;
import edu.itstep.blockchain.mining.Constants;
import edu.itstep.blockchain.mining.Miner;
import edu.itstep.blockchain.repository.BlockPersistentRepo;



@SpringBootApplication
public class MiningApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiningApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(BlockPersistentRepo repo) {
		return (String[] arg) -> {
//			List<BlockPersistent> blocks = repo.findAll();
//			if(blocks.size() == 0) {
//			 Block genesis = new Block(Constants.GENESIS_PREV_HASH);
//			 Miner miner = new Miner();
//			 Blockchain chain = new Blockchain();
//			 HttpClient client = HttpClient.newHttpClient();
//			 HttpRequest request = HttpRequest.newBuilder()
//					 .uri(
//							 URI.create("http://localhost:8082/blockchain/v1/poolTransactions")
//							 ).build();
//			 genesis.addTransaction(genesisTransaction);
//			 miner.mine(genesis, chain);
//			}
		};
	}
}

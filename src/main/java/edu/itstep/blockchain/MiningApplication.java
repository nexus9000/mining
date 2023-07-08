package edu.itstep.blockchain;




import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.itstep.blockchain.domain.BlockPersistent;
import edu.itstep.blockchain.mining.Block;
import edu.itstep.blockchain.mining.Blockchain;
import edu.itstep.blockchain.mining.Constants;
import edu.itstep.blockchain.mining.Miner;
import edu.itstep.blockchain.mining.Transaction;
import edu.itstep.blockchain.repository.BlockPersistentRepo;
import lombok.extern.slf4j.Slf4j;



@SpringBootApplication
@Slf4j
public class MiningApplication {
	@Autowired
	RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(MiningApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(BlockPersistentRepo repo) {
		return (String[] arg) -> {
			List<BlockPersistent> blocks = repo.findAll();
			if(blocks.size() == 0) {
			 Block genesis = new Block(Constants.GENESIS_PREV_HASH);
			 Miner miner = new Miner();
			 Blockchain chain = new Blockchain();
			 final String uri = "http://localhost:8082/blockchain/v1/genesis";
			 ObjectMapper mapper = new ObjectMapper()
					 .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			 String result = restTemplate.getForObject(uri, String.class);
			 JsonNode node = mapper.readTree(result);
			 Security.addProvider(new BouncyCastleProvider());
			 String sender =  node.get("sender").asText();
			 String receiver =  node.get("receiver").asText();
			 Double amount = node.get("amount").asDouble();
			 byte[] publicBytes = Base64.decode(sender);
			 X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
			 
     		 KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
     		 PublicKey pubKeySender = keyFactory.generatePublic(keySpec);
     		 byte[] publicBytesReceiver = Base64.decode(receiver);
  		     X509EncodedKeySpec keySpecR = new X509EncodedKeySpec(publicBytesReceiver);
			 KeyFactory keyFactoryR = KeyFactory.getInstance("ECDSA","BC");
			 PublicKey pubKeyReceiver = keyFactoryR.generatePublic(keySpecR);
			// Transaction genesisTransaction = mapper.readValue(result,Transaction.class);
			 Transaction genesisTransaction = new Transaction(pubKeySender, pubKeyReceiver, amount);
			 genesis.addTransaction(genesisTransaction);
			 miner.mine(genesis, chain);
			 BlockPersistent bpGenesis = new BlockPersistent(genesis);
			 repo.save(bpGenesis);
			 log.info("genesis block was generated");
			}
		};
	}
}

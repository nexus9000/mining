package edu.itstep.blockchain.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Miner {
	private double reward;
	private final Logger logger = LoggerFactory.getLogger(Miner.class);

	public void mine(Block block, Blockchain blockChain) {
		while (!isGoldenHash(block)) {
			block.incrementNonce();
			block.generateHash();
		}

		logger.info("Hash is " + block.getHash());
		blockChain.addBlock(block);
		reward += Constants.MINER_REWARD;
	}

	public boolean isGoldenHash(Block block) {
		String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
		return block.getHash().substring(0, Constants.DIFFICULTY).equals(leadingZeros);
	}

	public double getReward() {
		return this.reward;
	}
}

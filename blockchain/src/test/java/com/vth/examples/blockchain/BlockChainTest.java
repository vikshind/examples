package com.vth.examples.blockchain;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class BlockChainTest {

	List<Block> blockchain = new ArrayList<>();
	int prefix = 4;
	String prefixString = new String(new char[prefix]).replace('\0', '0');

	@Test
	public void testAddBlockToBlockChain() {
		String previousHash = "0";
		if (blockchain.size() >= 1) {
			previousHash = blockchain.get(blockchain.size() - 1).getHash();
		}

		Block newBlock = new Block("The is a New Block.", previousHash);
		newBlock.mineBlock(prefix);
		assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
		blockchain.add(newBlock);

		if (blockchain.size() >= 1) {
			previousHash = blockchain.get(blockchain.size() - 1).getHash();
		}
		
		Block newBlock2 = new Block("The is a Second Block.", previousHash);
		newBlock2.mineBlock(prefix);
		assertTrue(newBlock2.getHash().substring(0, prefix).equals(prefixString));
		blockchain.add(newBlock2);
	}

	@Test
	public void testValidateBlockChain() {
		boolean flag = true;
		for (int i = 0; i < blockchain.size(); i++) {
			String previousHash = i == 0 ? "0" : blockchain.get(i - 1).getHash();
			flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
					&& previousHash.equals(blockchain.get(i).getPreviousHash())
					&& blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
			if (!flag)
				break;
		}
		assertTrue(flag);
	}
}

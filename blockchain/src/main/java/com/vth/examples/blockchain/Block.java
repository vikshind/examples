package com.vth.examples.blockchain;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
	private static final Logger LOGGER = Logger.getLogger(Block.class.getName());
	private String hash;
	private String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = System.currentTimeMillis();
		this.hash = calculateBlockHash();
	}

	protected String calculateBlockHash() {
		String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
		MessageDigest digest = null;
		byte[] bytes = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(dataToHash.getBytes(Charset.defaultCharset()));
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage());
		}
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	public String mineBlock(int prefix) {
		String prefixString = new String(new char[prefix]).replace('\0', '0');
		while (!hash.substring(0, prefix).equals(prefixString)) {
			nonce++;
			hash = calculateBlockHash();
		}
		return hash;
	}
	// standard getters and setters

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

}

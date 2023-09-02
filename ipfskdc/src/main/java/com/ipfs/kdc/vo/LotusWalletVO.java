package com.ipfs.kdc.vo;

import org.springframework.stereotype.Repository;

@Repository(value="LotusWalletVO")
public class LotusWalletVO {

	String address;
	String miner_id;
	String name;
	double fil;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMiner_id() {
		return miner_id;
	}
	public void setMiner_id(String miner_id) {
		this.miner_id = miner_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFil() {
		return fil;
	}
	public void setFil(double fil) {
		this.fil = fil;
	}
	
	
	
}

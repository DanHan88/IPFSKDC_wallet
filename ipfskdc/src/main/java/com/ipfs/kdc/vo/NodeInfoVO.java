package com.ipfs.kdc.vo;

import org.springframework.stereotype.Repository;

@Repository(value="NodeInfoVO")
public class NodeInfoVO {

	String qaPower;
	String rawPower;
	  int cc;
     int verified;
     int nonVerified;
     String miner_id;
     String network;
     String version;
     int mpool;
	
     
    
	public int getMpool() {
		return mpool;
	}
	public void setMpool(int mpool) {
		this.mpool = mpool;
	}
	public String getMiner_id() {
		return miner_id;
	}
	public void setMiner_id(String miner_id) {
		this.miner_id = miner_id;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	public int getNonVerified() {
		return nonVerified;
	}
	public void setNonVerified(int nonVerified) {
		this.nonVerified = nonVerified;
	}
	public String getQaPower() {
		return qaPower;
	}
	public void setQaPower(String qaPower) {
		this.qaPower = qaPower;
	}
	public String getRawPower() {
		return rawPower;
	}
	public void setRawPower(String rawPower) {
		this.rawPower = rawPower;
	}
	
}

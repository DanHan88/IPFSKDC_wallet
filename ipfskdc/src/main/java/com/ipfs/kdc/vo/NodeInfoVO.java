package com.ipfs.kdc.vo;

import org.springframework.stereotype.Repository;

@Repository(value="NodeInfoVO")
public class NodeInfoVO {

	String qaPower;
	String rawPower;
	  int cc;
     int verified;
     int nonVerified;
	
     
     
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

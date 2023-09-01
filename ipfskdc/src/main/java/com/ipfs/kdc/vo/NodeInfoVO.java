package com.ipfs.kdc.vo;

import org.springframework.stereotype.Repository;

@Repository(value="NodeInfoVO")
public class NodeInfoVO {

	String qaPower;
	String rawPower;
	
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

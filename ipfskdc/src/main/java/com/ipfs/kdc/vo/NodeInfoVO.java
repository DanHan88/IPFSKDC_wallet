package com.ipfs.kdc.vo;

import java.util.Date;
import java.util.List;

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
    double feeDebt;
 	double initialPledge;
 	double lockedFunds;
 	double preCommiDeposits;
    List<LotusWalletVO> lotusWalletVO;
    Date info_date;
    
    int cc_percent;
    int verified_percent;
    int nonVerified_percent;
   
    
	public int getCc_percent() {
		return cc_percent;
	}
	public void setCc_percent(int cc_percent) {
		this.cc_percent = cc_percent;
	}
	public int getVerified_percent() {
		return verified_percent;
	}
	public void setVerified_percent(int verified_percent) {
		this.verified_percent = verified_percent;
	}
	public int getNonVerified_percent() {
		return nonVerified_percent;
	}
	public void setNonVerified_percent(int nonVerified_percent) {
		this.nonVerified_percent = nonVerified_percent;
	}
	public Date getInfo_date() {
		return info_date;
	}
	public void setInfo_date(Date info_date) {
		this.info_date = info_date;
	}
	public double getFeeDebt() {
		return feeDebt;
	}
	public void setFeeDebt(double feeDebt) {
		this.feeDebt = feeDebt;
	}
	public double getInitialPledge() {
		return initialPledge;
	}
	public void setInitialPledge(double initialPledge) {
		this.initialPledge = initialPledge;
	}
	public double getLockedFunds() {
		return lockedFunds;
	}
	public void setLockedFunds(double lockedFunds) {
		this.lockedFunds = lockedFunds;
	}
	public double getPreCommiDeposits() {
		return preCommiDeposits;
	}
	public void setPreCommiDeposits(double preCommiDeposits) {
		this.preCommiDeposits = preCommiDeposits;
	}
	public List<LotusWalletVO> getLotusWalletVO() {
		return lotusWalletVO;
	}
	public void setLotusWalletVO(List<LotusWalletVO> lotusWalletVO) {
		this.lotusWalletVO = lotusWalletVO;
	}
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

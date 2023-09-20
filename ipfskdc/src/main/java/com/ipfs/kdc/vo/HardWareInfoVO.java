package com.ipfs.kdc.vo;


import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository(value="HardWareVO")
public class HardWareInfoVO {
	private String miner_id;
	Date info_date;
	String source_link;
	double cpu_busy;
	double ram_used;
	double swap_used;
	double root_fs_used;
	
	
	

	public double getRoot_fs_used() {
		return root_fs_used;
	}
	public void setRoot_fs_used(double root_fs_used) {
		this.root_fs_used = root_fs_used;
	}
	public String getMiner_id() {
		return miner_id;
	}
	public void setMiner_id(String miner_id) {
		this.miner_id = miner_id;
	}
	public Date getInfo_date() {
		return info_date;
	}
	public void setInfo_date(Date info_date) {
		this.info_date = info_date;
	}
	public String getSource_link() {
		return source_link;
	}
	public void setSource_link(String source_link) {
		this.source_link = source_link;
	}
	public double getCpu_busy() {
		return cpu_busy;
	}
	public void setCpu_busy(double cpu_busy) {
		this.cpu_busy = cpu_busy;
	}
	public double getRam_used() {
		return ram_used;
	}
	public void setRam_used(double ram_used) {
		this.ram_used = ram_used;
	}
	public double getSwap_used() {
		return swap_used;
	}
	public void setSwap_used(double swap_used) {
		this.swap_used = swap_used;
	}
	
	
	
	
}

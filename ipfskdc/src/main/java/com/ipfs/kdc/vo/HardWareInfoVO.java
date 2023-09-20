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
	String root_fs_total;
	String ram_total;
	String swap_total;
	int cpu_count;
	String uptime;
	
	
	

	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getRoot_fs_total() {
		return root_fs_total;
	}
	public void setRoot_fs_total(String root_fs_total) {
		this.root_fs_total = root_fs_total;
	}
	public String getRam_total() {
		return ram_total;
	}
	public void setRam_total(String ram_total) {
		this.ram_total = ram_total;
	}
	public String getSwap_total() {
		return swap_total;
	}
	public void setSwap_total(String swap_total) {
		this.swap_total = swap_total;
	}
	public int getCpu_count() {
		return cpu_count;
	}
	public void setCpu_count(int cpu_count) {
		this.cpu_count = cpu_count;
	}
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

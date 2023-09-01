package com.ipfs.kdc.vo;

import java.sql.Date;

import org.springframework.stereotype.Repository;

@Repository(value="SectorInfoVO")
public class SectorInfoVO {
	
    private String miner_id;
    private String sector_id;
    private String deals;
    private String pledged;
    private String state;
    private String to_upgrade;
    private String weight_type;
    private String event_type;
    private String deadline_id;
    private String partition_id;
    private String is_faulty;
    private String is_live;
    private String is_recovering;
    private double qualityPower;
    private String is_active;
    private Date reg_date;
    
    
    
    
    
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public double getQualityPower() {
		return qualityPower;
	}
	public void setQualityPower(double qualityPower) {
		this.qualityPower = qualityPower;
	}
	public String getMiner_id() {
		return miner_id;
	}
	public void setMiner_id(String miner_id) {
		this.miner_id = miner_id;
	}
	public String getSector_id() {
		return sector_id;
	}
	public void setSector_id(String sector_id) {
		this.sector_id = sector_id;
	}
	public String getDeals() {
		return deals;
	}
	public void setDeals(String deals) {
		this.deals = deals;
	}
	public String getPledged() {
		return pledged;
	}
	public void setPledged(String pledged) {
		this.pledged = pledged;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTo_upgrade() {
		return to_upgrade;
	}
	public void setTo_upgrade(String to_upgrade) {
		this.to_upgrade = to_upgrade;
	}
	public String getWeight_type() {
		return weight_type;
	}
	public void setWeight_type(String weight_type) {
		this.weight_type = weight_type;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getDeadline_id() {
		return deadline_id;
	}
	public void setDeadline_id(String deadline_id) {
		this.deadline_id = deadline_id;
	}
	public String getPartition_id() {
		return partition_id;
	}
	public void setPartition_id(String partition_id) {
		this.partition_id = partition_id;
	}
	public String getIs_faulty() {
		return is_faulty;
	}
	public void setIs_faulty(String is_faulty) {
		this.is_faulty = is_faulty;
	}
	public String getIs_live() {
		return is_live;
	}
	public void setIs_live(String is_live) {
		this.is_live = is_live;
	}
	public String getIs_recovering() {
		return is_recovering;
	}
	public void setIs_recovering(String is_recovering) {
		this.is_recovering = is_recovering;
	}

	
	
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	public SectorInfoVO merge(SectorInfoVO other) {
        if (other.deals != null) {
            this.deals = other.deals;
        }
        if (other.pledged != null) {
            this.pledged = other.pledged;
        }
        if (other.state != null) {
            this.state = other.state;
        }
        if (other.to_upgrade != null) {
            this.to_upgrade = other.to_upgrade;
        }
        if (other.weight_type != null) {
            this.weight_type = other.weight_type;
        }
        if (other.event_type != null) {
            this.event_type = other.event_type;
        }
        if (other.deadline_id != null) {
            this.deadline_id = other.deadline_id;
        }
        if (other.partition_id != null) {
            this.partition_id = other.partition_id;
        }
        if (other.is_faulty != null) {
            this.is_faulty = other.is_faulty;
        }
        if (other.is_live != null) {
            this.is_live = other.is_live;
        }
        if (other.is_recovering != null) {
            this.is_recovering = other.is_recovering;
        }
        if (other.qualityPower > 0) {
            this.qualityPower = other.qualityPower;
        }
        if (other.is_active !=null) {
            this.is_active = other.is_active;
        }
        return this;
    }
	public Boolean isEmpty() {
		return miner_id == null &&
	               sector_id == null &&
	               deals == null &&
	               pledged == null &&
	               state == null &&
	               to_upgrade == null &&
	               weight_type == null &&
	               event_type == null &&
	               deadline_id == null &&
	               partition_id == null &&
	               is_faulty == null &&
	               is_live == null &&
	               is_active == null &&
	               is_recovering == null;
	}
	
	
}

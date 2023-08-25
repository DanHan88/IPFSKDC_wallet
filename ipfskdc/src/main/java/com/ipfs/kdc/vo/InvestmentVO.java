/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Repository
 */
package com.ipfs.kdc.vo;

import java.util.Date;
import org.springframework.stereotype.Repository;

@Repository(value="investmentVO")
public class InvestmentVO {
    int investment_id;
    String user_email;
    String user_name;
    String product_name;
    Date purchase_date;
    int purchase_size;

    public String getUser_email() {
        return this.user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getPurchase_date() {
        return this.purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public int getPurchase_size() {
        return this.purchase_size;
    }

    public void setPurchase_size(int purchae_size) {
        this.purchase_size = purchae_size;
    }

    public int getInvestment_id() {
        return this.investment_id;
    }

    public void setInvestment_id(int investment_id) {
        this.investment_id = investment_id;
    }
}


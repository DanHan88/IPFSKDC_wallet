/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Repository
 */
package com.ipfs.kdc.vo;

import org.springframework.stereotype.Repository;

@Repository(value="investmentCategoryVO")
public class InvestmentCategoryVO {
    String investment_category_name;
    String investment_names;
    String investment_index;

    public String getInvestment_category_name() {
        return this.investment_category_name;
    }

    public void setInvestment_category_name(String investment_category_name) {
        this.investment_category_name = investment_category_name;
    }

    public String getInvestment_names() {
        return this.investment_names;
    }

    public void setInvestment_names(String investment_names) {
        this.investment_names = investment_names;
    }

    public String getInvestment_index() {
        return this.investment_index;
    }

    public void setInvestment_index(String investment_index) {
        this.investment_index = investment_index;
    }
}


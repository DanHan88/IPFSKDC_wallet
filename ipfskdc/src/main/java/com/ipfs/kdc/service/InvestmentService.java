/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.ipfs.kdc.service;

import com.ipfs.kdc.mapper.InvestmentMapper;
import com.ipfs.kdc.vo.InvestmentCategoryVO;
import com.ipfs.kdc.vo.InvestmentVO;
import com.ipfs.kdc.vo.LoginVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService {
    @Autowired
    InvestmentMapper investmentMapper;

    public List<InvestmentVO> getInvestmentList() {
        return this.investmentMapper.selectInvestmentList();
    }

    public List<InvestmentVO> getInvestmentListByCategory(String category) {
        InvestmentCategoryVO investmentCategory = this.investmentMapper.selectInvestmentCategoryByIndex(category);
        return this.investmentMapper.getInvestmentListByCategory(investmentCategory.getInvestment_names().split(","));
    }

    public List<InvestmentCategoryVO> getInvestmentCategoryList() {
        return this.investmentMapper.selectInvestmentCategoryList();
    }

    public List<String> getIDList() {
        return this.investmentMapper.selectIDList();
    }

    public LoginVO findByUserId(String id) {
        return this.investmentMapper.findByUserId(id);
    }
}


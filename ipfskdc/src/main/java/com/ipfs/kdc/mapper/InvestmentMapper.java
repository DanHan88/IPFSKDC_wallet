/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.apache.ibatis.annotations.Mapper
 */
package com.ipfs.kdc.mapper;

import com.ipfs.kdc.vo.InvestmentCategoryVO;
import com.ipfs.kdc.vo.InvestmentVO;
import com.ipfs.kdc.vo.LoginVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvestmentMapper {
    public List<InvestmentVO> selectInvestmentList();

    public List<InvestmentCategoryVO> selectInvestmentCategoryList();

    public List<String> selectIDList();

    public LoginVO findByUserId(String id);

    public List<InvestmentVO> getInvestmentListByCategory(String[] names);

    public InvestmentCategoryVO selectInvestmentCategoryByIndex(String category);
}


package com.ipfs.kdc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipfs.kdc.mapper.NanoDCMapper;
import com.ipfs.kdc.service.NanoDCService;
import com.ipfs.kdc.vo.NodeInfoVO;

@Controller
public class NanoDCController {

	 @Autowired
	    private NanoDCService nanoDCService;
	 @Autowired
		NanoDCMapper nanoDCMapper;
	
	@GetMapping(value={"/monitor"})
    public ModelAndView login(HttpServletRequest request,@RequestParam("monitorID") String monitorID) throws IOException {
    	
        ModelAndView mav = new ModelAndView();
        NodeInfoVO nodeInfoVO = new NodeInfoVO();
        nodeInfoVO.setMiner_id("f01227505");
        nodeInfoVO = nanoDCMapper.selectLatestNodeInfo(nodeInfoVO);
        nodeInfoVO.setLotusWalletVO(nanoDCMapper.selectLotusWalletVO(nodeInfoVO));
        mav.addObject("nodeInfoVO", nodeInfoVO);
        if(monitorID.equals("f001")) {
        	mav.setViewName("views/cards");
        }else if(monitorID.equals("f002")) {
        	mav.setViewName("views/charts");
        }
        return mav;
    }
	
	
	
}

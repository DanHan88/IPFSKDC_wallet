package com.ipfs.kdc.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipfs.kdc.mapper.NanoDCMapper;
import com.ipfs.kdc.service.NanoDCService;
import com.ipfs.kdc.service.UtilityService;
import com.ipfs.kdc.vo.LotusWalletVO;
import com.ipfs.kdc.vo.NodeInfoVO;

@Controller
public class NanoDCController {

	 @Autowired
	    NanoDCService nanoDCService;
	 @Autowired
		NanoDCMapper nanoDCMapper;
	 @Autowired
	 	UtilityService util;
	@GetMapping(value={"/monitor_nodeInfo"})
    public ModelAndView login(HttpServletRequest request,@RequestParam("minerId") String minerId) throws IOException {
    	//f01227505
        ModelAndView mav = new ModelAndView();
        mav.addObject("nodeInfoVO", nanoDCService.initNodeInfo(minerId));
        mav.setViewName("views/nodeInfo");
        return mav;
    }
	@GetMapping(value={"/monitor_hardwareInfo"})
    public ModelAndView login(HttpServletRequest request,@RequestParam("minerId") String minerId,@RequestParam("hardwareId") String hardwareId) throws IOException {
    	//f01227505
        ModelAndView mav = new ModelAndView();
        mav.addObject("nodeInfoVO", nanoDCService.initNodeInfo(minerId));
        mav.setViewName("views/nodeInfo");
        return mav;
    }
	
	
	
}

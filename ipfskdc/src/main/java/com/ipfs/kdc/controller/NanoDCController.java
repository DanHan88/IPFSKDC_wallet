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
import com.ipfs.kdc.vo.HardWareInfoVO;
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
    public ModelAndView nodeInfo(HttpServletRequest request,@RequestParam("minerId") String minerId) throws IOException {
    	//f01227505 //f01695888
        ModelAndView mav = new ModelAndView();
        NodeInfoVO nodeInfoVO = nanoDCService.initNodeInfo(minerId);
        mav.addObject("nodeInfoVO", nodeInfoVO);
        mav.setViewName("views/nodeInfo");
        return mav;
    }
	@GetMapping(value={"/monitor_hardwareInfo"})
    public ModelAndView hardwareInfo(HttpServletRequest request,@RequestParam("minerId") String minerId, @RequestParam("source_link") String source_link) throws IOException {
    	//f01227505 //f01695888
        ModelAndView mav = new ModelAndView();
        HardWareInfoVO hardWareInfoVO = new HardWareInfoVO();
        hardWareInfoVO.setMiner_id(minerId); //"http://121.178.82.230:9100/metrics"
        hardWareInfoVO.setSource_link("http://"+source_link);
        hardWareInfoVO = nanoDCMapper.selectLatestHardWareInfo(hardWareInfoVO);
        mav.addObject("hardWareInfoVO", hardWareInfoVO);
        mav.setViewName("views/hardWareInfo");
        return mav;
    }
	@GetMapping(value={"/monitor_boostInfo"})
    public ModelAndView boostInfo(HttpServletRequest request,@RequestParam("minerId") String minerId) throws IOException {
    	//f01227505 //f01695888
		ModelAndView mav = new ModelAndView();
        NodeInfoVO nodeInfoVO = nanoDCService.initNodeInfo(minerId);
        mav.addObject("nodeInfoVO", nodeInfoVO);
        mav.setViewName("views/boostInfo");
        return mav;
    }
	@GetMapping(value={"/monitor_rackInfo"})
    public ModelAndView rackInfo(HttpServletRequest request) throws IOException {
    	//f01227505 //f01695888
		ModelAndView mav = new ModelAndView();
        mav.setViewName("views/rackInfo");
        return mav;
    }
	@GetMapping(value={"/monitor_switchInfo"})
    public ModelAndView switchInfo(HttpServletRequest request) throws IOException {
    	//f01227505 //f01695888
		ModelAndView mav = new ModelAndView();
        mav.setViewName("views/switchInfo");
        return mav;
    }
	@GetMapping(value={"/monitor_upsController"})
    public ModelAndView upsController(HttpServletRequest request) throws IOException {
    	//f01227505 //f01695888
		ModelAndView mav = new ModelAndView();
        mav.setViewName("views/upsController");
        return mav;
    }
	@GetMapping(value={"/monitor_storageInfo"})
    public ModelAndView storageInfo(HttpServletRequest request) throws IOException {
    	//f01227505 //f01695888
		ModelAndView mav = new ModelAndView();
        mav.setViewName("views/storageInfo");
        return mav;
    }
	@GetMapping(value={"/404"})
    public ModelAndView page404(HttpServletRequest request) throws IOException {
    	//f01227505 //f01695888
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/404page");
        return mav;
    }
	@GetMapping(value={"/monitor_homepage"})
    public ModelAndView hompage(HttpServletRequest request) throws IOException {
    	//f01227505
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/homepage");
        return mav;
    }
}

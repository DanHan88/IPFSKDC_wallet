package com.ipfs.kdc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipfs.kdc.service.NanoDCService;

@Controller
public class NanoDCController {

	 @Autowired
	    private NanoDCService nanoDCService;
	
	
	@GetMapping(value={"/monitor"})
    public ModelAndView login(HttpServletRequest request,@RequestParam("monitorID") String monitorID) throws IOException {
    	
        ModelAndView mav = new ModelAndView();
        
        List<String> prometeusData = nanoDCService.getPrometheusData("http://175.207.91.25:9101/metrics");
        double qaPower = nanoDCService.processPrometheusData(prometeusData);
        mav.addObject("qaPower", qaPower +"byte");
        if(monitorID.equals("f001")) {
        	mav.setViewName("views/cards");
        }else if(monitorID.equals("f002")) {
        	mav.setViewName("views/charts");
        }
        return mav;
    }
	
	
	
}

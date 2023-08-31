/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.stereotype.Controller
 *  org.springframework.validation.BindingResult
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.ModelAttribute
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.servlet.ModelAndView
 *  org.springframework.web.servlet.mvc.support.RedirectAttributes
 */
package com.ipfs.kdc.controller;

import com.ipfs.kdc.service.InvestmentService;
import com.ipfs.kdc.vo.InvestmentCategoryVO;
import com.ipfs.kdc.vo.InvestmentVO;
import com.ipfs.kdc.vo.LoginVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private PasswordEncoder pwEncoder;

    @GetMapping(value={"/main"})
    public ModelAndView main(HttpServletRequest request, String category,String sb) {
        ModelAndView mav = new ModelAndView();
        
        if(!investmentService.checkSession(request)) {
        	mav.setViewName("redirect:/");
            return mav;
        }
        HttpSession session = request.getSession();
        LoginVO loginVO = (LoginVO)session.getAttribute("user");
        List<InvestmentCategoryVO> listInvestmentCategory = this.investmentService.getInvestmentCategoryList();
        List<InvestmentVO> listInvestment = category == null || category.equals("0") ? this.investmentService.getInvestmentList() : this.investmentService.getInvestmentListByCategory(category);
        mav.addObject("sb", sb);
        mav.addObject("listInvestment", listInvestment);
        mav.addObject("listInvestmentCategory", listInvestmentCategory);
        mav.addObject("loginVO", loginVO);
        mav.setViewName("views/default_layout");
        return mav;
    }

    @GetMapping(value={"/"})
    public ModelAndView login(@ModelAttribute LoginVO loginVO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/login");
        return mav;
    }

    @GetMapping(value={"/logoutUser"})
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping(value={"/login.do"})
    private String doLogin(LoginVO loginVO, BindingResult result, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginVO lvo = this.investmentService.findByUserId(loginVO.getId());
        HttpSession session = request.getSession();
        String rawPw = "";
        String encodePw = "";
        if (lvo != null) {
            rawPw = loginVO.getPassword();
            if (this.pwEncoder.matches((CharSequence)rawPw, encodePw = lvo.getPassword())) {
                lvo.setPassword("");
                session.setAttribute("user", (Object)lvo);
                return "redirect:/main";
            }
            redirect.addFlashAttribute("result", (Object)0);
            return "redirect:/";
        }
        redirect.addFlashAttribute("result", (Object)0);
        return "redirect:/";
    }
    @PostMapping(value={"/payout"})
    private ModelAndView payout(RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response,@RequestParam("investment_id") List<String> investment_ids ) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	if(!investmentService.checkSession(request)) {
    		mav.setViewName("redirect:/");
            return mav;
        }
        
    	String cmd = "ll >> ~/testlog.log";
        String[] command = {"/bin/sh","-c",cmd};
        StringBuffer sb = new StringBuffer();

        try 
        {
        	ProcessBuilder processBuilder = new ProcessBuilder(command);
        	
        	Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n"); // Add newline character to separate lines
            }

            int exitCode = process.waitFor(); // Wait for the process to finish
            if (exitCode != 0) {
                sb.append("Command failed with exit code: ").append(exitCode);
            }
         } 
        catch (Exception e) 
        {     
        	sb.append("ERROR!!!: ").append(e.getMessage());
        }
    	
        mav.addObject("sb", sb);
        mav.setViewName("redirect:/main");
        return mav;
        
        
        
    }  
    
}


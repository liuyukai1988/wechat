package com.fanenet.interview_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private HttpSession session;
 
	@RequestMapping(value="/home")
	public String home(){
		System.out.println("redirect to login page!");
		return "index";
	}

	@RequestMapping(value="/main")
	public ModelAndView main(){
		System.out.println("redirect to main page!");
		Object userName = session.getAttribute("name");
		ModelAndView mode = new ModelAndView();
		if(null != userName){
			mode.addObject("userName", userName);
			mode.setViewName("main");
		}else{
			mode.setViewName("index");
		}
		return mode;
	}

	/**
	 * 注销
	 *
	 * @return String 注销
	 */
	@RequestMapping(value="/logout")
	public String logout(){
		session.removeAttribute("name");
		System.out.println("redirect to login page!");
		return "index";
	}
}
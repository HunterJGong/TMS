package com.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiDocController {

	@RequestMapping(value="rest/apis")
	public String restAPI(){
		return "TMSrest";
	}
	
}

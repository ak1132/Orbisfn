package com.orbisfn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

	@RequestMapping("/etf")
	public String getETFs() {
		return "Hello";
	}
}

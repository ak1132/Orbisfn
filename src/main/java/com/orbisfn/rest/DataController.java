package com.orbisfn.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbisfn.entity.ETFs;

@RestController
public class DataController {

	@RequestMapping("/etf")
	public List<ETFs> getETFs() {
		return null;
	}
}

package com.orbisfn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orbisfn.repository.FundRepository;

@RestController
public class QueryController {

	@Autowired
	private FundRepository fundRepository;

	@RequestMapping(value = "/listETFs", method = RequestMethod.GET)
	public ResponseEntity<?> getETFList() {
		return ResponseEntity.ok(fundRepository.getAllTickers());
	}
	
	
}

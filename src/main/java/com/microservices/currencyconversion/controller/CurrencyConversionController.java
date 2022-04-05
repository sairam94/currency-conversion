package com.microservices.currencyconversion.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.currencyconversion.feign.client.CurrencyConversionProxy;

import com.microservices.currencyconversion.model.CurrencyConversionModel;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyConversionProxy currencyConversionProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionModel getConversionAmount(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionModel currencyConversionModel = 
				currencyConversionProxy.getCurrencyExchange(from, to);
		
		return new CurrencyConversionModel(currencyConversionModel.getId(), 
				currencyConversionModel.getFrom(), 
				currencyConversionModel.getTo(), 
				quantity, 
				currencyConversionModel.getConversionMultiples(), 
				quantity.multiply(currencyConversionModel.getConversionMultiples()), 
				currencyConversionModel.getEncironment());
	}
}

package com.microservices.currencyconversion.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.currencyconversion.model.CurrencyConversionModel;

@FeignClient(name = "currency-exchange",url = "http://localhost:8001")
public interface CurrencyConversionProxy {
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionModel getCurrencyExchange(@PathVariable String from, 
			@PathVariable String to);
}

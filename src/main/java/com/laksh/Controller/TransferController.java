package com.laksh.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laksh.Exception.TransferException;
import com.laksh.Model.TransferRequest;
import com.laksh.Model.TransferResponse;
import com.laksh.Service.TransferService;

@RestController
public class TransferController {
	
	private Logger LOGGER = Logger.getLogger(TransferController.class);
    @Autowired
	TransferService service;
    	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> transfer(@RequestBody TransferRequest request) throws Exception {
		LOGGER.info("Inside RestController class");
		TransferResponse response =  new TransferResponse();
		
		try {
		 service.verify(request);
		}catch (TransferException e) {
			LOGGER.error(e);
			return new ResponseEntity<>(new String('"' + e.getMessage() + '"'), HttpStatus.BAD_REQUEST);
		}
		
		try {
		 response = service.transfer(request);
		 return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (TransferException e) {
			LOGGER.error(e);
			return new ResponseEntity<>(new String('"' + e.getMessage() + '"'), HttpStatus.METHOD_FAILURE);
		}
    }
}

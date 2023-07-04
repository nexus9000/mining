package edu.itstep.blockchain.controllers;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.itstep.blockchain.mining.Transaction;



@RestController
public class Controller {
	private static Logger logger = LoggerFactory.getLogger(Controller.class);
	

		@Autowired
		RestTemplate restTemplate;
        @GetMapping("/test")
		public String getTest() {
			final String uri = "http://localhost:8082/blockchain/v1/testBlock";

			String result = restTemplate.getForObject(uri, String.class);
            return result;
			
		}
        
        @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
        public String getJson() throws JsonMappingException, JsonProcessingException {
			final String uri = "http://localhost:8082/blockchain/v1/genesis";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "aplication/json; charset=utf-8");
			String result = restTemplate.getForObject(uri, String.class);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Transaction genesisTransaction = mapper.readValue(result,Transaction.class);
		    logger.info(genesisTransaction.toString());
            return result;
			
		}
}

package edu.itstep.blockchain.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
	
	

		@Autowired
		RestTemplate restTemplate;
        @GetMapping("/test")
		public String getTest() {
			final String uri = "http://localhost:8082/blockchain/v1/testBlock";

			String result = restTemplate.getForObject(uri, String.class);
            return result;
			
		}
	
}

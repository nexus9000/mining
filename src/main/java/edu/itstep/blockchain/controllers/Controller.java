package edu.itstep.blockchain.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        
        @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
        public String getJson() {
			final String uri = "http://localhost:8082/blockchain/v1/poolTransactions";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "aplication/json; charset=utf-8");
			String result = restTemplate.getForObject(uri, String.class);
            return result;
			
		}
}

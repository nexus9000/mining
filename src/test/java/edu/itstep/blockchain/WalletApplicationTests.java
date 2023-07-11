package edu.itstep.blockchain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.itstep.blockchain.domain.User;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest()
@Slf4j
class WalletApplicationTests {
	private static String createUserUrl;
	private static String updateUserUrl;
	
    RestTemplate restTemplate;
	
	 HttpHeaders headers;

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	 JSONObject userJsonObject;
	@BeforeTestClass
	public  void runBeforeAllTestMethods() throws JSONException {
	    createUserUrl = "http://localhost:8084/createUser";
	    updateUserUrl = "http://localhost:8084/updateUser";

	    restTemplate = new RestTemplate();
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    userJsonObject = new JSONObject();
	    userJsonObject.put("id", 1);
	    userJsonObject.put("name", "John");
	}
	@Test
	public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
	  throws IOException, JSONException {
		createUserUrl = "http://localhost:8084/createUser";
	    updateUserUrl = "http://localhost:8084/updateUser";

	    restTemplate = new RestTemplate();
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    userJsonObject = new JSONObject();
	    userJsonObject.put("id", 1);
	    userJsonObject.put("name", "John");

		
	    HttpEntity<String> request = 
	      new HttpEntity<String>(userJsonObject.toString(), headers);
	   User person = restTemplate.postForObject(createUserUrl, request, User.class);
        log.info(person.getName());
	    assertNotNull(person);
	    assertNotNull(person.getName());
	    String userResultAsJsonStr = 
	      restTemplate.postForObject(createUserUrl, request, String.class);
	    JsonNode root = objectMapper.readTree(userResultAsJsonStr);
	    
	    assertNotNull(userResultAsJsonStr);
	    assertNotNull(root);
	    assertNotNull(root.path("name").asText());
	}

}

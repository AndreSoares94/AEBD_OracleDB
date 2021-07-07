package aebd.group.webapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WelcomeController {

	@GetMapping("/cpu")
	public String cpu() {
		GETCPU cpu = new GETCPU();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(cpu.cpu());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@GetMapping("/datafile")
	public String data() {
		GETDataFile cpu = new GETDataFile();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(cpu.datafile());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}


	@GetMapping("/memory")
	public String memory() {
		GETMemory cpu = new GETMemory();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(cpu.memory());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@GetMapping("/session")
	public String session() {
		GETSession cpu = new GETSession();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(cpu.session());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@GetMapping("/table")
	public String table() {
		GETTable cpu = new GETTable();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(cpu.table());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}


	@GetMapping("/tablespace")
	public String tablespace() {
		GETTablespace cpu = new GETTablespace();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(cpu.datafile());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@GetMapping("/user")
	public String user() {
		GETUser user = new GETUser();


		ObjectMapper objectMapper = new ObjectMapper();
		String json="{\"rows\": ";
		try {
			json += objectMapper.writeValueAsString(user.user());
			json+="}";
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}



}

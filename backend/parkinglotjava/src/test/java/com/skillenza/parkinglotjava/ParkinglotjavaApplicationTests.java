package com.skillenza.parkinglotjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkinglotjavaApplicationTests {

	  protected MockMvc mvc;
	   @Autowired
	   WebApplicationContext webApplicationContext;
	   
	@Test
	public void getparkinglist() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/api/parkings";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(200, status);
			   String content = mvcResult.getResponse().getContentAsString();
			   ObjectMapper objectMapper = new ObjectMapper();
			   
			   ParkingLot plist[]=objectMapper.readValue(content, ParkingLot[].class );
			   assertTrue(plist.length>0);
	}
	
	@Test
	public void postParkingdata()throws Exception{
		String uri = "/api/parkings";
		ParkingLot parking=new ParkingLot();
		parking.setLot(43);
		parking.setParking_amount(33);
		parking.setVehicle_number(6007);
		ObjectMapper objectMapper = new ObjectMapper();
		String injson=objectMapper.writeValueAsString(parking);
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(injson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		  
		
		
	}

}


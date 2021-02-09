package com.example.mabaya;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class) 
@SpringBootTest
public class CampaignRestControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetCampaign() throws Exception {
		mockMvc.perform(get("/api/campaign/1")).andExpect(status().isOk())
		.andExpect(jsonPath("$.campaignName").value("BMW Campaign"))
		.andExpect(jsonPath("$.category").value("cars"));
	}
	
	@Test
	public void testGetAllCampaign() {
		try {
			mockMvc.perform(get("/api/campaign")).andExpect(status().isOk())
			.andExpect(jsonPath("$[0].campaignName", is("BMW Campaign")));
			System.out.println("lknlk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

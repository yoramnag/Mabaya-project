package com.example.mabaya.rest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mabaya.entity.Campaign;
import com.example.mabaya.entity.Product;
import com.example.mabaya.service.CampaignService;

@RestController
@RequestMapping("/api")
public class CampaignRestController {
	
	private CampaignService campaignService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CampaignRestController.class);
	
	
	
	
	public CampaignRestController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public CampaignRestController(CampaignService campaignService) {
		super();
		this.campaignService = campaignService;
	}
	
	// expose "/campaigns" and return list of campaigns
	@GetMapping("/campaign")
	public List<Campaign> findAll() {
		LOGGER.info("In CampaignRestController - running findAll function.");
		return campaignService.findAll();
	}
	
	// add mapping for GET /campaign/{campaignsId}
	@GetMapping("/campaign/{campaignId}")
	public Campaign getCampaign(@PathVariable int campaignId) {
		LOGGER.info("In CampaignRestController - running get Campaign function with the value " + campaignId);
		Campaign theCampaign = campaignService.findById(campaignId);
		
		if (theCampaign == null) {
			LOGGER.error("In CampaignRestController - running get Campaign function with the value " + campaignId + "Campaign was not found");
			throw new RuntimeException("campaign id not found - " + campaignId);
		}
		
		return theCampaign;
	}
	
	// add mapping for POST /campaign - add new campaign
	@PostMapping("/campaign")
	public Campaign addCampaign(@RequestBody Campaign theCampaign) {
		
		LOGGER.info("In CampaignRestController - running add Campaign function with the vaule " + theCampaign.toString());
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theCampaign.setId(0);
		
		campaignService.save(theCampaign);
		
		LOGGER.info("In CampaignRestController - new Campaign was created ");
		return theCampaign;
	}
	
	// add mapping for PUT /campaign - update existing campaign
	@PutMapping("/campaign")
	public Campaign updateCampaignr(@RequestBody Campaign theCampaign) {
		LOGGER.info("In CampaignRestController - running update Campaign function with the vaule " + theCampaign.toString());
		
		campaignService.save(theCampaign);
		
		LOGGER.info("In CampaignRestController - campaign was updated" );
		
		return theCampaign;
	}
	
	// add mapping for DELETE /campaign/{campaignsId} - delete campaign
	@DeleteMapping("/campaign/{campaignId}")
	public String deleteCampaign(@PathVariable int campaignId) {
		
		LOGGER.info("In CampaignRestController - delete Campaign function with the vaule " + campaignId);
		
		Campaign tempCampaign = campaignService.findById(campaignId);
		
		// throw exception if null
		
		if (tempCampaign == null) {
			LOGGER.info("In CampaignRestController - Campaign wuth the ID " + campaignId + "was not found");
			throw new RuntimeException("campaign id not found - " + campaignId);
		}
		
		campaignService.deleteById(campaignId);
		LOGGER.info("In CampaignRestController - campaign with the ID " + campaignId + " was deleted");
		
		return "Deleted campaign id - " + campaignId;
	}
	
	@GetMapping("/campaignFindByCategory/{category}")
	public Product getCampaignByCategory(@PathVariable String category) {
		
		LOGGER.info("In CampaignRestController - get campaign by category : " +category+ " and max bid ");
		
		Product product = new Product(); 
		Date campaignStratDate = null;
		Date compaignEndDate = null;
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate = formatter.format(date);  
	    Date today = null;
		try {
			today = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timestamp ts =new Timestamp(today.getTime()); 
		List<Campaign> campaigns = campaignService.findByCategory(category,ts);
		
		if (!campaigns.isEmpty()) {
			LOGGER.info("In CampaignRestController - no campaigns was found with the category : " + category);
			campaignStratDate = campaigns.get(0).getStartDate();
			compaignEndDate = campaigns.get(0).getEndDate();
			if (betweenTwoDates(ts,campaignStratDate,compaignEndDate)) {
				product = campaigns.get(0).getProducts().get(0);
//				LOGGER.info("In CampaignRestController - form " + campaigns.get(0).toString());
			}
			else {
				product = callMaxCompaign(product, campaigns);
//				LOGGER.info("In CampaignRestController - return product : " + product.toString() + "from campaign name : " + campaigns.get(0).getCampaignName());
			}
		}
		else{
			product = callMaxCompaign(product, campaigns);
//			LOGGER.info("In CampaignRestController - return product : " + product.toString() + "from campaign name : " + campaigns.get(0).getCampaignName());
		}
		
		return product;
	}
	
	@GetMapping("/campaignFindMaxBid")
	public List<Campaign> getMaxBid() {
		
		return campaignService.findMaxBid();
	}
	
	private Product callMaxCompaign(Product product,List<Campaign> campaigns) {
		campaigns = campaignService.findMaxBid();
		product = campaigns.get(0).getProducts().get(0);
		return product;
	}
	
	private boolean betweenTwoDates(Date date, Date start , Date end) {
		long diffInMillies = Math.abs(end.getTime() - start.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    if(diff == 10) {
	    	return true;
	    }

		return false;
		
	}
	
	
}

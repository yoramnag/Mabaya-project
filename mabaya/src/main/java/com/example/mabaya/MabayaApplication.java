package com.example.mabaya;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.mabaya.dao.CampaignsRepository;
import com.example.mabaya.dao.ProductRepository;
import com.example.mabaya.entity.Campaign;
import com.example.mabaya.entity.Product;

@SpringBootApplication
public class MabayaApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CampaignsRepository campaignsRepository;

	public static void main(String[] args) {
		SpringApplication.run(MabayaApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Cleanup the tables
		productRepository.deleteAllInBatch();
		campaignsRepository.deleteAllInBatch();
		
		String carsCampaignS = "01-03-2121";
		Date carsCampaignStart = convertToDate(carsCampaignS);

		Campaign carsCampaign = new Campaign("BMW Campaign", carsCampaignStart, "cars", 500000);
		
		Product x6 = new Product("X6", "cars", 100000, 101);
		
		Product z3 = new Product("Z3", "cars", 10000, 102);
		
		carsCampaign.getProducts().add(z3);
		carsCampaign.getProducts().add(x6);
		
//		x6.getCampaigns().add(carsCampaign);
//		z3.getCampaigns().add(carsCampaign);
		
		String tShirtCampaignS = "07-02-2121";
		Date tShirtCampaignStart = convertToDate(tShirtCampaignS);
		
		Campaign tShirtCampaign = new Campaign("Shirts ", tShirtCampaignStart, "shirts", 20000);
		
		Product golf = new Product("golf", "T-Shirt", 100, 201);
		Product tshirt = new Product("t-shirt", "T-Shirt", 100, 202);
		
		tShirtCampaign.getProducts().add(tshirt);
		tShirtCampaign.getProducts().add(golf);
		
		String tShirtCampaignS2 = "01-05-2121";
		Date tShirtCampaignStart2 = convertToDate(tShirtCampaignS2);
		
		Campaign tShirtCampaign2 = new Campaign("Shirts ", tShirtCampaignStart2, "shirts", 100);
		
		tShirtCampaign2.getProducts().add(tshirt);
		tShirtCampaign2.getProducts().add(golf);
		
		String tShirtCampaignS3 = "01-05-2121";
		Date tShirtCampaignStart3 = convertToDate(tShirtCampaignS3);
		
		Campaign tShirtCampaign3 = new Campaign("Shirts ", tShirtCampaignStart3, "shirts", 10000);
		
		tShirtCampaign3.getProducts().add(tshirt);
		tShirtCampaign3.getProducts().add(golf);
		
		List <Campaign> campaigns = Arrays.asList(carsCampaign,tShirtCampaign,tShirtCampaign2,tShirtCampaign3);
		 
		campaignsRepository.saveAll(campaigns);
		
	}
	
	Date convertToDate(String receivedDate) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(receivedDate);
        return date;
    }

}

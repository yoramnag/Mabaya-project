package com.example.mabaya.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.mabaya.entity.Campaign;

public interface CampaignService {
	
	public List<Campaign> findAll();
	
	public Campaign findById(int theId);
	
	public void save(Campaign theCampaign);
	
	public void deleteById(int theId);
	
	public List<Campaign> findByCategory(String category,Timestamp date);
	
	public List<Campaign> findMaxBid();
	
	

}

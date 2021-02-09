package com.example.mabaya.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mabaya.dao.CampaignsRepository;
import com.example.mabaya.entity.Campaign;

@Service
public class CampaignServiceImpl implements CampaignService{
	
	private CampaignsRepository campaignsRepository;
	
	@Autowired
	public CampaignServiceImpl(CampaignsRepository campaignsRepository) {
		super();
		this.campaignsRepository = campaignsRepository;
	}
	
	// return all Campaigns
	@Override
	public List<Campaign> findAll() {
		return campaignsRepository.findAll();
	}
	
	// get a specific Campaign by it's Id
	@Override
	public Campaign findById(int theId) {
		Optional<Campaign> result = campaignsRepository.findById(theId);
		
		Campaign theCampaign = null;
		
		if (result.isPresent()) {
			theCampaign = result.get();
		} else {
			throw new RuntimeException("didn't find Campaign ID - " + theId);
		}
		return theCampaign;
	}

	// add new Campaign
	@Override
	public void save(Campaign theCampaign) {
		campaignsRepository.save(theCampaign);
	}
	
	// delete a specific Campaign by it's Id
	@Override
	public void deleteById(int theId) {
		campaignsRepository.deleteById(theId);
	}

	@Override
	public List<Campaign> findByCategory(String category, Timestamp date) {
		return campaignsRepository.findBycategory(category,date);
	}

	@Override
	public List<Campaign> findMaxBid() {
		return campaignsRepository.findMaxBid();
	}
	

}

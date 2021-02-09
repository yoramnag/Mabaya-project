package com.example.mabaya.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mabaya.entity.Campaign;

@Repository
public interface CampaignsRepository extends JpaRepository<Campaign , Integer>{
	
	@Query(value = "SELECT c FROM Campaign c WHERE c.category=?1 AND "
			+ "c.bid = (SELECT MAX(c.bid) FROM c WHERE c.category=?1)")
	List<Campaign> findBycategory(String category, Timestamp date);
	
//	@Query(value = "SELECT c FROM Campaign c WHERE ?2 BETWEEN c.startDate AND c.endDate")
//	List<Campaign> findBycategory(String category, Timestamp date);
	
//	@Query(value = "SELECT c FROM Campaign c WHERE c.category=?1 AND ?2 BETWEEN c.startDate AND c.endDate")
//	List<Campaign> findBycategory(String category, Timestamp date);
	
	@Query(value = "SELECT c FROM Campaign c WHERE c.bid = (SELECT MAX(c.bid) FROM c)")
	List <Campaign> findMaxBid();
	
}

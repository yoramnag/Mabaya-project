package com.example.mabaya.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.mapping.Array;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="campaign")
@JsonIgnoreProperties(value = { "products" })
public class Campaign {
	
	//private static final long serialVersionUID = -6636776954812908486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="campaign_name")
	private String campaignName;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="category")
	private String category;
	
	@Column(name="bid")
	private int bid;
	
	@JsonIgnore
	@JsonSerialize
	@Lazy
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="campaign_product",
			joinColumns=@JoinColumn(name="campaign_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
			)
	private List<Product> products = new ArrayList<>();

	public Campaign() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Campaign(String campaignName, Date startDate, String category, int bid) {
		super();
		this.campaignName = campaignName;
		this.startDate = startDate;
		this.endDate = add10DaysToStartDate(startDate);
		this.category = category;
		this.bid = bid;
	}
	
	private Date add10DaysToStartDate(Date startDate) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 10); //minus number would decrement the days
        return cal.getTime();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product ) {
		products.add(product);
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", campaignName=" + campaignName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", category=" + category + ", bid=" + bid + ", products=" + products + "]";
	}
	
	
	
	

}

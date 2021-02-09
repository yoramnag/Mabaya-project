package com.example.mabaya.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")
@JsonIgnoreProperties(value = { "campaigns" })
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="category")
	private String category;
	
	@Column(name="price")
	private int price;
	
	@Column(name="serial_number" , unique = true)
	private int serialNumber;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="campaign_product",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns=@JoinColumn(name="campaign_id")
			)
	private List<Campaign> campaigns = new ArrayList<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String title, String category, int price, int serialNumber) {
		super();
		this.title = title;
		this.category = category;
		this.price = price;
		this.serialNumber = serialNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", category=" + category + ", price=" + price
				+ ", serialNumber=" + serialNumber + ", campaigns=" + campaigns + "]";
	}
	
	
	
	
	
	

}

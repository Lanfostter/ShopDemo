package com.example.ShopDemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private int id;
	@Column(name = "p_name")
	private String name;
	@Column(name = "p_price")
	private double price;
	@Column(name = "p_importdate")
	private Date importdate;
	@Column(name = "p_expirydate")
	private Date expirydate;
	@Column(name = "p_img")
	private String img;
	@ManyToOne
	@JoinColumn(name ="t_id")
	private Translation translation;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getImportdate() {
		return importdate;
	}
	public void setImportdate(Date importdate) {
		this.importdate = importdate;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	public Translation getTranslation() {
		return translation;
	}
	public void setTranslation(Translation translation) {
		this.translation = translation;
	}
	public ProductEntity(int id, String name, double price, Date importdate, Date expirydate, String img,
			Translation translation) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.importdate = importdate;
		this.expirydate = expirydate;
		this.img = img;
		this.translation = translation;
	}
	public ProductEntity() {
		super();
	}
	
	
	

}

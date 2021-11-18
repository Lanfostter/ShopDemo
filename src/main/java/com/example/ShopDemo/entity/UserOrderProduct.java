package com.example.ShopDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_order_product")
public class UserOrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "p_id")
	private ProductEntity productEntity;
	@ManyToOne
	@JoinColumn(name = "user_order_id")
	private UserOrder order;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "unitprice")
	private double unitprice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductEntity getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	public UserOrder getOrder() {
		return order;
	}
	public void setOrder(UserOrder order) {
		this.order = order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public UserOrderProduct(int id, ProductEntity productEntity, UserOrder order, int quantity, double unitprice) {
		super();
		this.id = id;
		this.productEntity = productEntity;
		this.order = order;
		this.quantity = quantity;
		this.unitprice = unitprice;
	}
	public UserOrderProduct() {
		super();
	}
	
}

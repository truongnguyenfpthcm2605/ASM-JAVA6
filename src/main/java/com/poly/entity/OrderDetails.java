package com.poly.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrdersDetails")
public class OrderDetails implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quanity;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "idProduct", referencedColumnName = "id")
	Product product;
	
	@ManyToOne
	@JoinColumn(name = "idOrders", referencedColumnName = "id")
	Order order;

}

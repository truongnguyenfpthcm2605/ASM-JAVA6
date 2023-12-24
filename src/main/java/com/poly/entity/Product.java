package com.poly.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private Double discount;
	private Double price;
	private Integer ram;
	private Integer rom;
	private Integer pin;
	private String chip;
	private Integer camera;
	private Double screen;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	private Integer quanityfinal;
	private Integer quantitysold;
	private String imgproduct;
	private String thumnail;
	private String description;
	private Boolean isActive;
	private Long views;
	
	@ManyToOne
	@JoinColumn(name = "idGroup",referencedColumnName = "id")
	GroupProduct group;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetails> orderDetails;


	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", discount=" + discount +
				", price=" + price +
				", ram=" + ram +
				", rom=" + rom +
				", pin=" + pin +
				", chip='" + chip + '\'' +
				", camera=" + camera +
				", screen=" + screen +
				", createDate=" + createDate +
				", updateDate=" + updateDate +
				", quanityfinal=" + quanityfinal +
				", quantitysold=" + quantitysold +
				", imgproduct='" + imgproduct + '\'' +
				", thumnail='" + thumnail + '\'' +
				", description='" + description + '\'' +
				", isActive=" + isActive +
				", views=" + views +
				", group=" + group +
				", orderDetails=" + orderDetails +
				'}';
	}
}
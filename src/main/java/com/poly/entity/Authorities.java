package com.poly.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name =  "Authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username","role"})
})
public class Authorities implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "username",referencedColumnName = "email")
	Account account;
	
	@ManyToOne
	@JoinColumn(name = "role",referencedColumnName = "id")
	Roles role;
}

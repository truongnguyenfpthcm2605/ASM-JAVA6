package com.poly.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class Account  implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@Email
	public String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,9}$")
	public String password;
	@NotEmpty
	public String fullname;
	@NotEmpty
	public String address;
	@Pattern(regexp = "^(03|07|08|09)\\d{8}$")
	public String phoneNumbers;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	private String img;
	@NotNull
	public Boolean gender;
	@NotNull
	private Boolean isActive = Boolean.TRUE;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authorities> authorities;
}

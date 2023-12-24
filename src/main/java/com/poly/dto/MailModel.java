package com.poly.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailModel {
	
	private String form = "FoodSystemOnline";
	private String to;
	private String subject;
	private String body;
	private String[] cc ;
	private String[] bcc;
	private List<File> files = new ArrayList<>();
	
	public MailModel(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	
}


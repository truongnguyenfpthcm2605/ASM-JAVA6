package com.poly.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.poly.dto.MailModel;
import com.poly.service.MailerService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerServiceImpl implements MailerService {

	@Autowired
	JavaMailSender sender;

	private List<MailModel> list = new ArrayList<>();

	@Override
	public void send(MailModel mail) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mail.getForm());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
		helper.setReplyTo(mail.getForm());

		String[] cc = mail.getCc();
		String[] bcc = mail.getBcc();
		List<File> files = mail.getFiles();

		if (cc != null && cc.length > 0) {
			helper.setCc(cc);
		}

		if (bcc != null && bcc.length > 0) {
			helper.setBcc(bcc);
		}

		if (files != null) {
			for (File file : files) {
				helper.addAttachment(file.getName(), file);
				
			}
		}

		sender.send(message);

	}

	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		this.send(new MailModel(to, subject, body));

	}

	@Override
	public void queue(MailModel mail) {
		list.add(mail);
	}

	@Override
	public void queue(String to, String subject, String body) {
		this.queue(new MailModel(to, subject, body));

	}

	@Scheduled(fixedDelay = 3000, initialDelay = 3000)
	public void run() {
		while (!list.isEmpty()) {
			MailModel mail = list.remove(0);
			try {
				this.send(mail);
			} catch (Exception e) {
				throw  new RuntimeException(e);
			}

		}
	}
}

package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.model.resenje.Resenje;
import lombok.var;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MailingService {

	@Autowired
	private JavaMailSender mailSender;

	private final Path templatesLocation;

	@Autowired
	public MailingService() {
		templatesLocation = Paths.get("src", "main", "resources", "templates");
	}

	@Async
	public void sendResenjeMail(KorisnikDTO korisnikDTO, Resenje resenje, File attachment) {
		boolean prihvaceno = resenje.getOdluka().isPrihvacen();
		String content = renderTemplate("resenje.html",
				"broj_prijave", resenje.getZahtev().getBrojPrijave(),
				"ime", korisnikDTO.getIme(),
				"prezime", korisnikDTO.getPrezime(),
				"status", prihvaceno ? "prihvaćen" : "odbijen.",
				"razlog", !prihvaceno ? "Razlog odbijanja: <br> " + resenje.getOdluka().getObrazlozenje() + "<br>" : ""
		);
		sendMailWithAttachment("ubreteam2023@gmail.com", "Rešenje zahteva", content, attachment);
	}

	private void sendMailWithAttachment(String to, String subject, String body, File attachment) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setText(body, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.addAttachment(attachment.getName(), attachment);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private void sendMail(String to, String subject, String body) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setText(body, true);
			helper.setTo(to);
			helper.setSubject(subject);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private String renderTemplate(String templateName, String... variables) {
		Map<String, String> variableMap = new HashMap<>();

		List<String> keyValueList = Arrays.stream(variables).collect(Collectors.toList());

		if (keyValueList.size() % 2 != 0)
			throw new IllegalArgumentException();

		for (int i = 0; i < keyValueList.size(); i += 2) {
			variableMap.put(keyValueList.get(i), keyValueList.get(i + 1));
		}

		return renderTemplate(templateName, variableMap);
	}

	private String renderTemplate(String templateName, Map<String, String> variables) {
		File file = templatesLocation.resolve(templateName).toFile();
		String message = null;
		try {
			message = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String target, renderedValue;
		for (var entry : variables.entrySet()) {
			target = "\\{\\{ " + entry.getKey() + " \\}\\}";
			renderedValue = entry.getValue();

			message = message.replaceAll(target, renderedValue);
		}

		return message;
	}


}
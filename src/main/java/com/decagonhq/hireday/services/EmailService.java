package com.decagonhq.hireday.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private JavaMailSender sender;
    private Configuration config;

    @Autowired
    public EmailService(JavaMailSender sender, Configuration config) throws IOException, MessagingException, TemplateException {
        this.sender = sender;
        this.config = config;
    }

    public void send() {

        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String, Object> model = new HashMap<>();
            model.put("firstName", "Odudu");
            model.put("lastName", "Abbasi");

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo("arewacode@gmail.com");
            helper.setText(html, true);
            helper.setSubject("Decagon Hiring Day");
            helper.setFrom("4transcolour@gmail.com");
            sender.send(message);

        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("Error sending email.");
        }
    }

}

package com.example.onlineshop.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Component
public class EmailService {
    @Value("classpath:static/testEmail.html")
    private Resource testEmailResource;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    private JavaMailSender mailSender;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendMessage(String[] to, String subject, String shopName)
    {
        try(Reader reader = new InputStreamReader(testEmailResource.getInputStream(), StandardCharsets.UTF_8)){
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            helper.setFrom("noreply@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);

            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache m = mf.compile(reader, "test-email");
            StringWriter stringWriter = new StringWriter();

            Map<String, Object> context = new HashMap<>();
            context.put("shopName", shopName);
            m.execute(stringWriter, context);

            String html = stringWriter.toString();

            helper.setText(html, true);
            mailSender.send(message);

        } catch (IOException | MessagingException exception){
            log.error(exception.getMessage());
        }
    }
}

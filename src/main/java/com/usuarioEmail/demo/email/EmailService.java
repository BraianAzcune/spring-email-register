package com.usuarioEmail.demo.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

  private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
  private final JavaMailSender javaMailSender;

  @Override
  @Async
  public void send(String to, String emailBody) {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
      mimeMessageHelper.setText(emailBody, true);
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject("DEMOAPP - Confirm your email");
      mimeMessageHelper.setFrom("demo@amigoscode.com");
      javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("Error sending email to {}", to, e);
      throw new IllegalStateException("failed to send email", e);
    }

  }

}

package com.ipn.mx.miniinventario.features.mail.service.Impl;

import com.ipn.mx.miniinventario.features.mail.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailCc;

    @Override
    public void enviarCorreoElectronico(String to, String subject, String text) {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            helper.setFrom("noreply@gmail.com", "Envio de correos via Spring");
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setTo(to);
            helper.setCc(mailCc);

            mailSender.send(mensaje);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

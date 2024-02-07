package com.crece.crece.service;


import com.crece.crece.model.MailStructure;
import com.crece.crece.model.MailTemplate;
import com.crece.crece.model.Novedades;
import com.crece.crece.model.dto.NovedadesDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMail(List<String> mails, MailStructure mailStructure){
        for (String mail : mails) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromMail);
            simpleMailMessage.setSubject(mailStructure.getSubject());
            simpleMailMessage.setText(mailStructure.getMessage());
            simpleMailMessage.setTo(mail);

            mailSender.send(simpleMailMessage);
        }
    }
    public void sendMailAttach(List<String> mails, MailStructure mailStructure,String file) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);


        for (String mail : mails){
            FileSystemResource fileSystemResource = new FileSystemResource(new File(file));
            helper.setFrom(fromMail,"Administración Crece");
            helper.setTo(mail);
            helper.setText(MailTemplate.generateMail(),true);
            helper.setSubject(mailStructure.getSubject());
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),fileSystemResource);
            mailSender.send(mimeMessage);
            System.out.println("mail enviado con attach");

        }
    }
    public void sendMailWithoutAttach(List<String> mails, MailStructure mailStructure, Novedades novedad) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);


        for (String mail : mails){
            helper.setFrom(fromMail,"Administración Crece");
            helper.setTo(mail);
            helper.setText(MailTemplate.generateMail2(novedad),true);
            helper.setSubject(mailStructure.getSubject());
            mailSender.send(mimeMessage);
            System.out.println("mail enviado de novedades");

        }
    }

    public String sendRecoveryEmail(String recipientEmail) {
        try {
            // Generar código OTP
            String otp = generateOTP();

            // Configurar estructura del correo electrónico para recuperación de contraseña
            MailStructure recoveryMail = new MailStructure();
            recoveryMail.setSubject("Recuperación de Contraseña - ADM Crece");
            recoveryMail.setMessage("El siguiente codigo es necesario para poder reestablecer su contraseña.\n Codigo: " + otp);

            // Enviar el correo electrónico solo al usuario que lo solicitó
            sendMail(Collections.singletonList(recipientEmail), recoveryMail);

            // Retornar el valor del OTP para que pueda ser capturado en el controlador
            return otp;
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante el envío del correo
            e.printStackTrace();
            // Manejar el caso de error retornando un valor predeterminado o lanzando una excepción
            throw new RuntimeException("Error al enviar el correo de recuperación");
        }
    }

    private String generateOTP() {
        // Implementa tu lógica para generar un código OTP
        return String.valueOf((int) ((Math.random() * (9999 - 1000)) + 1000));
    }

    public void sendPasswordChangeConfirmation(String recipientEmail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromMail);
            mailMessage.setTo(recipientEmail);
            mailMessage.setSubject("Confirmación de Cambio de Contraseña");
            mailMessage.setText("Tu contraseña ha sido cambiada exitosamente. Si no realizaste esta acción, por favor, contacta al soporte.");

            mailSender.send(mailMessage);
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante el envío del correo
            e.printStackTrace();
        }
    }
}

/*
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMail(String mail, MailStructure mailStructure){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(mailStructure.getSubject());
        simpleMailMessage.setText(mailStructure.getMessage());
        simpleMailMessage.setTo(mail);

        mailSender.send(simpleMailMessage);*/



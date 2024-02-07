package com.crece.crece.controller;

import com.crece.crece.model.MailRequest;
import com.crece.crece.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/mail")
@CrossOrigin("*")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")

    public String sendMail(@RequestBody MailRequest mailRequest){
        mailService.sendMail(mailRequest.getMails(), mailRequest.getMailStructure());
        return "Successfully sent the mail";
    }
    @PostMapping("/sendAttach")
    public String sendMailAttach(@RequestBody MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException {
        mailService.sendMailAttach(mailRequest.getMails(), mailRequest.getMailStructure(), mailRequest.getFilePath());
        return "Successfully sent the mail";
    }

    @GetMapping("/send-recovery-email")
    public ResponseEntity<String> sendRecoveryEmail(@RequestParam String recipientEmail) {
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            return ResponseEntity.badRequest().body("Recipient email is required.");
        }

        try {
            // Obtener el valor del OTP desde el servicio
            String otp = mailService.sendRecoveryEmail(recipientEmail);

            // Retornar el valor del OTP en la respuesta
            return ResponseEntity.ok(otp);
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante el envío del correo
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send recovery email");
        }
    }

    /*
    @PostMapping("/send")
    public String sendMail(@RequestBody List<String> mails, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mails, mailStructure);
        return "Successfully sent the mail";
    */
    /*

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail, mailStructure);
        return "Succesfully sent the mail";*/
    }

package com.emailsender.app.services.Impl;

import com.emailsender.app.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailsender;
  //  private Logger logger= (Logger) LoggerFactory.getLogger(EmailServiceImpl.class);
    public EmailServiceImpl(JavaMailSender mailsender)
    {
        this.mailsender=mailsender;
    }
    @Override
    public void sendEmail(String to, String subject, String message)
    {
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("kkumaribest7@gmail.com");
        mailsender.send(simpleMailMessage);
      //  logger.info("Email has been sent..");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
    SimpleMailMessage simpleMessage=new SimpleMailMessage();
    simpleMessage.setTo(to);
    simpleMessage.setSubject(subject);
    simpleMessage.setText(message);
    simpleMessage.setFrom("kkumaribest7@gmail.com");
    mailsender.send(simpleMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent)
    {
        MimeMessage simpleMsg=mailsender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMsg, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent,true);
            helper.setFrom("kkumaribest7@gmail.com");
            mailsender.send(simpleMsg);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
    MimeMessage smMsg=mailsender.createMimeMessage();
    try{
        MimeMessageHelper helpper = new MimeMessageHelper(smMsg, true, "UTF-8");
        helpper.setTo(to);
        helpper.setSubject(subject);
        helpper.setText(message,true);
        FileSystemResource fs=new FileSystemResource(file);
        helpper.addAttachment(fs.getFilename(),file);
        helpper.setFrom("kkumaribest7@gmail.com");
        mailsender.send(smMsg);
    }
    catch(MessagingException e)
    {
        throw new RuntimeException(e);
    }
        }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream file)
    {
        MimeMessage mSmg=mailsender.createMimeMessage();
        try {
            MimeMessageHelper help = new MimeMessageHelper(mSmg,true);
            help.setTo(to);
            help.setSubject(subject);
            help.setText(message);
            File files=new File("test.png");
            Files.copy(file,files.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fs=new FileSystemResource(files);
            help.addAttachment(fs.getFilename(),files);
            help.setFrom("kkumaribest7@gmail.com");
            mailsender.send(mSmg);
        }
        catch(MessagingException ex)
        {
            throw new RuntimeException(ex);
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }


    }

}

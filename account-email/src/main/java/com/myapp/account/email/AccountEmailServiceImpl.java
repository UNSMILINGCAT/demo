package com.myapp.account.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class AccountEmailServiceImpl implements AccountEmailService
{
    private JavaMailSender javaMailSender;
    private String systemEmail;

    public void sendMail(String to, String subject, String htmlText) throws AccountEmailException
    {
        try
        {
//            MimeMessage msg = javaMailSender.createMimeMessage();
//            MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
//            msgHelper.setFrom(systemEmail);
//            msgHelper.setTo(to);
//            msgHelper.setSubject(subject);
//            msgHelper.setText(htmlText, true);
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom(systemEmail);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(htmlText);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception me)
        {
            throw new AccountEmailException("发送邮件失败:", me);
        }
    }

    public JavaMailSender getJavaMailSender()
    {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail()
    {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail)
    {
        this.systemEmail = systemEmail;
    }
}

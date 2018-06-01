package com.myapp.account.email;

public interface AccountEmailService
{
    public void sendMail(String to,String subject,String htmlText) throws AccountEmailException;
}

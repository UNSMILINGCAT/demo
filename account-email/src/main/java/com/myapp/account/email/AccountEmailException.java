package com.myapp.account.email;

public class AccountEmailException extends Exception
{
    public AccountEmailException(String message, Exception e)
    {
        System.out.println(message + " " + e.getMessage());
    }
}

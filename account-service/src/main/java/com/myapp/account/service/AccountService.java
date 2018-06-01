package com.myapp.account.service;

public interface AccountService
{
    String generateCaptchaKey() throws Exception;
    byte[] generateCaptchaImage(String captchaKey) throws Exception;
    void signUp(SignUpRequest signUpRequest) throws Exception;
    void activate(String activationNumber) throws Exception;
    void login(String id,String password) throws Exception;
}

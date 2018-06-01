package com.myapp.account.service;

import com.myapp.account.captcha.AccountCaptchaService;
import com.myapp.account.captcha.RandomGenerator;
import com.myapp.account.email.AccountEmailService;
import com.myapp.account.persist.Account;
import com.myapp.account.persist.AccountPersistService;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService
{

    private AccountPersistService accountPersistService;
    private AccountEmailService accountEmailService;
    private AccountCaptchaService accountCaptchaService;

    public AccountPersistService getAccountPersistService()
    {
        return accountPersistService;
    }

    public void setAccountPersistService(AccountPersistService accountPersistService)
    {
        this.accountPersistService = accountPersistService;
    }

    public String generateCaptchaKey()
    {
        try
        {
            return accountCaptchaService.generateCaptchaKey();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] generateCaptchaImage(String captchaKey)
    {
        try
        {
            return accountCaptchaService.generateCaptchaImage(captchaKey);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> activationMap = new HashMap<String, String>();

    public void signUp(SignUpRequest signUpRequest) throws Exception
    {
        try
        {
            if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword()))
            {
                throw new Exception();
            }
            if (!accountCaptchaService.validateCaptcha(signUpRequest.getCaptchaKey(), signUpRequest.getCaptchaValue()))
            {
                throw new Exception();
            }
            Account account = new Account();
            account.setId(signUpRequest.getId());
            account.setEmail(signUpRequest.getEmail());
            account.setName(signUpRequest.getName());
            account.setPassword(signUpRequest.getPassword());
            account.setActivated(false);
            accountPersistService.createAccount(account);
            String activationId = RandomGenerator.getRandomString();
            activationMap.put(activationId, account.getId());
            String link = signUpRequest.getActivateServiceUrl().endsWith("/") ? signUpRequest.getActivateServiceUrl()
                    + activationId : signUpRequest.getActivateServiceUrl() + "?key = " + activationId;
            accountEmailService.sendMail(account.getEmail(), "please Activate your account", link);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void activate(String activationNumber)
    {

    }

    public void login(String id, String password)
    {

    }
}

package com.myapp.account.captcha;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountCaptchaServiceTest
{
    private AccountCaptchaService service;

    @Before
    public void prepare()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-captcha.xml");
        service = (AccountCaptchaService) ctx.getBean("accountCaptchaService");
    }

    @Test
    public void testGenerateCaptcha() throws Exception
    {
        List<String> list=new ArrayList<String>();
        list.add("12345");
        list.add("4568");
        service.setPreDefindTexts(list);
        String captchaKey = service.generateCaptchaKey();
        byte[] captchaImage = service.generateCaptchaImage(captchaKey);
        File image = new File("target/" + captchaKey + ".jpg");
        OutputStream output = null;
        try
        {
            output = new FileOutputStream(image);
            output.write(captchaImage);
        } finally
        {
            output.close();
        }
    }

    @Test
    public void testValidateCaptchaCorrect() throws Exception
    {
        List<String> preDefinedTexts = new ArrayList<String>();
        preDefinedTexts.add("12345");
        preDefinedTexts.add("abcde");
        service.setPreDefindTexts(preDefinedTexts);
        String captchaKey = service.generateCaptchaKey();
        service.generateCaptchaImage(captchaKey);
//        assertFalse(service.validateCaptcha(captchaKey, "12345"));
        captchaKey = service.generateCaptchaKey();
        service.generateCaptchaImage(captchaKey);
        System.out.println(service.validateCaptcha(captchaKey, "abcde"));
    }
}

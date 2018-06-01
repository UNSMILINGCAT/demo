package com.myapp.account.captcha;

import java.util.List;

public interface AccountCaptchaService
{
    String generateCaptchaKey();

    byte[] generateCaptchaImage(String captchaKey)throws Exception;

    boolean validateCaptcha(String captchaKey, String captchaValue) throws Exception;

    List<String> getPreDefindTexts();

    void setPreDefindTexts(List<String> preDefindTexts);

}

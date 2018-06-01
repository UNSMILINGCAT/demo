package com.myapp.account.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.InitializingBean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AccountCaptchaServiceImpl implements AccountCaptchaService, InitializingBean
{
    private DefaultKaptcha producer;
    private Map<String, String> captchaMap = new HashMap<String, String>();
    private List<String> preDefinedTexts;
    private int textCount = 0;

    public void afterPropertiesSet()
    {
        producer = new DefaultKaptcha();
        producer.setConfig(new Config(new Properties()));
    }

    public String generateCaptchaKey()
    {
        String key = RandomGenerator.getRandomString();
        String value = getCaptchaText();
        captchaMap.put(key, value);
        return key;
    }

    public byte[] generateCaptchaImage(String captchaKey) throws Exception
    {
        String text = captchaMap.get(captchaKey);
        if (text == null)
        {
            throw new Exception();
        }
        BufferedImage image = producer.createImage(text);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", out);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public boolean validateCaptcha(String captchaKey, String captchaValue) throws Exception
    {
        String text = captchaMap.get(captchaKey);
        if (text == null)
        {
            throw new Exception();
        }
        if (text.equals(captchaValue))
        {
            captchaMap.remove(captchaKey);
            return true;
        } else
        {
            return false;
        }
    }

    public List<String> getPreDefindTexts()
    {
        return preDefinedTexts;
    }

    public void setPreDefindTexts(List<String> preDefindTexts)
    {
        this.preDefinedTexts = preDefindTexts;
    }

    private String getCaptchaText()
    {
        if (preDefinedTexts != null && !preDefinedTexts.isEmpty())
        {
            String text = preDefinedTexts.get(textCount);
            textCount = (textCount + 1) % preDefinedTexts.size();
            return text;
        } else
        {
            return producer.createText();
        }
    }
}

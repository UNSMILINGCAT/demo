package com.myapp.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

public class AccountEmailServiceTest
{
    private GreenMail greenMail;

    @Before
    public void startMailServer()
    {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("3418670057@123.com", "123456");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
        String subject = "测试";
        String htmlText = "测试惊不惊喜意不意外？";
        accountEmailService.sendMail("3418670057@qq.com", subject, htmlText);
        System.out.println(((AccountEmailServiceImpl)accountEmailService).getSystemEmail());
        greenMail.waitForIncomingEmail(2000, 1);
        Message[] msgs = greenMail.getReceivedMessages();
        System.out.println(msgs.length);
//        Assert.assertEquals(1, msgs.length);
//        Assert.assertEquals(subject, msgs[0].getSubject());
//        Assert.assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @After
    public void stopMailServer()
    {
        greenMail.stop();
    }
}

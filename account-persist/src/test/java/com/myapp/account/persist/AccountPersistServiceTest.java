package com.myapp.account.persist;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class AccountPersistServiceTest
{
    private AccountPersistService service;

    @Before
    public void prepare() throws Exception
    {
        File persistDataFile = new File("target/test-classes/persist-data.xml");
        if (persistDataFile.exists())
        {
            persistDataFile.delete();
        }
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-persist.xml");
        service = (AccountPersistService) ctx.getBean("accountPersistService");
        Account account = new Account();
        account.setId("juven");
        account.setName("Juven Xu");
        account.setEmail("sngig");
        account.setPassword("hiseng");
        account.setActivated(true);
        service.createAccount(account);
    }
}

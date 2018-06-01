package com.myapp.account.captcha;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class RandomGeneratorTest
{
    @Test
    public void testGetRandomString()
    {
        Set<String> randoms=new HashSet<String>(100);
        for(int i=0;i<100;i++){
            String random=RandomGenerator.getRandomString();

        }
    }
}

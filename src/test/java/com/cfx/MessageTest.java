package com.cfx;

import com.cfx.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * @Author: chenfuxian
 * @Date: 2022/1/15 11:22
 */
@SpringBootTest(classes = Application.class)
public class MessageTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void loadMessage(){
        
    }
}

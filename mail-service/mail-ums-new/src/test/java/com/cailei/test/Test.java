package com.cailei.test;


import com.cailei.mail.MailUMSApplication;
import com.cailei.mail.entity.MailMember;
import com.cailei.mail.mapper.MailMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MailUMSApplication.class)
public class Test {

    @Autowired
    MailMemberMapper mailMemberMapper;

    @org.junit.jupiter.api.Test
    public void testInsert(){

        MailMember mailMember = new MailMember();
        mailMember.setUsername("cailei5");
        mailMember.setStatus(0);
        mailMember.setPassword("123456");
        mailMember.setEmail("1234656756@qq.com");
        mailMemberMapper.insert(mailMember);
    }
}

package com.cailei.mail.portal.controller;


import com.cailei.base.result.ResultWarpper;
import com.cailei.mail.dto.ReceiveMember;
import com.cailei.mail.entity.MailMember;
import com.cailei.mail.service.MailMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    MailMemberService memberService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @ResponseBody
    @GetMapping("/hello")
    public String testUser(){
        return "hello world";
    }




    @PostMapping("/register")
    public ResultWarpper register(@RequestBody @Valid ReceiveMember receiveMember){



//        String msg = ;

        return memberService.register(receiveMember);
    }



    @PostMapping("/login")
    public ResultWarpper login(@RequestBody ReceiveMember receiveMember){


        return memberService.login(receiveMember);
    }


}

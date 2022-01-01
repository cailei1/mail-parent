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
    public ResultWarpper<String> register(@RequestBody @Valid ReceiveMember receiveMember){

        // 1. 注册功能  防止用户名重复
        if(receiveMember!=null && !receiveMember.getUsername().isEmpty()){
//            memberService.searchByUserName(receiveMember.getUsername())
            List<MailMember> mailMembers = memberService.searchByUserName(receiveMember.getUsername());
            if(mailMembers!=null && mailMembers.size()>0) {
                return ResultWarpper.getFailedResultWapper().data("改用户名已被占用，请重新起名").build();
            }

        }

        memberService.register(receiveMember);

        return ResultWarpper.getSuccessResultWapper().data("注册成功").build();
    }



    @PostMapping("/login")
    public String login(@RequestBody ReceiveMember receiveMember){
        // 登录功能
        if(receiveMember.getUsername() == null){
            return "请检查用户名";
        }

        // 1. 验证用户名和密码是否正确
        List<MailMember> mailMembers = memberService.searchByUserName(receiveMember.getUsername());
        if(mailMembers!=null && mailMembers.size()>0){
            // 验证密码
            boolean matches = passwordEncoder.matches(receiveMember.getPassword(), mailMembers.get(0).getPassword());

            if(matches){
                return "登录成功";
            }else{
                return "请检查密码是否正确";
            }
        }else{
            return "请检查用户名是否正确";
        }
    }


}

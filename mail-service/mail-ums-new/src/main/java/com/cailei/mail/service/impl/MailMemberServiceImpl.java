package com.cailei.mail.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cailei.base.result.ResultWarpper;
import com.cailei.common.utils.JwtUtils;
import com.cailei.mail.dto.ReceiveMember;
import com.cailei.mail.entity.MailMember;
import com.cailei.mail.mapper.MailMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cailei.mail.service.MailMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailei
 * @since 2021-12-30
 */
@Service
public class MailMemberServiceImpl extends ServiceImpl<MailMemberMapper, MailMember> implements MailMemberService {


    @Autowired
    MailMemberMapper mailMemberMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResultWarpper register(ReceiveMember receiveMember) {
        // 1. 注册功能  防止用户名重复
        if(receiveMember!=null && !receiveMember.getUsername().isEmpty()){
//            memberService.searchByUserName(receiveMember.getUsername())
            List<MailMember> mailMembers = searchByUserName(receiveMember.getUsername());
            if(mailMembers!=null && mailMembers.size()>0) {
                return ResultWarpper.getFailedResultWapper().data("改用户名已被占用，请重新起名").build();
            }

        }

        MailMember mailMember = new MailMember();
        // 密码脱敏 设置

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder()
        String encodedPassword = passwordEncoder.encode(receiveMember.getPassword());

        receiveMember.setPassword(encodedPassword);
        BeanUtils.copyProperties(receiveMember,mailMember);

        mailMemberMapper.insert(mailMember);

        return ResultWarpper.getSuccessResultWapper().data("注册成功").build();
    }



    @Override
    public List<MailMember> searchByUserName(String username) {
        List<MailMember> mailMembers = mailMemberMapper.selectByName(username);

        if(mailMembers!=null&&mailMembers.size()>0){
            return mailMembers;
        }

        return null;
    }

    @Override
    public ResultWarpper login(ReceiveMember receiveMember) {
        // 登录功能
        if(receiveMember.getUsername() == null){
            return ResultWarpper.getFailedResultWapper().data("请检查用户名").build();
        }

        // 1. 验证用户名和密码是否正确
        List<MailMember> mailMembers = searchByUserName(receiveMember.getUsername());
        if(mailMembers!=null && mailMembers.size()>0){
            // 验证密码
            boolean matches = passwordEncoder.matches(receiveMember.getPassword(), mailMembers.get(0).getPassword());

            if(!matches){
                return ResultWarpper.getFailedResultWapper().data("请检查密码是否正确").build();
            }
        }else{
            return ResultWarpper.getFailedResultWapper().data("请检查用户名是否正确").build();
        }

        String token = JwtUtils.createToken(receiveMember.getUsername());

        return ResultWarpper.getFailedResultWapper().data(token).build();


    }
}

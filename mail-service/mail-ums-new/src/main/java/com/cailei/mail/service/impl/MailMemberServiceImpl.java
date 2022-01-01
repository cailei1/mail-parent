package com.cailei.mail.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
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
    public String register(ReceiveMember receiveMember) {
        MailMember mailMember = new MailMember();
//        mailMember.setId(15L);
//        mailMember.setUsername("xiaocai");
//        mailMember.setNickName("caicai");
//        mailMember.setEmail("111");
//        mailMember.setStatus(0);
        // 密码脱敏 设置

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder()
        String encodedPassword = passwordEncoder.encode(receiveMember.getPassword());

        receiveMember.setPassword(encodedPassword);
        BeanUtils.copyProperties(receiveMember,mailMember);

        mailMemberMapper.insert(mailMember);

        return "success insert";
    }

    @Override
    public List<MailMember> searchByUserName(String username) {
        List<MailMember> mailMembers = mailMemberMapper.selectByName(username);

        if(mailMembers!=null&&mailMembers.size()>0){
            return mailMembers;
        }

        return null;
    }
}

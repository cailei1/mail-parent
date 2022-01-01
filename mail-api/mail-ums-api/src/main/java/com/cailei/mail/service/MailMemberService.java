package com.cailei.mail.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cailei.mail.dto.ReceiveMember;
import com.cailei.mail.entity.MailMember;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailei
 * @since 2021-12-30
 */
public interface MailMemberService extends IService<MailMember> {
      String register(ReceiveMember receiveMember);

      List<MailMember> searchByUserName(String username);
}

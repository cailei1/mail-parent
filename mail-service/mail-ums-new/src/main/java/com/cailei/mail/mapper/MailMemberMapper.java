package com.cailei.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cailei.mail.entity.MailMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cailei
 * @since 2021-12-30
 */

public interface MailMemberMapper extends BaseMapper<MailMember> {

    List<MailMember> selectByName(String username);

}

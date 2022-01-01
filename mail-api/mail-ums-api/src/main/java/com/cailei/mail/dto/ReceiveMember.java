package com.cailei.mail.dto;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ReceiveMember {


    @NotEmpty(message = "用户名为空")
    private String username;
    private String password;

    private String email;

    private String nickName;
}

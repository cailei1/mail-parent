package com.cailei.base.result;

import com.cailei.base.enums.StateCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultWarpper<T> implements Serializable {

    // 状态码
    private int code;
    // message
    private String msg;
    private T data;


    public static ResultWarpper.ResultWarpperBuilder getSuccessResultWapper(){
        return ResultWarpper.builder().code(StateCodeEnum.SUCCESS.getCode()).msg(StateCodeEnum.SUCCESS.getMsg());
    }


    public static ResultWarpper.ResultWarpperBuilder getFailedResultWapper(){
        return ResultWarpper.builder().code(StateCodeEnum.FAILED.getCode()).msg(StateCodeEnum.FAILED.getMsg());
    }

}

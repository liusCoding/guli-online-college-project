package com.liuscoding.commonutils.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举
 * @author liusCoding
 */
@AllArgsConstructor
@Getter
public enum ResultCode implements IResultCode {

    SUCCESS(20000,"操作成功"),

    ERROR(20001,"响应失败")
    ;

    private Integer code;

    private String msg;
}

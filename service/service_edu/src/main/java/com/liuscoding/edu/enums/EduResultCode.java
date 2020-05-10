package com.liuscoding.edu.enums;

import com.liuscoding.commonutils.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举
 * @author liusCoding
 */
@AllArgsConstructor
@Getter
public enum EduResultCode implements IResultCode {

    SUCCESS(20000,"操作成功"),

    ERROR(20001,"响应失败"),

    FILE_IS_EMPTY(20002,"请上传文件！！！"),

    ;

    private Integer code;

    private String msg;
}

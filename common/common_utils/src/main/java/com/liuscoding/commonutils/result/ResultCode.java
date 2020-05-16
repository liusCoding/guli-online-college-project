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

    ERROR(20001,"响应失败"),

    UPLOAD_FILE_ERROR(20010,"文件上传失败"),

    FILE_EMPTY(20011,"文件为空"),

    DELETE_ERROR(20012,"删除失败"),

    VALID_CODE_SEND_FAIL(20013,"短信验证码发送失败"),
    ;

    private Integer code;

    private String msg;
}

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

    LOGIN_ERROR(20014,"登录失败，用户名或密码错误！"),

    REGISTER_ERROR(20015,"注册失败"),

    GET_PLAYAUTH_FAIL(20016,"获取视频凭证失败"),

    REMOTE_CALL_COURSE(20017,"远程调用查询课程信息失败"),

    REMOTE_CALL_MEMBER(20018,"远程调用会员模块失败"),

    WXPAY_FAIL(20019,"微信支付失败"),

    PARAMS_ERROR(20020,"参数校验失败")
    ;

    private Integer code;

    private String msg;
}

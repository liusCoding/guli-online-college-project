package com.liuscoding.ucenter.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: MemberForm
 * @description: 会员表单对象
 * @author: liusCoding
 * @create: 2020-05-16 16:48
 */

@Data
public class MemberForm implements Serializable {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}

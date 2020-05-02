package com.liuscoding.commonutils.result;

/**
 * 响应码和响应信息定义
 * @author  liuscoding
 */
public interface IResultCode {

    /**
     *  获取响应码
     * @return 响应码
     */
    Integer getCode();


    /**
     * 获取响应信息
     *
     * @return 响应信息
     */
    String getMsg();
}

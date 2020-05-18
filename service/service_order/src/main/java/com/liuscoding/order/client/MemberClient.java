package com.liuscoding.order.client;

import com.liuscoding.commonutils.model.vo.MemberOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-ucenter",fallback = MemberClientImpl.class)
@Component
public interface MemberClient {



    /**
     * 根据会员id  查询会员信息
     * @param  id 会员id
     * @return MemberOrderVo 会员信息
     */
     @GetMapping("/ucenter/member/getMemberInfoOrder/{id}")
     MemberOrderVo getMemberInfoOrderById(@PathVariable("id") String id);
}

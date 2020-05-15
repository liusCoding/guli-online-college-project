package com.liuscoding.cms.controller;


import com.liuscoding.cms.entity.CrmBanner;
import com.liuscoding.cms.service.CrmBannerService;
import com.liuscoding.commonutils.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-15
 */
@RestController
@Api(tags = "Banner前端管理")
@RequestMapping("/cms/bannerFront")
@CrossOrigin
public class BannerFrontController {
    private final CrmBannerService crmBannerService;

    public BannerFrontController(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }

    @ApiOperation("查询所有的Banner")
    @GetMapping("/getAllBanner")
    public ResultVo getAllBanner(){
        List<CrmBanner> bannerList = crmBannerService.selectAllBanner();
        return ResultVo.ok().data("list",bannerList);
    }
}


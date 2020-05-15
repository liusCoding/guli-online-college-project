package com.liuscoding.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuscoding.cms.entity.CrmBanner;
import com.liuscoding.cms.service.CrmBannerService;
import com.liuscoding.commonutils.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-15
 */
@RestController
@Api(tags = "Banner后台管理")
@RequestMapping("/cms/crmAdmin")
@CrossOrigin
public class BannerAdminController {
    private final CrmBannerService crmBannerService;

    public BannerAdminController(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }

    @GetMapping("/pageBanner/{page}/{size}")
    @ApiOperation("分页查询Banner")
    public ResultVo pageBanner(@PathVariable("page") Integer page,@PathVariable("size") Long size){
        Page<CrmBanner> pageBanner = new Page<>(page, size);
        crmBannerService.page(pageBanner, null);
        return ResultVo.ok().data("item",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    @ApiOperation("添加Banner")
    @PostMapping("/addBanner")
    public ResultVo addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return ResultVo.ok();
    }

    @ApiOperation("修改Banner")
    @PutMapping("/updateBanner")
    public ResultVo updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return ResultVo.ok();
    }

    @ApiOperation("删除Banner")
    @DeleteMapping("/remove/{id}")
    public ResultVo removeBanner(@PathVariable String id){
        crmBannerService.removeById(id);
        return ResultVo.ok();
    }


}


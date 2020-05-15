package com.liuscoding.cms.service;

import com.liuscoding.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-15
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询所有Banner
     * @return
     */
    List<CrmBanner> selectAllBanner();
}

package com.liuscoding.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuscoding.cms.entity.CrmBanner;
import com.liuscoding.cms.mapper.CrmBannerMapper;
import com.liuscoding.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author liusCoding
 * @since 2020-05-15
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 查询所有Banner
     *
     * @return
     */
    @Override
    @Cacheable(key = "'selectIndexList'",value = "banner")
    public List<CrmBanner> selectAllBanner() {

        //1.根据id进行降序排列，显示排列之后前两条记录
        LambdaQueryWrapper<CrmBanner> bannerQuery = new LambdaQueryWrapper<>();
        bannerQuery.orderByDesc(CrmBanner::getId);
        bannerQuery.last("limit 3");
        return list(null);
    }
}

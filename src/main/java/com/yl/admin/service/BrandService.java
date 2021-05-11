package com.yl.admin.service;

import com.yl.admin.domain.TBrand;
import com.yl.admin.dto.BrandDto;

import java.util.List;
import java.util.Map;

/**
 * Description:品牌管理的逻辑层
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
public interface BrandService  {
    /**
     * 查询所有品牌
     */
    List<TBrand> getAllBrand();
    /**
     * 添加或者更新品牌
     */
    void addOrUpdateBrand(BrandDto brand);
    /**
     * 删除
     */
    void deleteBrand(Long id);
    /**
     * 通过名字模糊查询
     */
    Map<String,Object> getBrandByNameLikePage(String name, Long page, Long pageSize);
    /**
     * 获取一个类型下面的所有品牌
     */
    List<TBrand> getBrandBySubType(Long subType);
}

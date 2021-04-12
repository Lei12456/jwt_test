package com.yl.admin.service;

import com.yl.admin.domain.TBrand;

import java.util.List;

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
     * 分页查询所有品牌
     */
    List<TBrand> getBrandByPage(Long page,Long pageSize);
    /**
     * 添加或者更新品牌
     */
    void addOrUpdateBrand(TBrand brand);
    /**
     * 删除
     */
    void deleteBrand(Long id);
    /**
     * 通过名字模糊查询
     */
    List<TBrand> getBrandByNameLike(String name,Long page,Long pageSize);
}

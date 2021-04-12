package com.yl.admin.service;

import cn.hutool.json.JSONObject;
import com.yl.admin.domain.TProductSubType;
import com.yl.admin.domain.TProductType;

import java.util.List;

/**
 * Description:商品分类的逻辑层
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
public interface ProductSubTypeService {

    /**
     * 查询所有商品大分类
     */
    List<TProductType> getAllType();
    /**
     * 查询所有商品小分类
     */
    List<TProductSubType> getAllSubType();
    /**
     *  查询所有商品分类子类
     */
    List<TProductSubType> getProductSubTypeByTypeId(Long typeId);
    /**
     * 分页查询子类型
     */
    List<TProductSubType> getSubTypePage(Integer page, Integer pageSize);
    /**
     *分页查询父类型
     */
    List<TProductType> getTypePage(Integer page, Integer pageSize);
    /**
     * 添加
     */
    void addOrUpdateType(JSONObject type);

    void addOrUpdateSubType(JSONObject subType);

    /**
     * 删除
     */
    void deleteType(Long id);

    void deleteSubType(Long id);
}

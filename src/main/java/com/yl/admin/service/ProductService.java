package com.yl.admin.service;

import cn.hutool.json.JSONObject;
import com.yl.admin.domain.TProduct;
import com.yl.admin.dto.ProductDto;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
public interface ProductService {
    /**
     * 查询所有的商品列表
     */
    List<TProduct> getAllProduct();
    /**
     *通过商品名称模糊搜索
     */
    List<TProduct> getProductByNameLike(String keywordName);
    /**
     * 通过商品分类筛选
     */
    List<TProduct> getProductByType(String subType);
    /**
     * 通过状态搜索
     */
    List<TProduct> getProductByStatus(Integer status);
    /**
     * 查询单个商品的详情
     */
    TProduct getProductById(Long id);
    /**
     * 添加或者更新商品
     */
    void addOrUpdateProduct(ProductDto productDto);
    /**
     * 更新商品的审核状态.0代表未审核,1代表已审核,
     */
    void updateProductById(Long id,Integer status,Integer flag);
    /**
     * 分页查询文章
     */
    List<TProduct> getProductPage(Integer currentPage,Long pageSize);
    /**
     * 根据品牌和类别查询
     */
    List<TProduct> getProductByFilter(JSONObject jsonObject);
    /**
     * 删除商品
     */
    void deleteProduct(List<Long> ids);
}

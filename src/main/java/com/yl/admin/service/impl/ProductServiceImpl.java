package com.yl.admin.service.impl;

import cn.hutool.json.JSONObject;
import com.yl.admin.dao.BrandRepository;
import com.yl.admin.dao.ProductQueryRepository;
import com.yl.admin.dao.ProductRepository;
import com.yl.admin.dao.ProductSubTypeRepository;
import com.yl.admin.domain.TBrand;
import com.yl.admin.domain.TProduct;
import com.yl.admin.domain.TProductSubType;
import com.yl.admin.service.ProductService;
import com.yl.admin.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description:商品管理的逻辑层
 *
 * @author YangLei
 * @date Created on 2021/4/3
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubTypeRepository productSubTypeRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductQueryRepository productQueryRepository;

    @Override
    public List<TProduct> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public List<TProduct> getProductByNameLike(String keywordName) {
        return null;
    }

    @Override
    public List<TProduct> getProductByType(String subType) {
        return null;
    }

    @Override
    public List<TProduct> getProductByStatus(Integer status) {
        return null;
    }

    @Override
    public TProduct getProductById(Long id) {
        return productRepository.getTProductById(id);
    }

    @Override
    public void addOrUpdateProduct(ProductDto productDto) {
        TProduct tProduct = new TProduct();
        boolean simpleProperty = BeanUtils.isSimpleProperty(productDto.getClass());
        BeanUtils.copyProperties(productDto,tProduct);
        if (productDto.getSubTypeId() != null && productDto.getBrandId() != null){
            TProductSubType subType = productSubTypeRepository.getOne(productDto.getSubTypeId());
            TBrand brand = brandRepository.getOne(productDto.getBrandId());
            tProduct.setProductSubType(subType);
            tProduct.setBrand(brand);
            productRepository.save(tProduct);
        }
    }

    @Override
    public void updateProductById(Long id, Integer status,Integer flag) {
        productRepository.updateProductStatusById(id,status,flag);
    }

    @Override
    public List<TProduct> getProductPage(Integer currentPage, Long pageSize) {
        Long page = (currentPage - 1) * pageSize;
        List<TProduct> productsPage = productRepository.getProductPage(page, pageSize);
        return productsPage;
    }

    @Override
    public List<TProduct> getProductByFilter(JSONObject jsonObject) {
        JSONObject pageParams = jsonObject.getJSONObject("pageParams");
        JSONObject filterParams = jsonObject.getJSONObject("filterParams");
        Integer currentPage = (Integer)pageParams.get("currentPage");
        Integer pageSize = (Integer)pageParams.get("pageSize");
        Long brandSearch = Long.parseLong(filterParams.get("brandSearch").toString() == "" ? "0" : filterParams.get("brandSearch").toString());
        Long subTypeSearch = Long.parseLong(filterParams.get("subTypeSearch").toString() == "" ? "0" : filterParams.get("subTypeSearch").toString());
        Object nameSearch = filterParams.get("nameSearch");
        return productQueryRepository.getProductByFilter(brandSearch,subTypeSearch,nameSearch.toString(),currentPage,pageSize);
    }

    @Override
    public void deleteProduct(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            for (Long id : ids) {
                productRepository.deleteById(id);
            }
        }
    }
}

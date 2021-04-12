package com.yl.admin.service.impl;

import cn.hutool.json.JSONObject;
import com.yl.admin.dao.ProductSubTypeRepository;
import com.yl.admin.dao.ProductTypeRepository;
import com.yl.admin.domain.TProductSubType;
import com.yl.admin.domain.TProductType;
import com.yl.admin.service.ProductSubTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
@Service
public class ProductSubTypeServiceImpl implements ProductSubTypeService {

    @Autowired
    private ProductSubTypeRepository productSubTypeRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<TProductType> getAllType() {
        return productTypeRepository.getAllProductType();
    }

    @Override
    public List<TProductSubType> getAllSubType() {
        return productSubTypeRepository.getAllProductSubType();
    }

    @Override
    public List<TProductSubType> getProductSubTypeByTypeId(Long typeId) {
        List<TProductSubType> productSubTypes = null;
        // if (typeId != null){
        //      productSubTypes = productSubTypeRepository.getTProductSubTypeByTypeId(typeId);
        // }
        return null;
    }

    @Override
    public List<TProductSubType> getSubTypePage(Integer page, Integer pageSize) {
        return productSubTypeRepository.getSubTypePage((page - 1) * pageSize,pageSize);
    }

    @Override
    public List<TProductType> getTypePage(Integer page, Integer pageSize) {
        return productTypeRepository.getTypePage((page - 1) * pageSize,pageSize);
    }

    @Override
    public void addOrUpdateType(JSONObject type) {
        TProductType tProductType = new TProductType();
        String id = type.get("id") != null ? type.get("id").toString():null;
        String name = type.get("name") != null ? type.get("name").toString():null;
        if(id == null){
            tProductType.setId(null);
        }else {
            tProductType.setId(Long.parseLong(id));
        }
        tProductType.setName(name);
        productTypeRepository.save(tProductType);
    }

    @Override
    public void addOrUpdateSubType(JSONObject subType) {
        TProductSubType tProductSubType = new TProductSubType();
        String id = subType.get("id") != null ? subType.get("id").toString():null;
        String name = subType.get("subName") != null ? subType.get("subName").toString():null;
        String typeId = subType.get("typeId") != null ? subType.get("typeId").toString():null;
        TProductType type = productTypeRepository.getOne(Long.parseLong(typeId));
        if(id == null){
            tProductSubType.setId(null);
        }else {
            tProductSubType.setId(Long.parseLong(id));
        }
        tProductSubType.setSubName(name);
        tProductSubType.setProductType(type);
        productSubTypeRepository.save(tProductSubType);
    }

    @Override
    public void deleteType(Long id) {
        if (id != null){
            productTypeRepository.deleteById(id);
        }
    }

    @Override
    public void deleteSubType(Long id) {
        if(id != null){
            productSubTypeRepository.deleteById(id);
        }
    }
}

package com.yl.admin.service.impl;

import com.yl.admin.dao.BrandRepository;
import com.yl.admin.dao.SubTypeBrandRelaRepository;
import com.yl.admin.domain.TBrand;
import com.yl.admin.domain.TSubTypeBrandRelation;
import com.yl.admin.dto.BrandDto;
import com.yl.admin.service.BrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SubTypeBrandRelaRepository subTypeBrandRelaRepository;

    @Override
    public List<TBrand> getAllBrand() {
        List<TBrand> brands = brandRepository.getAllBrand();
        if (CollectionUtils.isEmpty(brands)){
            return null;
        }
        return brands;
    }

    @Override
    public void addOrUpdateBrand(BrandDto brand) {
        TBrand tBrand = new TBrand();
        TSubTypeBrandRelation subTypeBrandRelation = new TSubTypeBrandRelation();
        BeanUtils.copyProperties(brand,tBrand);
        brandRepository.save(tBrand);
        TBrand lastBrand = brandRepository.getLastBrand();
        subTypeBrandRelation.setBrandId(lastBrand.getId());
        subTypeBrandRelation.setSubTypeId(brand.getSubTypeId());
        if(brand.getId() == null){
            subTypeBrandRelaRepository.save(subTypeBrandRelation);
        }

    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Map<String,Object> getBrandByNameLikePage(String name,Long page,Long pageSize) {
        Map<String,Object> map = new HashMap<>();
        Long currentPage = (page -1) * pageSize;
        if(name == "" || name == null){
            List<TBrand> brandPage = brandRepository.getBrandPage(currentPage, pageSize);
            map.put("total",getAllBrand().size());
            map.put("brandList",brandPage);
            return map;
        }else {
            List<TBrand> brandPage = brandRepository.getBrandByNameLike(name, currentPage, pageSize);
            map.put("total",brandRepository.getCountByNameLike(name));
            map.put("brandList",brandPage);
            return map;
        }
    }

    @Override
    public List<TBrand> getBrandBySubType(Long subTypeId) {
        List<TBrand> brandList = new ArrayList<>();
        List<TSubTypeBrandRelation> subTypeBrandRelas = subTypeBrandRelaRepository.getBrandBySubTypeId(subTypeId);
        if(!CollectionUtils.isEmpty(subTypeBrandRelas)){
            for (TSubTypeBrandRelation subTypeBrandRelation : subTypeBrandRelas){
                Long brandId = subTypeBrandRelation.getBrandId();
                brandList.add(brandRepository.getOne(brandId));
            }
        }
        return brandList;
    }
}

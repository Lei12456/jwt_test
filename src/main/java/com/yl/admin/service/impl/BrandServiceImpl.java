package com.yl.admin.service.impl;

import com.yl.admin.dao.BrandRepository;
import com.yl.admin.domain.TBrand;
import com.yl.admin.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public List<TBrand> getAllBrand() {
        List<TBrand> brands = brandRepository.getAllBrand();
        if (CollectionUtils.isEmpty(brands)){
            return null;
        }
        return brands;
    }

    @Override
    public List<TBrand> getBrandByPage(Long page, Long pageSize) {
        Long currentPage =  (page - 1) * pageSize;
        return brandRepository.getBrandPage(currentPage,pageSize);
    }

    @Override
    public void addOrUpdateBrand(TBrand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<TBrand> getBrandByNameLike(String name,Long page,Long pageSize) {
        Long currentPage = (page -1) * pageSize;
        return brandRepository.getBrandByNameLike(name,currentPage,pageSize);
    }
}

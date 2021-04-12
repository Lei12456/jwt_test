package com.yl.admin.dto;

import com.yl.admin.domain.TBrand;
import com.yl.admin.domain.TProductSubType;
import com.yl.admin.domain.TProductType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/4
 */
@Data
public class ProductDto {

    private Long id;

    private String name;

    private String picture;//商品图片

    private Long price;    //商品价格

    private Integer flag; //商品标签

    private Long typeId;

    private Long subTypeId;

    private Long brandId;

    private Long stock;   //商品库存

    private Long  salesVolume; //商品销量

    private Integer  status;

}

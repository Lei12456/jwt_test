package com.yl.admin.dto;

import com.yl.admin.domain.TBrand;
import lombok.Data;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/5/5
 */
@Data
public class BrandDto extends TBrand {
    private Long  subTypeId;
}

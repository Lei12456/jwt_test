package com.yl.admin.vo;


import com.yl.admin.domain.TOrder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/11
 */
@Data
public class StatisticalDataVo {

    private Long productSum;
    private Long orderSum;
    private Long subTypeSum;
    private Long totalSales;

    private  Map<String, Object> closeOrderMap;
    private  Map<String, Object> deliveredOrderMap;
    private  Map<String, Object> completedOrderMap;
}

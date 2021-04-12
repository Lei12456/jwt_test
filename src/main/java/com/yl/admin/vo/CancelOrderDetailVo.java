package com.yl.admin.vo;

import com.yl.admin.domain.TCustomer;
import com.yl.admin.domain.TReceiver;
import com.yl.admin.dto.ProductDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/9
 */
@Data
public class CancelOrderDetailVo {

    private Long id;

    private String number;          //订单编号

    private TCustomer cancelCus;     //退// 货人信息

    private List<ProductDto> products;

    private Integer status;         //订单状态

    private Long amountReturn;         //退款金额

    private String reason;

    private String isChecked;

    private Date submitTime;         //订单提交时间

    private Date modifyTime;        //订单更新时间

}

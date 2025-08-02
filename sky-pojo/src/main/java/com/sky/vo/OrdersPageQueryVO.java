package com.sky.vo;

import com.sky.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersPageQueryVO {

    //订单菜品信息
    private String orderDishes;

    //订单详情
    private Orders orders;
}

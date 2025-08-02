package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderDetailMapper {
    /**
     * 批量插入订单详细表
     * @param orderDetails
     */
    void insertBatch(ArrayList<OrderDetail> orderDetails);

    /**
     * 根据订单id查询数据
     * @param OrderId
     * @return
     */
    @Select("select * from order_detail where order_id = #{OrderId}")
    List<OrderDetail> selectByOrderId(Long OrderId);
}

package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 修改订单数据
     * @param orders
     */
    @Update("update orders set status = #{status}, pay_method = #{payMethod}, pay_status = #{payStatus} where number = #{number}")
    void update(Orders orders);

    /**
     * 条件查询
     * @param status
     * @return
     */
    List<Orders> pageQuery(Integer status);

    /**
     * 取消订单
     * @param orders
     */
    void cancel(Orders orders);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders selectById(Long id);

    /**
     * 查询此状态下的订单数量
     * @param status
     */
    @Select("select count(*) from orders where status = #{status}")
    Integer countByStatus(Integer status);

    /**
     * 完成订单
     * @param orders
     */
    @Update("update orders set status = #{status} where id = #{id}")
    void complete(Orders orders);

    /**
     * 拒单
     * @param orders
     */
    @Update("update orders set status = #{status}, rejection_reason = #{rejectionReason} where id = #{id}")
    void rejection(Orders orders);

    /**
     * 根据id修改status
     * @param orders
     */
    @Update("update orders set status = #{status} where id = #{id}")
    void updateStatus(Orders orders);

    /**
     * 条件查询
     * @param ordersPageQueryDTO
     * @return
     */
    List<Orders> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 统计营业额
     * @param begin
     * @param end
     * @return
     */
    @Select("select sum(amount) from orders where order_time between #{begin} and #{end}")
    Double sumTurnover(LocalDateTime begin, LocalDateTime end);

    /**
     * 统计订单数量
     * @param beginTime
     * @param endTime
     * @param status
     * @return
     */
    Integer countOrder(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    /**
     * top10
     * @param beginTime
     * @param endTime
     * @return
     */
    @Select("select od.name name, sum(od.number) number from order_detail od, orders o where od.order_id = o.id and o.order_time between #{beginTime} and #{endTime} " +
            "group by name order by number desc limit 10")
    List<GoodsSalesDTO> top10(LocalDateTime beginTime, LocalDateTime endTime);
}

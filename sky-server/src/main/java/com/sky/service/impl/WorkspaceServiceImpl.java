package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    DishMapper dishMapper;

    /**
     * 查询今日运营数据
     * @return
     */
    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        Double turnover = orderMapper.sumTurnover(begin, end);
        turnover = turnover == null ? 0 : turnover;

        Integer validOrderCount = orderMapper.countOrder(begin, end, Orders.COMPLETED);
        validOrderCount = validOrderCount == null ? 0 : validOrderCount;

        Integer totalOrderCount = orderMapper.countOrder(begin, end, null);
        totalOrderCount = totalOrderCount == null ? 0 : totalOrderCount;
        Double orderCompletionRate = 0.0;
        if(totalOrderCount > 0){
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
        }

        Double unitPrice = 0.0;
        if(validOrderCount > 0){
            unitPrice = turnover / validOrderCount;
        }

        Integer newUsers = userMapper.countUser(begin, end);
        newUsers = newUsers == null ? 0 : newUsers;

        return BusinessDataVO.builder()
                .turnover(turnover)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .unitPrice(unitPrice)
                .newUsers(newUsers)
                .build();
    }


    /**
     * 查询套餐总览
     * @return
     */
    @Override
    public SetmealOverViewVO getOverviewSetmeals() {
        //状态 0:停用 1:启用

        // 已启售数量
        Integer sold = setmealMapper.countByStatus(1);

        // 已停售数量
        Integer discontinued = setmealMapper.countByStatus(0);

        return SetmealOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    /**
     * 查询菜品总览
     * @return
     */
    @Override
    public DishOverViewVO getOverviewDishes() {
        //状态 0:停用 1:启用

        // 已启售数量
        Integer sold = dishMapper.countByStatus(1);

        // 已停售数量
        Integer discontinued = dishMapper.countByStatus(0);

        return DishOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    /**
     * 查询订单管理数据
     * @return
     */
    @Override
    public OrderOverViewVO getOverviewOrders() {
        //待接单数量
        Integer waitingOrders = orderMapper.countByStatus(Orders.TO_BE_CONFIRMED);

        //待派送数量
        Integer deliveredOrders = orderMapper.countByStatus(Orders.CONFIRMED);

        //已完成数量
        Integer completedOrders = orderMapper.countByStatus(Orders.COMPLETED);

        //已取消数量
        Integer cancelledOrders = orderMapper.countByStatus(Orders.CANCELLED);

        //全部订单
        Integer allOrders = orderMapper.countByStatus(null);

        return OrderOverViewVO.builder()
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .completedOrders(completedOrders)
                .cancelledOrders(cancelledOrders)
                .allOrders(allOrders)
                .build();
    }
}

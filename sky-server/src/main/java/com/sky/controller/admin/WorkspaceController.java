package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@Api(tags = "")
@RequestMapping("/admin/workspace")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    OrderService orderService;

    /**
     * 查询今日运营数据
     * @return
     */
    @ApiOperation("查询今日运营数据")
    @GetMapping("/businessData")
    public Result<BusinessDataVO> getBusinessData(){
        LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        BusinessDataVO businessDataVO = workspaceService.getBusinessData(begin, end);
        return Result.success(businessDataVO);
    }


    /**
     * 查询套餐总览
     * @return
     */
    @ApiOperation("查询套餐总览")
    @GetMapping("/overviewSetmeals")
    public Result<SetmealOverViewVO> getOverviewSetmeals(){
        SetmealOverViewVO setmealOverViewVO = workspaceService.getOverviewSetmeals();
        return Result.success(setmealOverViewVO);
    }

    /**
     * 查询菜品总览
     * @return
     */
    @ApiOperation("查询菜品总览")
    @GetMapping("/overviewDishes")
    public Result<DishOverViewVO> getOverviewDishes(){
        DishOverViewVO dishOverViewVO = workspaceService.getOverviewDishes();
        return Result.success(dishOverViewVO);
    }

    /**
     * 查询订单管理数据
     * @return
     */
    @ApiOperation("查询订单管理数据")
    @GetMapping("/overviewOrders")
    public Result<OrderOverViewVO> getOverviewOrders(){
        OrderOverViewVO orderOverViewVO = workspaceService.getOverviewOrders();
        return Result.success(orderOverViewVO);
    }

}

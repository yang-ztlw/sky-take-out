package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 条件查询
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> select(ShoppingCart shoppingCart);

    /**
     * 将商品number加一
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} + 1 where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 插入商品数据
     * @param shoppingCart
     */
    void insert(ShoppingCart shoppingCart);
}

package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
	
	/**
	 * 根据传入的查询条件返回店铺类别列表
	 * @param shopCategory
	 * @return
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
	
	/**
	 * 查询特定的店铺名字
	 * @param id
	 * @return
	 */
	String queryShopCategoryName(@Param("id") Integer id);
}

package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	
	/**
	 * 根据传入的查询条件返回店铺类别列表
	 * @param shopCategoryCondition  
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}

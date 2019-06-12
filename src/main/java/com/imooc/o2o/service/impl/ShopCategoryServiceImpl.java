package com.imooc.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	/**
	 * 根据传入的查询条件返回店铺类别列表
	 * @param shopCategoryCondition  
	 * @return
	 */
	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
}

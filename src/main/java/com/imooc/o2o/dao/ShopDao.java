package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

/**
 * 店铺接口测试
 * @author Lin
 * @date 2019年1月24日 上午9:34:11
 */
public interface ShopDao {
	
	/**
	 * 添加店铺
	 * @param shop 客户新添加的店铺对象
	 * @return
	 */
	int insertShop(Shop shop);
}

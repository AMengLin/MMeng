package com.imooc.o2o.service;

import java.io.File;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

public interface ShopService {
	
	/**
	 * 
	 * @param shop 新增的店铺信息
	 * @param shopImg 店铺图片
	 * @return 
	 */
	ShopExecution addShop(Shop shop,File shopImg);
	
}

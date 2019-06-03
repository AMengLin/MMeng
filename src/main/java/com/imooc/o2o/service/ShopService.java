package com.imooc.o2o.service;

import java.io.InputStream;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

public interface ShopService {
	
	/**
	 * 
	 * @param shop 新增的店铺信息
	 * @param shopImg 店铺图片
	 * @param fileName 因为InputStream无法获取输入流文件的名字，需要用到这来获取，需要知道图片扩展名是png还是jpg类型
	 * @return 
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName);
	
}

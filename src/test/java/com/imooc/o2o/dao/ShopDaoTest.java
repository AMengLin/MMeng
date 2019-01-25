package com.imooc.o2o.dao;

import java.util.Date;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	
	private int insertShop(Shop shop){
		
		//创建个人信息对象
		PersonInfo personInfo = new PersonInfo();
		//创建区域对象
		Area area = new Area();
		//创建种类对象
		ShopCategory shopCategory = new ShopCategory();
		//给这些对象插入测试数据
		personInfo.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(4L);
		//给店铺添加测试数据
		shop.setShopName("宅小野奶茶");
		shop.setShopDesc("好喝的奶茶");
		shop.setShopAddr("上川店");
		shop.setPhone("123456");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		
		return 0;
	}
}

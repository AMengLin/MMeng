package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{
	
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop(){
		//创建商品对象888
				Shop shop = new Shop();
				//创建个人信息对象
				PersonInfo personInfo = new PersonInfo();
				//创建区域对象
				Area area = new Area();
				//创建种类对象
				ShopCategory shopCategory = new ShopCategory();
				//给这些对象插入测试数据
				personInfo.setUserId(1L);
				area.setAreaId(2);
				shopCategory.setShopCategoryId(10L);
				//shop添加上面3个对象的参数
				shop.setOwner(personInfo);
				shop.setArea(area);
				shop.setShopCategory(shopCategory);
				//给店铺添加测试数据
				shop.setShopName("宅小野奶茶35");
				shop.setShopDesc("好喝的奶茶35");
				shop.setShopAddr("上川店35");
				shop.setPhone("18613650");
				shop.setCreateTime(new Date());
				shop.setPriority(1);
				//设置状态为check，审核状态
				shop.setEnableStatus(ShopStateEnum.CHECK.getState());
				shop.setAdvice("审核中");
				File shopImg = new File("C:/Users/Administrator/Desktop/xiaohuangren.jpg");
				System.out.println(shopImg.toString());
				ShopExecution se = shopService.addShop(shop, shopImg );
				assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}

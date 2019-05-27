package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void insertShop(){
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
		shop.setShopName("宅小野奶茶1");
		shop.setShopDesc("好喝的奶茶2");
		shop.setShopAddr("上川店3");
		shop.setPhone("1234567");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		int testShopDao = shopDao.insertShop(shop);
		assertEquals(1,testShopDao);
	}
	
	
	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(39L);
		shop.setShopDesc("测试描述558");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	
	
}

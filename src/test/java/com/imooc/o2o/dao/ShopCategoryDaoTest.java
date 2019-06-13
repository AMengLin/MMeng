package com.imooc.o2o.dao;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testShopCategoryDao(){
		//List<ShopCategory> list = shopCategoryDao.queryShopCategory(new ShopCategory());
		//assertEquals(21,list.size());
		//创建一级目录，即父级目录 
		ShopCategory parentCategor = new ShopCategory();
		//创建二级目录
		ShopCategory testCategory = new ShopCategory();
		//设置一级目录的id
		parentCategor.setShopCategoryId(10L);
		//设置二级目录id,把ShopCategory对象放进去，因为上面父级id设置了10L，
		//所以下面的parentCategor对象可以拿到setShopCategoryId为10 的数据
		testCategory.setParent(parentCategor);
		List<ShopCategory> lists = shopCategoryDao.queryShopCategory(testCategory);
		assertEquals(2,lists.size());
		System.out.println(lists.get(1).getShopCategoryName());
	}
	
	@Test
	public void testShopCategoryDaos(){
		String name = shopCategoryDao.queryShopCategoryName(10);
		System.out.println(name);
	}
}

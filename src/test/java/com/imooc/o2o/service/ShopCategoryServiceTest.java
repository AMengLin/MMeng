package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.ShopCategory;

public class ShopCategoryServiceTest {
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Test
	public void getQuery(){
		ShopCategory parentCategor = new ShopCategory();
		//创建二级目录
		ShopCategory testCategory = new ShopCategory();
		//设置一级目录的id
		parentCategor.setShopCategoryId(10L);
		//设置二级目录id,把ShopCategory对象放进去，因为上面父级id设置了10L，
		//所以下面的parentCategor对象可以拿到setShopCategoryId为10 的数据
		testCategory.setParent(parentCategor);
		List<ShopCategory> lists = shopCategoryService.getShopCategoryList(testCategory);
		assertEquals(2,lists.size());
	}
}

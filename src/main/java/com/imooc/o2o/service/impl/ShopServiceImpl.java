package com.imooc.o2o.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.ShopService;

public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;
	
	/**
	 * @param shop 新增的店铺信息
	 * @param shopImg 店铺图片
	 * @return 
	 */
	public ShopExecution addShop(Shop shop, File shopImg) {
		
		//判断商铺信息是否为空
		if(shop==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给商铺赋初始值
			//状态为0，不可用
			shop.setEnableStatus(0);
			//添加创建时间和最后修改时间
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//调用dao层，添加商铺
			int effectedNum = shopDao.insertShop(shop);
			//如果影响行数<=0，添加失败
			if(effectedNum<=0){
				throw new RuntimeException("店铺创建失败");
			}else{
				if(shopImg!=null){
					try {
						//effectedNum = addImg(shop,shopImg);
						if(effectedNum<=0){
							
						}
					} catch (Exception e) {
						throw new RuntimeException("添加图片失败");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}

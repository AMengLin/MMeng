package com.imooc.o2o.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	/**
	 * @param shop
	 *            新增的店铺信息
	 * @param shopImg
	 *            店铺图片
	 * @return
	 */
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {

		// 判断商铺信息是否为空
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给商铺赋初始值
			// 状态为0，不可用
			shop.setEnableStatus(0);
			// 添加创建时间和最后修改时间
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 调用dao层，添加商铺
			int effectedNum = shopDao.insertShop(shop);
			// 如果影响行数<=0，添加失败
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (thumbnail.getImage() != null) {
					// 存储图片
					try {
						//addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					// 更新图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		//返回审核中的商品枚举类型
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

//	private void addShopImg(Shop shop, ImageHolder thumbnail) {
//		// 获取shop图片目录的相对值路径
//		String dest = PathUtil.getShopImagePath(shop.getShopId());
//		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
//		shop.setShopImg(shopImgAddr);
//	}

}

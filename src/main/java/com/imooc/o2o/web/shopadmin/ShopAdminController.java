package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主要用来解析路由并转发到相应的html中
 * @author Lin
 * @date 2019年6月5日 下午9:53:43
 */
@Controller
@RequestMapping(value="shopadmin",method={RequestMethod.GET})
public class ShopAdminController {
	
	@RequestMapping(value="/shopoperation")
	public String shopOperation(){
		//转发至店铺注册，编辑页面
		return "shop/shopoperation";
	}
}

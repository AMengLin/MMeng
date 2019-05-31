package com.imooc.o2o.web.shopadmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.util.HttpServletRequestUtil;

/**
 * 店铺管理控制层
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManageController {
	
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		//接受并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		//将json数据转化成实体类对象
		ObjectMapper mapper = new ObjectMapper();
		//创建一个实体类接受
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//处理实体对象信息，接下来处理图片，用到Sping自带的CommonsMultipartFile处理
		CommonsMultipartFile shopImg = null;
		//CommonsMultipartResolver是处理文件上传用到的，父接口是MultipartResolver
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				//从request的本次会话但当中的上下文去获取相关文件上传的内容
				request.getSession().getServletContext());
		//判断commonsMultipartResolver中的request是否有上传的文件流
		if(commonsMultipartResolver.isMultipart(request)){
			//把request转成成MultipartHttpServletRequest类型的对象
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			//提取出相对应的文件流
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		
		return null;
	}
}

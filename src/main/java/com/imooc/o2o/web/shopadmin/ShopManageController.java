package com.imooc.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

/**
 * 店铺管理控制层
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManageController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	/**
	 * 获取店铺类别和区域列表
	 * @return
	 */
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 创建店铺列表集合以及区域集合
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			// 调用service层，查询出店铺列表数据
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			// 调用service层，查询出区域列表数据
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success",true);
		} catch (Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//如果输入的验证码和图片的验证码不一样
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 接受并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		// 将json数据转化成实体类对象
		ObjectMapper mapper = new ObjectMapper();
		// 创建一个实体类接受
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		// 处理实体对象信息，接下来处理图片，用到Sping自带的CommonsMultipartFile处理
		CommonsMultipartFile shopImg = null;
		// CommonsMultipartResolver是处理文件上传用到的，父接口是MultipartResolver
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				// 从request的本次会话但当中的上下文去获取相关文件上传的内容
				request.getSession().getServletContext());
		// 判断commonsMultipartResolver中的request是否有上传的文件流
		if (commonsMultipartResolver.isMultipart(request)) {
			// 把request转成成MultipartHttpServletRequest类型的对象
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			// 提取出相对应的文件流
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 注册店铺
		if (shop != null && shopImg != null) {
			// 目前先给一个指定的店家id
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			// 创建一个file文件
			/*
			 * File shopImgFile = new
			 * File(PathUtil.getImgBasePath()+ImageUtil.getRandomFileName());
			 * try { shopImgFile.createNewFile(); } catch (IOException e) {
			 * modelMap.put("success", false); modelMap.put("errMsg",
			 * e.getMessage()); return modelMap; }
			 */
			/*
			 * try { //然后把前台传过来的信息添加到shop中
			 * //因为addShop的方法中的shopImg是CommonsMultipartFile类型，需要先转化成file类型
			 * //调用inputStreamToFile,把shopImg.getInputStream()
			 * 流中的内容读取进shopImgFile文件夹中，生成图片，最终是一个图片的路径
			 * inputStreamToFile(shopImg.getInputStream(),shopImgFile); } catch
			 * (IOException e) { modelMap.put("success", false);
			 * modelMap.put("errMsg", e.getMessage()); return modelMap; }
			 */
			// shopImg.getOriginalFilename(),CommonsMultipartFile中的getOriginalFilename方法，可获取原本文件的名字
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
			}
			// 返回结果和ShopStateEnum枚举中审核的状态是一样的
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}

	// 编写一个方法，把inputStream转化成file
	/*
	 * private static void inputStreamToFile(InputStream ins, File file) { //
	 * 定义一个输出流,最终结果将其转换成文件 FileOutputStream os = null; try { os = new
	 * FileOutputStream(file); int bytesRead = 0; //
	 * 定义一个默认值为1024的byte的数组,调用buffer读取InputStream里面的内容 byte[] buffer = new
	 * byte[1024]; // 把InputStream的内容读取到buffer中 while ((bytesRead =
	 * ins.read(buffer)) != -1) { //
	 * 把buffer的内容不断写入到输出流中,读满1024的字节，就往输出流里写入一次，直到都满为止 os.write(buffer, 0,
	 * bytesRead); } } catch (Exception e) { throw new
	 * RuntimeException("调用inputStreamToFile产生异常"+e.getMessage());
	 * 
	 * }finally { //把输出刘流和输入流都关闭掉 try{ if(os!=null){ os.close(); }
	 * if(ins!=null){ ins.close(); } }catch(IOException e){ throw new
	 * RuntimeException("inputStreamToFile关闭io产生异常"+e.getMessage()); } } }
	 */

}

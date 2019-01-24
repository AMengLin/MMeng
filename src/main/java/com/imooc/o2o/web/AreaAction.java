package com.imooc.o2o.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;

@Controller
@RequestMapping("/areatest")
public class AreaAction {
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/listarea",method=RequestMethod.GET)	
	@ResponseBody
	public Map<String,Object> listArea(){
		Map<String,Object> map = new HashMap<String,Object>();
		//查询返回区域的所有对象
		List<Area> list = areaService.getList();
		//把区域对象封装到map集合中返回给前端
		try {
			map.put("rows", list);
			map.put("total", list.size());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		//返回最终的map集合
		return map;
	}
}

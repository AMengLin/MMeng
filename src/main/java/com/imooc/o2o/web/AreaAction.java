package com.imooc.o2o.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.imooc.o2o.service.AreaService;

@Controller
public class AreaAction {
	
	@Autowired
	private AreaService areaService;
	
	public void getList(){
		areaService.getList();
	}
}

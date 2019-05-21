package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.Area;

public interface AreaDao {
	
	/**
	 * 查询区域列表e
	 * @return areaList(所有的区域对象)
	 */
	List<Area> queryArea();
}

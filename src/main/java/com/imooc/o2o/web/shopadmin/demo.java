package com.imooc.o2o.web.shopadmin;

import java.io.File;
import java.io.IOException;

import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

public class demo {
	
	public static void main(String[] args) {
		File shopImgFile = new File(PathUtil.getImgBasePath()+ImageUtil.getRandomFileName());
		try {
			System.out.println(shopImgFile.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

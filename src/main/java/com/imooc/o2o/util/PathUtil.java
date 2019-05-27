package com.imooc.o2o.util;

public class PathUtil {
	
	private static String seperator = System.getProperty("file.seperator");
	public static String getImageBasePath(){
		String os = System.getProperty("os.name");
		String bathPath = "";
		if(os.toLowerCase().startsWith("win")){
			bathPath="D:/projectedev/image/";
		}else{
			bathPath = "home/meng/image";
		}
		bathPath=bathPath.replace("/", seperator);
		return bathPath;
	}
}

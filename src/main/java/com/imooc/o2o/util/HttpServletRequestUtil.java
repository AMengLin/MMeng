package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 负责处理HttpServlet request请求的参数， HttpServletRequest request，String key
 * key中有各种类型的值，取出来转换成需要的值
 * 
 * @author Administrator
 *
 */
public class HttpServletRequestUtil {

	/**
	 * HttpServlet request参数里可能会有需要的类型的值
	 * 
	 * @param request
	 *            参数
	 * @param key
	 *            request参数中提取的key
	 * @return 需要转化的的类型
	 */
	// 转化成整形
	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	// 转化成长整形
	public static long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}
	
	//转化成boolean形
	public static boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}
	
	//String类型，处理一下空格符
	public static String getString(HttpServletRequest request, String key){
		try {
			String result = request.getParameter(key);
			//如果result有空格符的话
			if(result!=null){
				result = result.trim();
			}
			if("".equals(result)){
				result = null; 
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}

package com.imooc.o2o.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");

	/**
	 * 图片保存的根目录
	 * @return
	 */
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/baidu/work/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	/**
	 * 根据业务不同，返回储存的路径（店铺图片存储的相对值路径）
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {
		String imagePath = "D:/upload/images/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
}

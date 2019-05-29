package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

	// 项目中水印的图片
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	// 设置时间的格式
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	// 随机对象
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 将CommonsMultipartFile转换成File类
	 * 
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 * 
	 * @param thumbnail
	 *            用户传过来的文件流
	 * @param targerAddr
	 *            处理好的缩略图所在的位置
	 * @return
	 */
	public static String generateThumbnail(File thumbnail, String targerAddr) {
		// 获取不重复的名字
		String realFileName = getRandomFileName();
		// 获取用户传过来的扩扩展名,jpg,png
		String extension = getFileExtension(thumbnail);
		// 如果目标不存在，则自动创建
		makeDirPath(targerAddr);
		// 获取文件储存的相对路径（带文件名）
		String relativeAddr = realFileName + extension + targerAddr;
		// 获取文件要保存的目标路径
		// 打印路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("basePath is :" + basePath);
		try {
			// 调用Thumbnails生成有水印的缩略图
			Thumbnails.of(thumbnail).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			e.getStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及的目录，即/home/work/xiangze/xxx.jpg, 
	 * 那么 home work xiangze 三个文件夹都得创建
	 * @param targerAddr 目标文件所属的文件夹的对相对路径
	 */
	private static void makeDirPath(String targetAddr) {
		// 获取绝对路径
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(File cfile) {
		// 获取输入文件流的文件名
		String originFileName = cfile.getName();
		return originFileName.substring(originFileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机的五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}

	public static void main(String[] args) throws IOException {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		Thumbnails.of(new File("D:\\Users\\baidu\\work\\image\\xiaohuangren.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile("D:/Users/baidu/work/image/xiaohuangrennew.jpg");
	}
}

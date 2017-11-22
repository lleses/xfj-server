package cn.dlj.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Tool {

	/*** 
	* MD5加密 生成32位md5码
	* @param 待加密字符串
	* @return 返回32位md5码
	*/
	public static String md5Encode(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		byte[] byteArray;
		try {
			byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}


	/** 获取(第n天)的 23:59:59 **/
	public static Date day(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, +day);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return c.getTime();
	}

	/**
	 * 文件检查:当父目录不存在时，创建父目录
	 * 
	 * @param file
	 *            需要检查的文件
	 */
	public static void fileCheck(File file) {
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			fileCheck(parentFile);
			parentFile.mkdir();
		}
	}

	/**
	 * 写文件
	 * @param in
	 * 			输入流
	 * @param out
	 * 			文件输出流
	 * @return	true:成功  false:失败
	 */
	public static boolean write(InputStream in, FileOutputStream out) {
		if (null != in && out != null) {
			try {
				byte[] b = new byte[1024];
				int k = 0;
				while ((k = in.read(b, 0, 1024)) != -1) {
					out.write(b, 0, k);
				}
				out.flush();
			} catch (IOException e) {
				return false;
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
					}
				}
				if (null != in) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 写文件
	 * @param in
	 * 			输入流
	 * @param outPath
	 * 			输出路径
	 * @return	true:成功  false:失败
	 */
	public static boolean write(InputStream in, String outPath) {
		try {
			FileOutputStream out = new FileOutputStream(outPath);
			return write(in, out);
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	/**
	 * 写文件
	 * 
	 * @param multipartFile
	 * 			输入文件
	 * @param outPath
	 * 			输出路径
	 * @return  true:成功  false:失败
	 */
	public static boolean write(MultipartFile multipartFile, String outPath) {
		try {
			InputStream in = multipartFile.getInputStream();
			return write(in, outPath);
		} catch (IOException e1) {
			return false;
		}
	}

	/**
	 * 写文件
	 * 
	 * @param multipartFile
	 * 			输入文件
	 * @param file
	 * 			输出文件
	 * @return  true:成功  false:失败
	 */
	public static boolean write(MultipartFile multipartFile, File file) {
		try {
			InputStream in = multipartFile.getInputStream();
			return write(in, new FileOutputStream(file));
		} catch (IOException e1) {
			return false;
		}
	}

	/**
	 * 过滤系统换行符
	 * 
	 * @param content
	 * 				内容
	 * @return
	 */
	public static String filter(String content) {
		content = content.replace("\r\n", "");
		content = content.replace("\n", "");
		content = content.replace("\r", "");
		content = content.replace("\"", "\\" + "\"");
		return content;
	}

	/**
	 * 获取天数
	 * 
	 * @param begin
	 * 			开始时间
	 * @param end
	 * 			结束时间
	 * @return
	 */
	public static String days(Date begin, Date end) {
		int days = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
		return String.valueOf(days + 1);
	}
}

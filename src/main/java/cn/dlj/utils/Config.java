package cn.dlj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 配置文件读取<br/>
 * 读取ClassPath下{@link CONFIG_FILE}<br/>
 * 延迟 {@link CONFIG_RELOAD_DELAY}自动重载
 * 
 * <pre>
 * 使用Config.get(key)读取配置
 * 应用停止后，请主动调用Config.stop()通知停止配置文件监听
 * </pre>
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class Config {

	private Config() {
	}

	/** 配置文件名称 {@code CONFIG_FILE} */
	public static final String CONFIG_FILE = "config.properties";

	/** 配置文件修改检查延迟时间 */
	public static final int CONFIG_RELOAD_DELAY = 1000 * 30;

	/** 配置 */
	private static Properties props = null;

	/** 配置文件上次修改时间 */
	private static long lastModified = 0L;

	/** 上次加载配置文件时间 */
	private static long lastInit = 0L;

	/** 是否停止配置文件修改监听线程 */
	private static boolean stoped = false;

	/**
	 * 应用停止，通知Config停止监听配置文件
	 */
	public static void stop() {
		if (!stoped) {
			print("Stopping listen " + CONFIG_FILE);
			stoped = true;
			if (CONFIG_MONITOR.isAlive()) {
				CONFIG_MONITOR.interrupt();
			}
		}
	}

	/**
	 * 配置文件监听是否已停止
	 * 
	 * @return true：已停止
	 */
	public static boolean isStoped() {
		return stoped;
	}

	/** 配置文件监听线程 */
	private static final Thread CONFIG_MONITOR = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				if (stoped) {
					break;
				}
				try {
					Thread.sleep(CONFIG_RELOAD_DELAY);// 延迟指定时间
				} catch (InterruptedException e) {
				}
				Config.init();// 重新加载配置文件
			}
		}
	});

	/**
	 * 重载配置文件
	 */
	synchronized public static void init() {

		long now = System.currentTimeMillis();// 当前时间
		if (now - lastInit < 5000) {
			return;// 5秒内加载过配置文件,直接返回
		}

		StringBuilder log = new StringBuilder();// 配置文件读取日志
		log.append("\n");
		log.append("\n**********************************************************");
		log.append("\n*****    Loading " + CONFIG_FILE + " ...");

		// 获取配置文件
		URL url = Config.class.getResource("/" + CONFIG_FILE);
		if (null == url) {
			log.append("\n*****    " + CONFIG_FILE + " is not found.");
			log.append("\n**********************************************************");
			log.append("\n\n");
			print(log);
			throw new RuntimeException(CONFIG_FILE + " is not found.");
		}

		InputStream in = null;// 配置文件流
		File file = new File(url.getFile());// 配置文件
		try {
			long lastModified = file.lastModified();// 文件修改时间
			if (lastModified == Config.lastModified) {// 检查配置文件修改情况
				log = null;
				return;// 文件未修改
			}
			Config.lastModified = lastModified;// 更新文件修改时间
			in = new FileInputStream(file);
		} catch (Exception e) {
			log.append("\n*****    " + CONFIG_FILE + " could not be read.");
			log.append("\n*****    " + url.getFile());
			log.append("\n**********************************************************");
			log.append("\n\n");
			print(log);
			throw new RuntimeException(CONFIG_FILE + " could not be read.", e);
		}

		// 重新加载配置文件
		props = null;
		props = new Properties();
		lastInit = now;
		try {
			props.load(in);
		} catch (IOException e) {
			log.append("\n*****    " + CONFIG_FILE + " read error. " + e.getMessage());
			log.append("\n**********************************************************");
			log.append("\n\n");
			print(log);
			throw new RuntimeException(CONFIG_FILE + " read error.", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		log.append("\n*****    " + (props == null ? "props is null" : ("props.size is " + props.size())));
		if (null != props && !props.isEmpty()) {
			Set<Object> keys = props.keySet();
			List<String> list = new ArrayList<String>();
			for (Object key : keys) {
				list.add(key.toString());
			}
			Collections.sort(list);
			for (String key : list) {
				log.append("\n*****      " + key + "=" + props.getProperty(key));
			}

			// 加载Web资源根路径
			webResContextPath = StringUtils.trim(props.getProperty("web_file_contextpath"));
			if (null == webResContextPath) {
				String fileRootDir = StringUtils.trim(props.getProperty("file_root_dir"));
				if (null != fileRootDir && fileRootDir.equals("/data/tomcat/file")) {
					webResContextPath = "https://res.dlj100.cn/";
				} else {
					webResContextPath = "https://res.test.dlj100.cn/";
				}
			} else if (!webResContextPath.endsWith("/")) {
				webResContextPath = webResContextPath + "/";
			}

		}
		log.append("\n**********************************************************");
		log.append("\n\n");
		print(log);// 打印日志

	}

	/** 必要性输出信息 */
	private static void print(CharSequence s) {
		System.out.println(s);// 必要性输出信息
	}

	/**
	 * 获取配置
	 */
	public static String get(String key) {
		if (null == props) {
			init();
			// 启动监听线程
			if (!CONFIG_MONITOR.isAlive()) {
				try {
					CONFIG_MONITOR.setDaemon(true);
					CONFIG_MONITOR.start();
				} catch (IllegalThreadStateException e) {
				}
			}
		}
		return props.getProperty(key);
	}

	/** Web资源根路径 */
	private static String webResContextPath = null;

	/**
	 * 获取Web资源根路径
	 * 
	 * <pre>
	 * 配置KEY：web_file_contextpath
	 * 生产平台：https://res.dlj100.cn/
	 * 测试平台：https://res.test.dlj100.cn/
	 * config.properties未配置KEY：web_file_contextpath时，根据file_root_dir值自动判断
	 * </pre>
	 * 
	 * @return Web资源根路径
	 */
	public static String getWebResContextPath() {
		return webResContextPath;
	}

}

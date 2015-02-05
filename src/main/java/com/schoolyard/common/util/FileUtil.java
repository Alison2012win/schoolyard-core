package com.schoolyard.common.util;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class.getName());

	public static String REAL_PATH = null;
	static {
		if (Thread.currentThread().getContextClassLoader().getResource("/") != null) {
			REAL_PATH = Thread.currentThread().getContextClassLoader()
					.getResource("/").getPath().replace("%20", " ");
		} else {
			REAL_PATH = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath().replace("%20", " ");
		}
	}

	// public static final String REAL_PATH
	// =FileUtil.class.getClassLoader().getResource("/").getPath();

	/**
	 * 读取properties文件
	 * 
	 * @param propsFile
	 *            文件路径，相对路径于com 同级
	 * @return
	 */
	public static Properties loadProperties(String propsFile) {

		Properties props = new Properties();
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(REAL_PATH
					+ propsFile));
			props.load(is);
			is.close();
			log.info("loadProperties OK!");
			log.info("Properties path = " + REAL_PATH + propsFile);
		} catch (Exception e) {
			log.error("CuteMonitor FileUtil || Unable to load the properties file:"
					+ e.getMessage());
			log.error("Properties path = " + REAL_PATH + propsFile);
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return props;
	}

	/**
	 * 读取路径当前文件夹内的音乐文件列表
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> findMusicFiles(String path) {
		File musicFile = new File(path);
		List<String> musicFullPaths = new ArrayList<String>();
		if (musicFile.exists()) {
			File[] musics = musicFile.listFiles();
			for (File music : musics) {
				String musicName = music.getName();
				int i = musicName.lastIndexOf(".");
				String end = musicName.substring(i + 1);
				if (end.equals("wav") || end.equals("mp3") || end.equals("wma")) {
					musicFullPaths.add(musicName);
				}
			}
		}
		return musicFullPaths;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param charsetName
	 *            文件编码方式
	 * @return
	 */
	public static String readFile(String filePath, String charsetName) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filePath), charsetName));
			String data = null;
			while ((data = br.readLine()) != null) {
				sb.append(data);
				sb.append("<br/>");

			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (Exception e) {
			}

		}
		return sb.toString();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param charsetName
	 *            文件编码方式
	 * @return
	 */
	public static String readFile(String filePath, String charsetName,
			long lineNow, long lineEnd) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filePath), charsetName));
			String data = null;
			int i = 0;
			while ((data = br.readLine()) != null) {
				if (i <= lineEnd && i >= lineNow) {
					sb.append(data);
					sb.append("<br/>");
				}
				i++;
			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (Exception e) {
			}
		}
		return sb.toString();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param charsetName
	 *            文件编码方式
	 * @return
	 */
	public static long getFileLine(String filePath, String charsetName) {
		// StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		int i = 0;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filePath), charsetName));
			// String data = null;

			while (br.readLine() != null) {
				i++;
			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (Exception e) {
			}

		}
		return i;
	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 *            内容
	 * @param path
	 *            文件路径
	 * @param filename
	 *            文件名
	 * @throws IOException
	 */
	public static void SaveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, bytesum);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
	
	/**
	 * <p>
	 * Description:[删除文件（文件夹）]
	 * </p>
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}
}

package file;
/**
 * File handle class
 * @author naplues
 *
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileHandle {
	/**
	 * 读取文件返回一个字符串列表
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> readFiletoLines(String filePath, boolean... args) {
		BufferedReader reader = null;
		if (args.length > 0 && args[0])
			reader = FileHandle.getExternelPath(filePath); // 读取文件系统路径
		else
			reader = FileHandle.getActualPath(filePath); // 默认读取实际路径
		List<String> lines = new ArrayList<>();
		try {
			String s = null;
			while ((s = reader.readLine()) != null)
				lines.add(s);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * 读取文件返回一个文本字符串
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileToString(String filePath) {
		String string = "";
		List<String> lines = readFiletoLines(filePath);
		for (String t : lines) {
			string += t;
		}

		return string;
	}

	/**
	 * 获取实际的文件路径
	 * 
	 * @param path
	 * @return
	 */
	public static BufferedReader getActualPath(String path) {
		try {
			if (FileHandle.class.getResource("FileHandle.class").toString().startsWith("jar:")) {
				path = "/" + path;
				return new BufferedReader(new InputStreamReader(FileHandle.class.getResourceAsStream(path), "UTF-8"));
			}
			return new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取参数文件
	 * 
	 * @param path
	 * @return
	 */
	public static BufferedReader getExternelPath(String path) {
		try {
			return new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}

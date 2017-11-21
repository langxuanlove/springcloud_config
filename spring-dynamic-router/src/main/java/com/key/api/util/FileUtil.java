package com.key.api.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * 搜索文件目录下指定文件名称的文件，返回绝对路径
 * @author aaron
 *
 */
public class FileUtil {

	/**
	 * 列出目录下所有的文件包括子目录的文件路径
	 * 
	 * @param dirName
	 *            文件夹的文件路径
	 */

	public static void listAllFiles(String dirName) {

		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dirName.endsWith(File.separator)) {
			dirName = dirName + File.separator;
		}

		File dirFile = new File(dirName);
		// 如果dir对应的文件不存在，或者不是一个文件夹则退出
		if (!dirFile.exists() || (!dirFile.isDirectory())) {
			return;
		}

		/*
		 * list方法返回该目录下的所有文件（包括目录）的文件名，文件名不含路径信息
		 * 
		 * String[] files = dirFile.list(); for(int i = 0; i < files.length;
		 * i++){ System.out.println(files[i]); }
		 */

		// 列出文件夹下所有的文件,listFiles方法返回目录下的所有文件（包括目录）的File对象
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
			} else if (files[i].isDirectory()) {
				FileUtil.listAllFiles(files[i].getAbsolutePath());
			}
		}
	}

	/**
	 * 根据指定的文件后缀名搜索文件
	 * @param filter
	 * @param dirName
	 * @param result
	 */
	public static void listFilesByFilenameFilter(FilenameFilter filter,String dirName, List<String> result) {
		
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dirName.endsWith(File.separator)) {
			dirName = dirName + File.separator;
		}

		File dirFile = new File(dirName);
		// 如果dir对应的文件不存在，或者不是一个文件夹则退出
		if (!dirFile.exists() || (!dirFile.isDirectory())) {
			return;
		}

		// 检查源文件夹下所有能通过过滤器的文件包括子目录
		File[] files = dirFile.listFiles(filter);
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				
				result.add(files[i].getAbsolutePath());
			} else if (files[i].isDirectory()) {
				FileUtil.listFilesByFilenameFilter(filter,files[i].getAbsolutePath(), result);
			}
		}
	}
}

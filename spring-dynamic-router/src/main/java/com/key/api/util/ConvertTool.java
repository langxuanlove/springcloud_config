package com.key.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 曹芳/dsf/zyf
 *
 * 
 * @version v1.0
 *
 * Created on 2014年3月21日 下午1:39:31
 *
 * Revision History:
 * Date   		Reviser		Description
 * 
 * 	----------------------------------------------------
 * Description:【工具方法类 ConvertTool】,此方法注释不规范,请相应人员补充完善
 */
public class ConvertTool {
	private static Random random = new Random();

	/**
	 * <summary> 生成日期格式+三位随机数
	 * 
	 * @return yyyyMMddssmmhh+三位随机数
	 */
	public static String getImgName() {
		Date param = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(param) + getRandom();
	}

	/**
	 * 生成三位小于10的随机数 returns 三位10以内的随机数
	 */
	private static String getRandom() {
		return random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
				+ "";
	}
	
	/**
	 * 写入要上传的图片
	 * 
	 * @param imageStr
	 * @param imageType
	 * @param urlStr
	 * @return
	 * 				图片名称
	 * @throws IOException 
	 */
	public static String addImage(String imageStr, String imageType,
			String urlStr) throws IOException{
        byte [] c = Base64Util.decode(imageStr); 
		String imageName = getImgName() + "." + imageType;
		String savePath = urlStr;
		String toDir = urlStr + imageName; // 存储路径
		File f = new File(savePath);
		File file = new File(toDir);
		if (!f.exists()) {// 不存在 创建
			try {
				f.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(c);
		fos.flush();
		fos.close();
		return imageName;
	}

	/**
	 * 写入要上传的图片
	 * 
	 * @param imageStr
	 * @param imageType
	 * @param urlStr
	 * @return
	 * 				图片名称
	 * @throws IOException 
	 */
	public static String addImage(byte[] imageStr, String imageType,
			String urlStr) throws IOException{
		String imageName = getImgName() + "." + imageType;
		String toDir = urlStr +"/"+ imageName; // 存储路径
		File path = new File(urlStr);
		if(!path.exists()){
			path.mkdirs();
		}
		File file = new File(toDir);
		if (!file.exists()) {// 不存在 创建
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(imageStr);
		fos.flush();
		fos.close();
//		FileInputStream fis=new FileInputStream("D:\\123.jpg");
//		FileOutputStream fos=new FileOutputStream(file);
//		byte[] b =new byte[1028];
//		while(fis.read(b)!=-1){
//		fos.write(b);
//		}
//		fis.close();
//		fos.close();

		return imageName;
	}

	/**
	 * 写入要上传的图片
	 * 
	 * @param imageStr
	 * @param urlStr
	 * @return
	 * 				图片名称
	 * @throws IOException 
	 */
	public static String addImageIO(byte[] imageStr, String urlStr) throws IOException {

		String imageName = getImgName() + ".jpg";
		String toDir = urlStr + imageName; // 存储路径
		File file = new File(toDir);
		if (!file.exists()) {// 不存在 创建
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(imageStr);
		fos.flush();
		fos.close();
		return imageName;
	}
	
	/**
	 * <summary> 生成日期格式+三位随机数
	 * 
	 * @return yyyyMMddssmmhh+三位随机数
	 */
	public static String getNameByDate() {
		Date param = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(param) + getRandom();
	}
	
	public static void main(String[] args)throws Exception{
		String imageName = getImgName() + "." + "jpg";
//		System.out.println(imageName);
//		String toDir = "E:/tools/tomcat-6.0.33/webapps/IBD_WebService/image/theme/custome/" + imageName; // 存储路径
//		
//		FileInputStream fis=new FileInputStream("D:/123.jpg");
//        DataInputStream dis=new DataInputStream(fis);
//
//        FileOutputStream fos=new FileOutputStream(toDir);
//         DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(fos));
//         byte[] buf=new byte[1024];
//         while((dis.read(buf))>-1)
//         {
//             dos.write(buf);
//             dos.flush();
//         }
//         fis.close();
//         dis.close();
//         fos.close();
//         dos.close();
		
//		FileInputStream fis=new FileInputStream("E:\\z记事本\\Koala.jpg");
//		byte[] b =new byte[1028];
//		System.out.println(b.length);
//		fis.read(b);
	}
}

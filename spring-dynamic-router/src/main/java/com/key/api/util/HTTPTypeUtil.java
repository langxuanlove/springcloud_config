/*   
 * Copyright (c) 2014-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
/**
 * 
 */
package com.key.api.util;

/**
 * @author mst
 * 
 * 
 * @version v1.0
 * 
 *          Created on 2014年10月31日 下午5:05:58
 * 
 *          Revision History: Date Reviser Description
 * 
 *          ----------------------------------------------------
 *          Description:http访问资源类型
 */
public class HTTPTypeUtil {
	private static HTTPTypeUtil httpType = null;

	private HTTPTypeUtil() {
	}

	public static HTTPTypeUtil getInstance() {
		if (httpType == null) {
			httpType = new HTTPTypeUtil();
		}
		return httpType;
	}

	/**
	 * 作者：mst
	 * 
	 * Description: 判断文件类型以获取contentType
	 * 
	 * @param psSuffix
	 *            文件后缀
	 * @return 返回对应的contenttype
	 */
	public String getHttpContentType(String psSuffix) {
		if (".doc".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDoc();
		} else if (".docx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDocx();
		} else if (".docm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDocm();
		} else if (".dot".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDot();
		} else if (".dotx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDotx();
		} else if (".dotm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDotm();
		} else if (".xls".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXls();
		} else if (".xlsx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXlsx();
		} else if (".xlsm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXlsm();
		} else if (".xlt".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXlt();
		} else if (".xltx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXltx();
		} else if (".xltm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXltm();
		} else if (".ppt".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPpt();
		} else if (".pptx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPptx();
		} else if (".pptm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPptm();
		} else if (".pot".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPot();
		} else if (".potx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPotx();
		} else if (".potm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPotm();
		} else if (".pps".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPps();
		} else if (".ppsx".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPpsx();
		} else if (".ppsm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPpsm();
		} else if (".pdf".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPdf();
		} else if (".swf".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getSwf();
		} else if (".js".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getJs();
		} else if (".dll".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getDll();
		} else if (".tar".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getTar();
		} else if (".zip".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getZip();
		} else if (".exe".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getExe();
		} else if (".img".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getImg();
		} else if (".bmp".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getBmp();
		} else if (".gif".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getGif();
		} else if (".jpeg".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getJpeg();
		} else if (".jpg".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getJpg();
		} else if (".jpe".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getJpe();
		} else if (".png".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getPng();
		} else if (".htm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getHtm();
		} else if (".html".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getHtml();
		} else if (".xml".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getXml();
		} else if (".css".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getCss();
		} else if (".txt".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getTxt();
		} else if (".mpeg".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getMpeg();
		} else if (".mpg".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getMpg();
		} else if (".mpe".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getMpe();
		} else if (".mov".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getMov();
		} else if (".avi".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getAvi();
		} else if (".movie".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getMovie();
		} else if (".mp4".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getMp4();
		} else if (".wmv".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getWmv();
		} else if (".wm".equals(psSuffix)) {
			return HTTPTypeUtil.getInstance().getWm();
		}
		return "text/html";
	}

	String doc = "application/msword";
	String docx = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	String docm = "application/vnd.ms-word.document.macroEnabled.12";
	String dot = "application/msword";
	String dotx = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
	String dotm = "application/vnd.ms-word.template.macroEnabled.12";
	String xls = "application/vnd.ms-excel";
	String xlsx = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	String xlsm = "application/vnd.ms-excel.sheet.macroEnabled.12";
	String xlt = "application/vnd.ms-excel";
	String xltx = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
	String xltm = "application/vnd.ms-excel.template.macroEnabled.12";
	String ppt = "application/vnd.ms-powerpoint";
	String pptx = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	String pptm = "application/vnd.ms-powerpoint.presentation.macroEnabled.12";
	String pot = "application/vnd.ms-powerpoint";
	String potx = "application/vnd.openxmlformats-officedocument.presentationml.template";
	String potm = "application/vnd.ms-powerpoint.presentation.macroEnabled.12";
	String pps = "application/vnd.ms-powerpoint";
	String ppsx = "application/vnd.openxmlformats-officedocument.presentationml.slideshow";
	String ppsm = "application/vnd.ms-powerpoint.slideshow.macroEnabled.12";
	String pdf = "application/pdf";
	String swf = "application/x-shockwave-flash";
	String js = "application/x-javascript";
	String dll = "application/octet-stream";
	String tar = "application/x-tar";
	String zip = "application/zip";
	String exe = "application/x-msdownload";

	String img = "application/x-img";
	String bmp = "image/bmp";
	String gif = "image/gif";
	String jpeg = "image/jpeg";
	String jpg = "image/jpeg";
	String jpe = "image/jpeg";
	String png = "image/png";

	String htm = "text/html";
	String html = "text/html";
	String xml = "text/xml";
	String css = "text/css";
	String txt = "text/plain";

	String mpeg = "video/mpeg";
	String mpg = "video/mpeg";
	String mpe = "video/mpeg";
	String mov = "video/quicktime";
	String avi = "video/avi";
	String movie = "video/x-sgi-movie";
	String mp4 = "video/mpeg4";
	String wmv = "video/x-ms-wmv";
	String wm = "video/x-ms-wm";

	public String getDoc() {
		return doc;
	}

	public String getDocx() {
		return docx;
	}

	public static HTTPTypeUtil getHttpType() {
		return httpType;
	}

	public String getDocm() {
		return docm;
	}

	public String getDot() {
		return dot;
	}

	public String getDotx() {
		return dotx;
	}

	public String getDotm() {
		return dotm;
	}

	public String getXls() {
		return xls;
	}

	public String getXlsx() {
		return xlsx;
	}

	public String getXlsm() {
		return xlsm;
	}

	public String getXlt() {
		return xlt;
	}

	public String getXltx() {
		return xltx;
	}

	public String getXltm() {
		return xltm;
	}

	public String getPpt() {
		return ppt;
	}

	public String getPptx() {
		return pptx;
	}

	public String getPptm() {
		return pptm;
	}

	public String getPot() {
		return pot;
	}

	public String getPotx() {
		return potx;
	}

	public String getPotm() {
		return potm;
	}

	public String getPps() {
		return pps;
	}

	public String getPpsx() {
		return ppsx;
	}

	public String getPpsm() {
		return ppsm;
	}

	public String getPdf() {
		return pdf;
	}

	public String getSwf() {
		return swf;
	}

	public String getJs() {
		return js;
	}

	public String getDll() {
		return dll;
	}

	public String getTar() {
		return tar;
	}

	public String getZip() {
		return zip;
	}

	public String getExe() {
		return exe;
	}

	public String getImg() {
		return img;
	}

	public String getBmp() {
		return bmp;
	}

	public String getGif() {
		return gif;
	}

	public String getJpeg() {
		return jpeg;
	}

	public String getJpg() {
		return jpg;
	}

	public String getJpe() {
		return jpe;
	}

	public String getPng() {
		return png;
	}

	public String getHtm() {
		return htm;
	}

	public String getHtml() {
		return html;
	}

	public String getXml() {
		return xml;
	}

	public String getCss() {
		return css;
	}

	public String getTxt() {
		return txt;
	}

	public String getMpeg() {
		return mpeg;
	}

	public String getMpg() {
		return mpg;
	}

	public String getMpe() {
		return mpe;
	}

	public String getMov() {
		return mov;
	}

	public String getAvi() {
		return avi;
	}

	public String getMovie() {
		return movie;
	}

	public String getMp4() {
		return mp4;
	}

	public String getWmv() {
		return wmv;
	}

	public String getWm() {
		return wm;
	}
}

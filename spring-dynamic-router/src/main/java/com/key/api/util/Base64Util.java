package com.key.api.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class Base64Util {
	
	/**
	 * 使用Base64加密算法加密字符串
	 * 
	 * @param plainText
	 * 			加密字符串
	 * @return
	 */
	public static String encode(byte[] pargPlainText) {
		
//		Base64 _base64=new Base64();
//		pargPlainText=_base64.encode(pargPlainText);
		Base64.Encoder encoder = Base64.getEncoder();
		String strPargPlainText = encoder.encodeToString(pargPlainText);
		return strPargPlainText;
	}

	/**
	 * 使用Base64解密
	 * 
	 * @param encodeStr
	 * 			解密字符串
	 * @return
	 */
	public static byte[] decode(String psEncodeStr) {
		
		byte[] _b = psEncodeStr.getBytes();
//		Base64 _base64 = new Base64();
//		_b = _base64.decode(_b);
		Base64.Decoder decoder = Base64.getDecoder();
		_b = decoder.decode(_b);
		return _b;
	}
	
	public String FileToBase64(InputStream psInputStream) throws Exception{
		
		ByteArrayOutputStream _baos = new ByteArrayOutputStream();
		byte[] _bArgBuffer = new byte[8192];
		int _iCount = 0;
		while ((_iCount = psInputStream.read(_bArgBuffer)) >= 0) {
			
			_baos.write(_bArgBuffer, 0, _iCount);
		}
		return Base64Util.encode(_baos.toByteArray());
	}
	
	public OutputStream Base64ToFile(String psBase64Chars) {
		
		byte[] _bfile = Base64Util.decode(psBase64Chars);

		OutputStream _bos = null;
		FileOutputStream _fos = null;
		try {
			_bos = new BufferedOutputStream(_fos);
			_bos.write(_bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (_bos != null) {
				try {
					_bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (_fos != null) {
				try {
					_fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return _bos;
	}
}
package com.key.api.util; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author zhouaiping
 *
 * 
 * @version v1.0
 *
 * Created on 2014年5月7日 下午3:45:06
 *
 * Revision History:
 * Date     Reviser  Description
 * 
 *  ----------------------------------------------------
 * Description:仅供批量导入数据的时候用户名、真实姓名、邮箱的验证，并提示
 */
public class Verification {

	/**
	 * 【作者】zyh
	 * @Description : 检测账号是手机号还是邮箱
	 * @param psAccount
	 * @return 0-既不是手机号也不是邮箱；1-手机号；2-邮箱
	 */
	public static String isPhoneOrEmail(String psAccount){
		String _sIsPhoneOrEmail = "0";
		if(psAccount!=null && !psAccount.equals("")){
			if(psAccount.length()==11&&Verification.checkMobileNumber(psAccount)){
				_sIsPhoneOrEmail = "1";
			}else if(psAccount.indexOf("@")>0){
				if(Verification.checkEmail(psAccount))_sIsPhoneOrEmail = "2";
			}
		}
		return _sIsPhoneOrEmail;
	}
	
	public static String regex(String sUserName,String sUserTrueName,String sMail){
		if(!checkEmail(sUserName) && !checkMobileNumber(sUserName)){
			return "用户名不合法，请输入合法的邮箱或手机号";
		}else if(!checkEmail(sMail)){
			return "邮箱不合法，请输入合法的邮箱";
		}else{			
			return "true";
		}
		
	}
	
	/**
	 * 
	 * 【作者】 zhouaiping
	 * 
	 * @Description:邮箱正则验证
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email){ 
        boolean flag = false; 
        try{ 
        	if(email.indexOf("@")>0){
        		 String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
                 Pattern regex = Pattern.compile(check); 
                 Matcher matcher = regex.matcher(email); 
                 flag = matcher.matches(); 
        	}
        }catch(Exception e){ 
            flag = false; 
        } 
        return flag; 
    } 
	/**
	 * 
	 * 【作者】 zhouaiping
	 * 
	 * @Description：电话号码正则验证
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber){ 
        boolean flag = false; 
        try{ 
                Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$"); 
                Matcher matcher = regex.matcher(mobileNumber); 
                flag = matcher.matches(); 
            }catch(Exception e){ 
                flag = false; 
            } 
        return flag; 
    } 
	//对特殊字符进行转换
	public static String checkString(String psString){
		if(psString!=null && !psString.equals("")){
			String _sResult = psString.replaceAll("<", "&lt;");
			_sResult = _sResult.replaceAll(">", "&gt;");
			return _sResult;
		}else{
			return psString;
		}
	}
//	/**
//	 * 
//	 * 【作者】 zhouaiping
//	 * 
//	 * @Description:真实姓名正则验证
//	 * 
//	 * @param trueName
//	 * @return
//	 */
//	private static boolean checkTrueName(String trueName){ 
//        boolean flag = false; 
//        try{ 
//                Pattern regex = Pattern.compile("^([a-zA-Z]{1,50})$|^([\u4e00-\u9fa5]{2,25})$"); 
//                Matcher matcher = regex.matcher(trueName); 
//                flag = matcher.matches(); 
//            }catch(Exception e){ 
//                flag = false; 
//            } 
//        return flag; 
//    } 
}

package cn.scau.zzzd.xst.util;

import java.security.MessageDigest;


/**
 * 字符工具类
 * @author gccd
 * 2013-12-13
 */
public class StringUtil {
	/**
	 * 判断是否为空
	 * @param cids
	 * @return
	 */
	public static boolean isBlank(String cids) {
		if(cids==null)
			return true;
		if(cids.trim().equals(""))
			return true;
		return false;
	}
	/**
	 * 首字母大写
	 * @param srcstr
	 * @return
	 */
	public static String upFirstLeter(String srcstr){
		return srcstr.replaceFirst(srcstr.substring(0, 1),srcstr.substring(0, 1).toUpperCase()) ;
	}
	/**
	 * 是否符合username格式
	 * @param username
	 * @return
	 */
	public static boolean isUsernameFormat(String username){
		return true;
	}
	/**
	 * 是否符合password格式
	 * @param password
	 * @return
	 */
	public static boolean isPasswordFormat(String password){
		return true;
	}
	/**
	 * 过长截断
	 * @param s
	 * @param maxLength
	 * @param append
	 * @return
	 */
	public static String subString(String s,int maxLength,String append){
		if(s==null)
			return "";
		if( maxLength<=0 || s.length() < maxLength)
			return s;
		return s.substring(0, maxLength)+append;
	}
	/**
	 * 过长截断
	 * @param s
	 * @param maxLength
	 * @return
	 */
	public static String subString(String s,int maxLength){
		return subString(s,maxLength,"...");
	}
	/**
	 * md5 加密 
	 * @param str
	 * @return
	 */
	public final static String md5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String ucfirst (String str) {
		if (str != null && str != "") {
			str  = str.substring(0,1).toUpperCase()+str.substring(1);
		}
		return str;
	}
	/**
	 * 根据url获取文件名
	 * @param url
	 * @return
	 */
	public static String getFilenameFromUrl(String url){
		if(url == null)
			return null;
		String[] temps = url.split("/");
		String filename = temps[temps.length-1];
		return filename;
	}
	
}

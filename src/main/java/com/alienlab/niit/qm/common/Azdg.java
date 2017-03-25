package com.alienlab.niit.qm.common;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Set;


public class Azdg {
	//密钥，可自定�?
	String key = new PropertyConfig("sysConfig.properties").getValue("syskey");
    public static void main(String[] args) {
       Azdg azdg = new Azdg();
   

       String cipherText = azdg.encrypt("123456");
       System.out.println("加密后为"+cipherText);
       String cleanText = azdg.decrypt(cipherText);
       System.out.println("解密后"+cleanText);

    }

    

    /**
     * 加密算法
     * @param txt
     * @return
     */
    public String encrypt(String txt) {
       String encrypt_key = new PropertyConfig("sysConfig.properties").getValue("syskey");
       int ctr = 0;
       String tmp = "";
       for (int i = 0; i < txt.length(); i++) {
    	   ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
           tmp = tmp + encrypt_key.charAt(ctr) + (char)(txt.charAt(i) ^ encrypt_key.charAt(ctr));
           ctr++;

       }
       return base64_encode(key(tmp, key));

    }
    public String encrypt(String txt , String key_) {
        String encrypt_key =key_;
        int ctr = 0;
        String tmp = "";
        for (int i = 0; i < txt.length(); i++) {
     	   ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
            tmp = tmp + encrypt_key.charAt(ctr) + (char)(txt.charAt(i) ^ encrypt_key.charAt(ctr));
            ctr++;

        }
        return base64_encode(key(tmp, key_));

     }

    
    /**
     * 解密算法
     * @param cipherText
     * @return
     */
    public  String decrypt(String cipherText) {
       // base64解码
       cipherText = base64_decode(cipherText);
       cipherText = key(cipherText, key);
       String tmp = "";

       for (int i = 0; i < cipherText.length(); i++) {
           int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
           String x = "" + (char) c;
           tmp += x;
           i++;

       }

       return tmp;

    }
    public  String decrypt(String cipherText ,String key_) {
        // base64解码
        cipherText = base64_decode(cipherText);
        cipherText = key(cipherText, key_);
        String tmp = "";

        for (int i = 0; i < cipherText.length(); i++) {
            int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
            String x = "" + (char) c;
            tmp += x;
            i++;

        }

        return tmp;

     }
 

    public String key(String txt, String encrypt_key) {
       encrypt_key = strMD5(encrypt_key);
       int ctr = 0;
       String tmp = "";
       for (int i = 0; i < txt.length(); i++) {
           ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
           int c = txt.charAt(i) ^ encrypt_key.charAt(ctr);
           String x = "" + (char) c;
           tmp = tmp + x;
           ctr++;
       }

       return tmp;

    }

 

    public String base64_encode(String str) {

       return new sun.misc.BASE64Encoder().encode(str.getBytes());

    }

    

    public String base64_decode(String str) {
       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
       if (str == null)
           return null;
       try {
           return new String(decoder.decodeBuffer(str));
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }

    }

 

    public static final String strMD5(String s) {
       char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
              'a', 'b', 'c', 'd', 'e', 'f','h','o','t'};
       try {
           byte[] strTemp = s.getBytes();
           MessageDigest mdTemp = MessageDigest.getInstance("MD5");
           mdTemp.update(strTemp);
           byte[] md = mdTemp.digest();
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
       }
       return null;
    }
  
  
    //生成token
    public static String getToken(String key_ , String config){
    	Azdg azdg =new Azdg();
    	System.out.println(config);
    	JSONObject jsonObject = (JSONObject) JSONObject.parse(config);
    	Set<String> keys = jsonObject.keySet();
    	Iterator<String> iterator = keys.iterator();
    	String token_ = "";
    	while(iterator.hasNext()){
    		String value = iterator.next(); 
    		//替换为用户输入的key
    		token_ += azdg.encrypt(value, key_);
    	}
    	return token_;
    }
    //生成token
    public static String getToken(String key_ , JSONObject config){
    	Azdg azdg =new Azdg();
    	System.out.println(config);
    	Set<String> keys = config.keySet();
    	Iterator<String> iterator = keys.iterator();
    	String token_ = "";
    	while(iterator.hasNext()){
    		String value = iterator.next(); 
    		//替换为用户输入的key
    		token_ += azdg.encrypt(value, key_);
    	}
    	return token_;
    }
}


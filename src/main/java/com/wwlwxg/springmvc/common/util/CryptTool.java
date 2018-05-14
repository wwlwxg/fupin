package com.wwlwxg.springmvc.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具封装. CryptTool 封装了一些加密工具方法, 包括 3DES, MD5 等.
 * 
 * @author rocken.zeng@gmail.com(整理)
 * @version 1.0
 */
public class CryptTool {

	/**
	 * 构造函数.
	 * 
	 */
	public CryptTool() {
	}

	private static final String Algorithm = "DESede";
	
    //keybyte为加密密钥，长度为24字节
    //src为被加密的数据缓冲区（源）
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //keybyte为加密密钥，长度为24字节
    //src为加密后的缓冲区
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
	
	
	/**
	 * MD5 摘要计算(byte[]).
	 * 
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5Digest(byte[] src) {
		byte[] bytes = null;
		try {
			java.security.MessageDigest alg = java.security.MessageDigest
					.getInstance("MD5");
			bytes = alg.digest(src); 
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		return bytes;
	}

	/**
	 * MD5 摘要计算(String).
	 * 
	 * @param src
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String md5Digest(String src) {
		return new String(md5Digest(src.getBytes()));
	}

	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		byte[] byteArray = null;
		try {
			byteArray = md5Digest(str.getBytes("" +
					"utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		StringBuffer md5StrBuff = new StringBuffer();  
        for (int i = 0; i < byteArray.length; i++) {               
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)   
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));   
            else   
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));   
        } 
		return md5StrBuff.toString().toUpperCase();
	}

	/**
	 * 对给定字符进行 URL 编码.
	 * 
	 * @param src
	 *            String
	 * @return String
	 */
	public static String urlEncode(String src,String charset) {
		try {
			src = java.net.URLEncoder.encode(src, charset);

			return src;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return src;
	}

	/**
	 * 对给定字符进行 URL 解码
	 * 
	 * @param value
	 *            解码前的字符串
	 * @return 解码后的字符串
	 */
	public static String urlDecode(String value,String charset) {
		try {
			return java.net.URLDecoder.decode(value, charset);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return value;
	}
	
	private static Log log = LogFactory.getLog(CryptTool.class);
	
	public static void main(String[] args){
        //添加新安全算法,如果用JCE就要把它添加进去
		BASE64Encoder bn=new BASE64Encoder();
		BASE64Decoder bd = new BASE64Decoder();
		
        Security.addProvider(new com.sun.crypto.provider.SunJCE());

//        final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38
//                               , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
//                               , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};    //24字节的密钥
        byte[] keyBytes = "DA防daaaeeee22222211111".getBytes();
        String szSrc = "This is a 3DES test. 测试";
        
        System.out.println("加密前的字符串:" + szSrc);
        
        byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
//        System.out.println("加密后的字符串:" + new String(encoded));
//        String enUrl = CryptTool.urlEncode(new String(encoded),"utf-8");
//        System.out.println("url加密后的字符串:" + enUrl);
//        
//        String deUrl = CryptTool.urlDecode(enUrl,"utf-8");
//        System.out.println("url解密后的字符串:" + deUrl);
//        byte[] _encoded = null;
//        try {
//			_encoded = deUrl.getBytes("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        System.out.println(_encoded.length);
//        System.out.println(encoded.length);
        
        String bn1 = bn.encode(encoded);
        System.out.println("base64加密后的字符串:" + bn1);
        
        byte[] _encoded = null;
        try {
        	_encoded=bd.decodeBuffer(bn1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] srcBytes = decryptMode(keyBytes, _encoded);
        System.out.println("解密后的字符串:" + (new String(srcBytes)));
	}
}

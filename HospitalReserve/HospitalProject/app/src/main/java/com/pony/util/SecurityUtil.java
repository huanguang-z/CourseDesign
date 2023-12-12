package com.pony.util;

import java.security.Key;

import javax.crypto.Cipher;

	public class SecurityUtil {
	    private static String strDefaultKey = "test_zyt";
	    private Cipher encryptCipher = null;
	    private Cipher decryptCipher = null;

	    /**
	     * 使用默认的key
	     */
	    public SecurityUtil() throws Exception {
	        this(strDefaultKey);
	    }

	    /**
	     * 使用默认加密算法
	     */
	    public byte[] encrypt(byte[] arrB) throws Exception {
	        return encryptCipher.doFinal(arrB);// 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此 Cipher 的初始化方式）。
	    }

	    /**
	     * 使用自定义加密加密算法
	     */
	    public String encrypt(String strIn) throws Exception {
	        return byteArr2HexStr(encrypt(strIn.getBytes()));
	    }

	    /**
	     * 使用默认解密算法，与encrypt(byte[] arrB)对应
	     */
	    public byte[] decrypt(byte[] arrB) throws Exception {
	        return decryptCipher.doFinal(arrB);
	    }

	    /**
	     * 使用自定义加密解密算法，与encrypt(String strIn)对应
	     */
	    public String decrypt(String strIn) throws Exception {
	        return new String(decrypt(hexStr2ByteArr(strIn)));
	    }

	    /**
	     * 使用自定义key
	     */
	    public SecurityUtil(String strKey) {
	        Key key;
	        try {
	            key = getKey(strKey.getBytes());
	            encryptCipher = Cipher.getInstance("DES");
	            encryptCipher.init(Cipher.ENCRYPT_MODE, key);// 初始化加密模式
	            decryptCipher = Cipher.getInstance("DES");
	            decryptCipher.init(Cipher.DECRYPT_MODE, key);// 初始化解密模式
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 生成密钥
	     */
	    private Key getKey(byte[] arrBTmp) throws Exception {
	        // 创建一个空的8位字节数组（默认值为0）
	        byte[] arrB = new byte[8];
	        // 将原始字节数组转换为8位
	        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
	            arrB[i] = arrBTmp[i];
	        }
	        // 生成密钥
	        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
	        return key;
	    }

	    public static String byteArr2HexStr(byte[] arrB) throws Exception {
	        int iLen = arrB.length;
	        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
	        StringBuffer sb = new StringBuffer(iLen * 2);
	        for (int i = 0; i < iLen; i++) {
	            int intTmp = arrB[i];
	            // 把负数转换为正数
	            while (intTmp < 0) {
	                intTmp = intTmp + 256;
	            }
	            // 小于0F的数需要在前面补0
	            if (intTmp < 16) {
	                sb.append("0");
	            }
	            sb.append(Integer.toString(intTmp, 16));
	        }
	        return sb.toString();
	    }

	    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
	        byte[] arrB = strIn.getBytes();
	        int iLen = arrB.length;
	        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
	        byte[] arrOut = new byte[iLen / 2];
	        for (int i = 0; i < iLen; i = i + 2) {
	            String strTmp = new String(arrB, i, 2);
	            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
	        }
	        return arrOut;
	    }

	  
	}
package com.huanke.sql;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Administrator
 *
 */
public class Md5Encryption {

	/**
	 * md5密码加密
	 * 
	 * @param message
	 *            需要加密的信息
	 * @return String 加密后的信息16进制表示
	 */
	public static String encryption(String message) {
		byte[] md5Sequence = null;
		String password = null;
		try {
			md5Sequence = encodeMd5(message);
			password = bytes2HexString(md5Sequence);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * 生成md5散列
	 * 
	 * @param String
	 * @return byte[]
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeMd5(String s) throws NoSuchAlgorithmException {
		byte[] obj = s.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(obj);
		return md5.digest();
	}

	// 将数组转成16进制string
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex;
		}
		return ret;
	}
}

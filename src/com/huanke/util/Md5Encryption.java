package com.huanke.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
		String encryptedInfo = null;
		try {
			md5Sequence = encodeMd5(message);
			encryptedInfo = Bytes2HexString.bytes2HexString(md5Sequence);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedInfo;
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

	/**
	 * 加密文件 2015年7月1日 上午12:06:37
	 */
	public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	/**
	 * 
	 * @funcion 对文件全文生成MD5摘要
	 * 
	 * @param fis
	 * 
	 * @return MD5摘要码
	 * 
	 */

	public static String getMD5(InputStream fis) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			byte[] b = md.digest();
			return Bytes2HexString.bytes2HexString(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

			try {

				fis.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}
}

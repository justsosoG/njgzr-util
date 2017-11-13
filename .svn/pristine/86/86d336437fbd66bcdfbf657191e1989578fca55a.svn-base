package com.njgzr.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author Justsoso丶G
 * @description （一句话描述作用）
 * @version 2017年11月8日上午11:33:33
 */
public class StreamTransUtil {

	/**
	 * byte字节组转InputStream
	 * 
	 * @param buf
	 * @return
	 */
	public static InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	/**
	 * InputStream转byte字节组
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	/**
	 * String转InputStream
	 * 
	 * @param str
	 * @return
	 */
	public static InputStream String2InputStream(String str) {
		ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
		return stream;
	}

	/**
	 * inputStream转String
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	
	/**
	 * 文件转InputStream
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream file2InputStream(File file)
			throws FileNotFoundException {
		InputStream in = new FileInputStream(file);
		return in;
	}

	
	/**
	 * inputstream转文件
	 * @param ins
	 * @param path（欲存储文件的路径）
	 * @throws IOException
	 */
	public static void inputstreamtofile(InputStream ins, String path)throws IOException {
		File file = new File(path);
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}

}

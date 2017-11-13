package com.njgzr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 上传到七牛云
 * @author Administrator
 *
 */
@Slf4j
public class UploadUtil {

	// 设置好账号的ACCESS_KEY和SECRET_KEY
	static String ACCESS_KEY = "zNmjRIgTGoOiszTGgud51XigNCKWWzzfhI9Dws-Q"; // 这两个登录七牛
																			// 账号里面可以找到
	static String SECRET_KEY = "dt87Yu2nCNAQFbVySw9U72NG3ih2WQLKeJD7rhVZ";

	static Configuration cfg = new Configuration(Zone.zone0());

	// 要上传的空间
	static String bucketname = "justsoso"; // 对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）
	// 上传到七牛后保存的文件名
	// static String key = null;
	// 上传文件的路径
	// static String FilePath = "d:\\data\\111.jpeg"; //本地要上传文件路径

	// 密钥配置
	static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	static UploadManager uploadManager = new UploadManager(cfg);

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	// 普通上传
	public static void upload(String FilePath, String key) throws IOException {
		log.info("准备上传文件");
		try {
			// 调用put方法上传
			Response res = uploadManager.put(FilePath, key, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	public static void uploadInputStream(InputStream stream, String key)
			throws UnsupportedEncodingException {
		log.info("准备上传文件流");
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String upToken = auth.uploadToken(bucketname);
		try {
			Response response = uploadManager.put(stream, key, upToken, null,
					null);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),
					DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
	}

	public static void uploadBytes(byte[] uploadBytes, String key)
			throws UnsupportedEncodingException {
		log.info("准备上传文件字节流");
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String upToken = auth.uploadToken(bucketname);
		try {
			Response response = uploadManager.put(uploadBytes, key, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),
					DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
	}

	public static void main(String args[]) throws IOException {
		String FilePath = "d:\\data\\111.jpeg"; // 本地要上传文件路径
		File f = new File(FilePath);
		InputStream in = new FileInputStream(f);
		String key = "meinv3344.png";// 传入后需要保存的名字
		// upload(FilePath,key);//直接上传文件
		uploadInputStream(in, key);// 上传流
	}

}
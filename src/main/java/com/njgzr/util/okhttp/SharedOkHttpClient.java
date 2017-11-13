/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-05-26 
 * Date         Author        Version        Discription    
 * 2017年5月26日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.util.okhttp;


import java.io.IOException;
import java.net.Proxy;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.njgzr.util.okhttp.commen.HttpResponse;
import com.njgzr.util.okhttp.commen.NetworkException;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName:     SharedOkHttpClient
 * @Description: 全局唯一的OKHttpClient实例，确保全局共享Http连接池和公共配置
 *               ,基于此全局唯一的根实例为每一个Account创建一个OKHttpClient实例
 * @author rocky
 * @date:   2017年3月8日 上午9:37:13 
 *
 */
@Component
public final class SharedOkHttpClient {

	private static final Logger logger = LoggerFactory.getLogger(SharedOkHttpClient.class);

	public static void main(String[] args) {
		Request.Builder builder = new Request.Builder();

		try {
			SharedOkHttpClient client = new SharedOkHttpClient();
			Response resp = client.realClient(null, null)
					.newCall(builder
							.url("http://comment.news.163.com/api/v1/products/a2869674571f77b5a0867c3d71db5856/threads/CHJEK89F000189FH?callback=getData&ibc=newspc")
							.build())
					.execute();
			Headers j = resp.networkResponse().request().headers();
			System.out.println(j);
		} catch (IOException e) {
			logger.error("",e) ;
		}
	}

	/**
	 * 根OkHttpClient,全局唯一
	 */
	private OkHttpClient coreCilent = null;

	private boolean supportHttps = false;

	/**
	 * Cookie持久化服务
	 */
	@Autowired(required=false)
	private CookieService cookieService;

	@PostConstruct
	public void init(){
		OkHttpClient.Builder builder = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS).addInterceptor(new Interceptor() {
					public Response intercept(Chain chain) throws IOException {
						Request req = chain.request().newBuilder()
								.addHeader("User-Agent",
										"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
								.addHeader("Accept-Language", "zh-CN,en-US;q=0.8,en;q=0.6")
								.addHeader("Connection", "keep-alive")  
                                .addHeader("Accept", "*/*")
								.addHeader("Cache-Control", "no-cache").build();
						return chain.proceed(req);
					}
				});
		enableHttps(builder);
		this.coreCilent = builder.build();
	}

	/**   
	 * @param builder      
	 */ 
	private void enableHttps(OkHttpClient.Builder builder) {
		try {
			X509TrustManager trustManager = new TrustAllManager();
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { trustManager }, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			builder.sslSocketFactory(sslSocketFactory, trustManager).hostnameVerifier(DO_NOT_VERIFY);
			supportHttps = true;
		} catch (Exception e) {
			logger.warn("SharedOkHttpClient enable Https fail", e);
		}
	}

	public List<Cookie> findCookies(String cookieStoreKey){
		if(StringUtils.isBlank(cookieStoreKey))
			return Lists.newArrayList();
		return cookieService.reloadCookie(cookieStoreKey);
	}
	
	
	
	/**
	 * @param cookieService
	 *            the cookieService to set
	 */
	public void setCookieService(CookieService cookieService) {
		this.cookieService = cookieService;
	}

	/**
	 * @return the supportHttps
	 */
	public boolean isSupportHttps() {
		return supportHttps;
	}
	public OkHttpClient realClient(){
		return this.coreCilent;
	}
	/**
	 * @Title: fetchAccountClient
	 * @Description: 根据key获取OkHttpClient实例
	 * @param key
	 * @return
	 */
	public OkHttpClient realClient(String cookieKey,Proxy proxy) {
		if (StringUtils.isBlank(cookieKey)&&proxy==null)
			return this.coreCilent;
		OkHttpClient.Builder builder = this.coreCilent.newBuilder();
		if(this.cookieService==null)
			this.cookieService = new DefaultCookieService();
		if(StringUtils.isNotBlank(cookieKey)){
			builder.cookieJar(new CookieJarImpl(cookieKey, this.cookieService));
		}
		if(proxy!=null){
			builder.proxy(proxy);
		}			
		return builder.build();
	}
	public HttpResponse doGet(String url) throws NetworkException{
		return doGet(null,null,null,url);
	}
	public HttpResponse doGet(String referer,String url) throws NetworkException{
		return doGet(null,null,referer,url);
	}
	public HttpResponse doGet(String cookieKey,Proxy proxy,String referer,String url) throws NetworkException{
		RequestBuilder builder = new RequestBuilder();
		Request req = builder.url(url).referer(referer).build();
		return execute(cookieKey,proxy, req);
	}
	public HttpResponse execute(String cookieKey,Proxy proxy,Request req) throws NetworkException{
		return executeHttpMethod(realClient(cookieKey,proxy), req);
	}
	public HttpResponse execute(Request req) throws NetworkException{
		return executeHttpMethod(coreCilent, req);
	}
	/**   
	 * @Title:       executeHttpMethod   
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param client
	 * @param url
	 * @param req
	 * @return
	 * @throws NetworkException      
	 */ 
	public HttpResponse executeHttpMethod(OkHttpClient client, Request req) throws NetworkException {
		String url = req.url().toString();
		try(Response response = client.newCall(req).execute()){
  			logger.debug(req.method()+" URL:"+url+",code="+response.code());
			if(!response.isSuccessful()){
				logger.info("服务器异常，返回码:"+response.code());
			}
			ResponseReader reader = new ResponseReader(response);			
			return reader.getResult();
		}catch (Exception e) {
			logger.error(req.method()+" URL:"+url+",网络异常："+e.getMessage());
			throw new NetworkException("网络异常："+e.getMessage());
		}
	}
	
	/**   
	 * @Title:       executeHttpMethod   
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param client
	 * @param url
	 * @param req
	 * @return
	 * @throws NetworkException      
	 */ 
	public Response executeRequestMethod(OkHttpClient client, Request req) throws NetworkException {
		String url = req.url().toString();
		try(Response response = client.newBuilder().followRedirects(false).build().newCall(req).execute()){
  			logger.debug(req.method()+" URL:"+url+",code="+response.code());
			if(!response.isSuccessful()){
				logger.info("服务器异常，返回码:"+response.code());
			}
			return response;
		}catch (Exception e) {
			logger.error(req.method()+" URL:"+url+",网络异常："+e.getMessage());
			throw new NetworkException("网络异常："+e.getMessage());
		}
	}
	
	public HttpResponse doPostJson(String jsonBody,String url)throws NetworkException{
		return doPostJson(null,null,null,jsonBody,url);
	}
	public HttpResponse doPostForm(Map<String,String> formValues,String url)throws NetworkException{
		return doPostForm(null,null,null,null,formValues,url);
	}
	protected HttpResponse doPostJson(String cookieKey,Proxy proxy,String referer,String jsonBody,String url) throws NetworkException{
		RequestBuilder builder = new RequestBuilder();
		Request req = null;
		if(jsonBody!=null&&!jsonBody.isEmpty()){
			req=builder.url(url).referer(referer).postJson(jsonBody).build();
		}else{
			req=builder.url(url).referer(referer).build();
		}
		return executeHttpMethod(realClient(cookieKey,proxy), req);
	}
	protected HttpResponse doPostForm(String cookieKey,Proxy proxy,String referer,String origin,Map<String,String> formValues,String url) throws NetworkException{	
		RequestBuilder builder = new RequestBuilder();	
		Request req = builder.url(url).referer(referer).postFormValue(formValues).build();
		return executeHttpMethod(realClient(cookieKey,proxy), req);
	}
	
	
	/**
	 * @ClassName:     CookieJarImpl
	 * @Description: Cookie实现类 
	 * @author rocky
	 * @date:   2017年3月8日 下午1:47:56 
	 */
	class CookieJarImpl implements CookieJar {

		private String cookieKey = null;

		private CookieService cookieService = null;

		public CookieJarImpl(String key, CookieService cookieService) {
			this.cookieKey = key;
			this.cookieService = cookieService;
		}

		/**
		 *  
		 * <p>
		 * Title: saveFromResponse
		 * </p>
		 *   
		 * <p>
		 * Description: 
		 * </p>
		 *   
		 * 
		 * @param url
		 * @param cookies 
		 * @see okhttp3.CookieJar#saveFromResponse(okhttp3.HttpUrl,
		 *      java.util.List)  
		 */
		@Override
		public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
			this.cookieService.saveCookie(cookies, this.cookieKey);
		}

		/**
		 *  
		 * <p>
		 * Title: loadForRequest
		 * </p>
		 *   
		 * <p>
		 * Description: 
		 * </p>
		 *   
		 * 
		 * @param url
		 * @return 
		 * @see okhttp3.CookieJar#loadForRequest(okhttp3.HttpUrl)  
		 */
		@Override
		public List<Cookie> loadForRequest(HttpUrl url) {
			return this.cookieService.reloadCookie(this.cookieKey);
		}

	}

	private static class TrustAllManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
}

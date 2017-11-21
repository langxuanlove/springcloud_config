package com.key.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.config.RequestConfig.Builder;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.CoreProtocolPNames;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：封装了一些采用HttpClient发送HTTP请求的方法
 */
public class HttpUtil {

//	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
//	
//	//连接超时3秒，默认重发3次
//	private static HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(getRequestConfig()).build();
//	
//	private HttpUtil() {}
//	
//	private static RequestConfig getRequestConfig() {
//		
//		Builder custom = RequestConfig.custom();
//		
//		//连接3秒超时时间
//		custom.setConnectionRequestTimeout(30000);
//		custom.setConnectTimeout(30000);
//		custom.setSocketTimeout(30000);
//		RequestConfig config = custom.build();
//		
//		return config;
//		
//	}
//
//	/**
//	 * 描述：发送HTTP_GET请求,该方法会自动关闭连接,释放资源
//	 * 
//	 * @param psReqURL
//	 *            请求地址(含参数)
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	@SuppressWarnings({"unused" })
//	public static String sendGetRequest(String psReqURL) {
//		long responseLength = 0; // 响应长度
//		String responseContent = null; // 响应内容
//		HttpGet httpGet = new HttpGet(psReqURL); // 创建org.apache.http.client.methods.HttpGet
//		try {
//			HttpResponse response = httpClient.execute(httpGet); // 执行GET请求
//			HttpEntity entity = response.getEntity(); // 获取响应实体
//			if (null != entity) {
//				responseLength = entity.getContentLength();
//				responseContent = EntityUtils.toString(entity, "UTF-8");
//				EntityUtils.consume(entity); // Consume response content
//			}
//		} catch (ClientProtocolException e) {
//			logger.error("该异常通常是协议错误导致,比如构造HttpGet对象时传入的协议不对(将'http'写成'htp')或者服务器端返回的内容不符合HTTP协议要求等,堆栈信息如下", e);
//		} catch (ParseException e) {
//			logger.error(e.getMessage(), e);
//		} catch (IOException e) {
//			logger.error("该异常通常是网络原因引起的,如HTTP服务器未启动等,堆栈信息如下", e);
//		} finally {}
//		return responseContent;
//	}
//
//	/**
//	 * 描述：发送HTTP_GET请求, 该方法会自动关闭连接,释放资源
//	 * 
//	 * @param psReqURL
//	 *            请求地址(含参数)
//	 * @param psDecodeCharset
//	 *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	@SuppressWarnings({"unused" })
//	public static String sendGetRequest(String psReqURL, String psDecodeCharset) {
//		long responseLength = 0; // 响应长度
//		String responseContent = null; // 响应内容
//		HttpGet httpGet = new HttpGet(psReqURL); // 创建org.apache.http.client.methods.HttpGet
//		try {
//			HttpResponse response = httpClient.execute(httpGet); // 执行GET请求
//			HttpEntity entity = response.getEntity(); // 获取响应实体
//			if (null != entity) {
//				responseLength = entity.getContentLength();
//				responseContent = EntityUtils.toString(entity, psDecodeCharset == null ? "UTF-8" : psDecodeCharset);
//				EntityUtils.consume(entity); // Consume response content
//			}
//		} catch (ClientProtocolException e) {
//			logger.error("该异常通常是协议错误导致,比如构造HttpGet对象时传入的协议不对(将'http'写成'htp')或者服务器端返回的内容不符合HTTP协议要求等,堆栈信息如下", e);
//		} catch (ParseException e) {
//			logger.error(e.getMessage(), e);
//		} catch (IOException e) {
//			logger.error("该异常通常是网络原因引起的,如HTTP服务器未启动等,堆栈信息如下", e);
//		} finally {}
//		return responseContent;
//	}
//
//	/**
//	 * 描述：发送HTTP_POST请求,该方法为sendPostRequest(String,String,boolean,String,String)
//	 * 的简化方法,该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8,当<code>isEncoder=true</code>
//	 * 时,其会自动对sendDat中的[中文][|][ ]等特殊字符进行 URLEncoder.encode(string,"UTF-8"
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param sendData
//	 *            请求参数,若有多个参数则应拼接成param11=value11m22=value22m33=value33的形式后,
//	 *            传入该参数中
//	 * @param isEncoder
//	 *            用于指明请求数据是否需要UTF-8编码,true为需要
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder) {
//		return sendPostRequest(reqURL, sendData, isEncoder, null, null);
//		/*int i = 0;
//		String response = null;
//		
//		while (true) {
//			
//			try {
//	        	
//	        	response = sendPostRequest(reqURL, sendData, isEncoder, null, null);
//	        } catch (Exception e) {
//	        	
//	        	if (i > 1) {
//	            	
//	            	break;
//	            } else {
//	            	
//	            	try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
//	            	i++;
//	            }
//	        }
//			if (response != null ) {
//				
//				break;
//			} else {
//				
//				if (i > 1) {
//	            	
//	            	break;
//	            } else {
//	            	
//	            	try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
//	            	i++;
//	            }
//			}
//		}
//		
//		logger.info("当前请求重试：" + i);
//		return response;*/
//	}
//	
//	public static StringBuffer sendPutRequest(String reqURL, String sendData) {
//		return sendPutRequest(reqURL, sendData, null, null);
//	}
//
//	/**
//	 * 描述：发送HTTP_POST请求,该方法会自动关闭连接、释放资源,当isEncoder=true时,其会自动对sendData中的[中文][|][
//	 * ]等特殊字符进行URLEncoder.encode(string,encodeCharset)
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param sendData
//	 *            请求参数,若有多个参数则应拼接成param11=value11m22=value22m33=value33的形式后,
//	 *            传入该参数中
//	 * @param isEncoder
//	 *            请求数据是否需要encodeCharset编码,true为需要
//	 * @param encodeCharset
//	 *            编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
//	 * @param decodeCharset
//	 *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset, String decodeCharset) {
//		
//		String responseContent = null;
//		
//		HttpPost httpPost = new HttpPost(reqURL);
//		//'*/*'代表的可接受的格式：application/json;application/x-www-form-urlencoded;
//		// text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,;q=0.5等
//		httpPost.setHeader(HTTP.CONTENT_TYPE,  "*/*; charset=UTF-8");
//		try {
//			if (isEncoder) {
//				
//				List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//				for (String str : sendData.split("&")) {
//					
//					formParams.add(new BasicNameValuePair(str.substring(0, str.indexOf("=")), str.substring(str.indexOf("=") + 1)));
//				}
//				httpPost.setEntity(new StringEntity(URLEncodedUtils.format(formParams, encodeCharset == null ? "UTF-8" : encodeCharset)));
//			} else {
//				httpPost.setEntity(new StringEntity(sendData, "UTF-8"));
//			}
//			
//			HttpResponse response = httpClient.execute(httpPost);
//			if (null == response) {
//				
//				return responseContent;
//			}
//			HttpEntity entity = response.getEntity();
//			if (null != entity) {
//				
//				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
//				EntityUtils.consume(entity);
//			}
//			return responseContent;
//		} catch (Exception e) {
//			
//			//logger.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下:", e);
//			return responseContent;
//		}
//	}
//	
//	public static StringBuffer sendPutRequest(String reqURL, String sendData, String encodeCharset,
//			String decodeCharset) {
//		
//		StringBuffer responseContent = new StringBuffer();
//		HttpPut httpPut = new HttpPut(reqURL);
//		httpPut.setHeader(HTTP.CONTENT_TYPE, "*/*");
//		try {
//			StringEntity params =new StringEntity(sendData.toString());
//			httpPut.setEntity(params);
//			
//			HttpResponse response = httpClient.execute(httpPut);
//			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//			String line = "";
//			while ((line = rd.readLine()) != null) {
//				responseContent.append(line);
//			}
//		} catch (Exception e) {
//			logger.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下", e);
//			e.printStackTrace();
//		} finally {
//		}
//		return responseContent;
//	}
//
//	/**
//	 * 描述：发送HTTP_POST请求，该方法会自动关闭连接,释放资源，该方法会自动对params中的[中文][|][
//	 * ]等特殊字符进行URLEncoder.encode(string,encodeCharset)
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param params
//	 *            请求参数
//	 * @param encodeCharset
//	 *            编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
//	 * @param decodeCharset
//	 *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	public static String sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset,
//			String decodeCharset) {
//		String responseContent = null;
//
//		HttpPost httpPost = new HttpPost(reqURL);
//		List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列
//		for (Map.Entry<String, String> entry : params.entrySet()) {
//			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//		}
//		try {
//			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));
//
//			HttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity();
//			if (null != entity) {
//				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
//				EntityUtils.consume(entity);
//			}
//		} catch (Exception e) {
//			logger.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下", e);
//		} finally {
//		}
//		return responseContent;
//	}
//
//	/**
//	 * 描述：发送HTTPS_POST请求，该方法为sendPostSSLRequest(String,Map<String,String>,String
//	 * ,String)方法的简化方法，该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8，该方法会自动对params中的[中文][
//	 * |][]等特殊字符进行URLEncoder.encode(string,"UTF-8")
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param params
//	 *            请求参数
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	public static String sendPostSSLRequest(String reqURL, Map<String, String> params) {
//		return sendPostSSLRequest(reqURL, params, null, null);
//	}
//
//	/**
//	 * 发送HTTPS_POST请求，该方法会自动关闭连接,释放资源，该方法会自动对<code>params</code>
//	 * 中的[中文][|][]等特殊字符进行URLEncoder.encode(string,encodeCharset)
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param params
//	 *            请求参数
//	 * @param encodeCharset
//	 *            编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
//	 * @param decodeCharset
//	 *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
//	 * 
//	 * @return 远程主机响应正文
//	 */
//	public static String sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset,
//			String decodeCharset) {
//		String responseContent = "";
//		X509TrustManager xtm = new X509TrustManager() {
//			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			}
//
//			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			}
//
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//		};
//		try {
//			SSLContext ctx = SSLContext.getInstance("TLS");
//			ctx.init(null, new TrustManager[] { xtm }, null);
//			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
//			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
//
//			HttpPost httpPost = new HttpPost(reqURL);
//			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//			for (Map.Entry<String, String> entry : params.entrySet()) {
//				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//			}
//			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));
//
//			HttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity();
//			if (null != entity) {
//				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
//				EntityUtils.consume(entity);
//			}
//		} catch (Exception e) {
//			logger.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息为", e);
//		} finally {}
//		
//		return responseContent;
//	}
//
//	/**
//	 * 描述：发送HTTP_POST请求，若发送的params中含有中文,记得按照双方约定的字符集将中文URLEncoder.encode(string,
//	 * encodeCharset)本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param params
//	 *            发送到远程主机的正文数据,其数据类型为java.util.Map<String, String>
//	 * 
//	 * @return 远程主机响应正文`HTTP状态码,如"SUCCESS`200"，若通信过程中发生异常则返回"Failed`HTTP状态码",如
//	 *         "Failed`500"
//	 */
//	public static String sendPostRequestByJava(String reqURL, Map<String, String> params) {
//		StringBuilder sendData = new StringBuilder();
//		for (Map.Entry<String, String> entry : params.entrySet()) {
//			sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//		}
//		if (sendData.length() > 0) {
//			sendData.setLength(sendData.length() - 1); // 删除最后一个&符号
//		}
//		return sendPostRequestByJava(reqURL, sendData.toString());
//	}
//
//	/**
//	 * 描述：发送HTTP_POST请求，若发送的<code>sendData</code>
//	 * 中含有中文,记得按照双方约定的字符集将中文URLEncoder.
//	 * encode(string,encodeCharset)，本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
//	 * 
//	 * @param reqURL
//	 *            请求地址
//	 * @param sendData
//	 *            发送到远程主机的正文数据
//	 * 
//	 * @return 远程主机响应正文`HTTP状态码,如"SUCCESS`200"，若通信过程中发生异常则返回"Failed`HTTP状态码",如
//	 *         "Failed`500"
//	 */
//	public static String sendPostRequestByJava(String reqURL, String sendData) {
//		HttpURLConnection httpURLConnection = null;
//		OutputStream out = null; // 写
//		InputStream in = null; // 读
//		int httpStatusCode = 0; // 远程主机响应的HTTP状态码
//		try {
//			URL sendUrl = new URL(reqURL);
//			httpURLConnection = (HttpURLConnection) sendUrl.openConnection();
//			httpURLConnection.setRequestMethod("POST");
//			httpURLConnection.setDoOutput(true); // 指示应用程序要将数据写入URL连接,其值默认为false
//			httpURLConnection.setUseCaches(false);
//			httpURLConnection.setConnectTimeout(30000); // 30秒连接超时
//			httpURLConnection.setReadTimeout(30000); // 30秒读取超时
//
//			out = httpURLConnection.getOutputStream();
//			out.write(sendData.toString().getBytes());
//
//			// 清空缓冲区,发送数据
//			out.flush();
//
//			// 获取HTTP状态码
//			httpStatusCode = httpURLConnection.getResponseCode();
//
//			// 该方法只能获取到[HTTP/1.0 200 OK]中的[OK]
//			// 若对方响应的正文放在了返回报文的最后一行,则该方法获取不到正文,而只能获取到[OK],稍显遗憾
//			// respData = httpURLConnection.getResponseMessage();
//
//			// //处理返回结果
//			// BufferedReader br = new BufferedReader(new
//			// InputStreamReader(httpURLConnection.getInputStream()));
//			// String row = null;
//			// String respData = "";
//			// if((row=br.readLine()) != null){
//			// //readLine()方法在读到换行[\n]或回车[\r]时,即认为该行已终止
//			// respData = row; //HTTP协议POST方式的最后一行数据为正文数据
//			// }
//			// br.close();
//
//			in = httpURLConnection.getInputStream();
//			byte[] byteDatas = new byte[in.available()];
//			in.read(byteDatas);
//			return new String(byteDatas) + "`" + httpStatusCode;
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			return "Failed`" + httpStatusCode;
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (Exception e) {
//					logger.error("关闭输出流时发生异常,堆栈信息如下", e);
//				}
//			}
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception e) {
//					logger.error("关闭输入流时发生异常,堆栈信息如下", e);
//				}
//			}
//			if (httpURLConnection != null) {
//				httpURLConnection.disconnect();
//				httpURLConnection = null;
//			}
//		}
//	}
//	
//	/**
//	 * 描述信息:进行http请求
//	 * 
//	 * @param psHttpUrl
//	 * 				http访问路径
//	 * 
//	 * @return
//	 * 				http响应数据
//	 */
//	@SuppressWarnings({ "deprecation" })
//	public static String sendHttpReqeust(String psHttpUrl) {
//		
//		// HTTP请求
//		HttpUriRequest request = new HttpPost(psHttpUrl);
//		try {
//			httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
//			// 发送请求，返回响应
//			HttpResponse response = httpClient.execute(request);
//			HttpEntity httpEntity = response.getEntity();
//			// 得到相应正文
//			String sReturnHtml = new String(EntityUtils.toString(httpEntity));  
//			request.abort();
//			return sReturnHtml;
//		} catch (ClientProtocolException e) {
//			// 协议错误
//			e.printStackTrace();
//		} catch (IOException e) {
//			// 网络异常
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * 发送get方式的请求
//	 * @return
//	 * @author aaron
//	 */
//	public static InputStream Get(String url) throws ClientProtocolException, IOException {
//		
//		HttpGet httpGet = new HttpGet(url);
//		CloseableHttpClient client = HttpClientBuilder.create().build();
//		
//		CloseableHttpResponse response = client.execute(httpGet);
//		InputStream inputStream = response.getEntity().getContent();
//		
//		client.close();
//		response.close();
//		
//		return inputStream;
//	}
//	
//	public static void main(String[] args) {
//		
//		String request = sendPostRequest("http://www.baidu.com", "", false);
//	}
}
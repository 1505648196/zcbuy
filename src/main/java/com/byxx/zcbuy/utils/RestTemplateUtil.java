package com.byxx.zcbuy.utils;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author 聂鹏
 * @description: RestTemplate 远程调用工具类
 * @create 2019/06/28
 */
public class RestTemplateUtil {

	private static final String HEADER_NAME="id";
	private static final MultiValueMap<String, String> header;
  //静态赋值,初始化 header
	static {
	  header = new LinkedMultiValueMap<>();
	  header.add("Accept", "application/json");
	  header.add("Accpet-Encoding", "gzip");
	  header.add("Content-Encoding", "UTF-8");
	  header.add("Content-Type", "application/json; charset=UTF-8");
	}

	/**
	 * 单例对象实例
	 */
	private static class SingletonRestTemplate {
		static final RestTemplate INSTANCE = new RestTemplate();
	}

	private RestTemplateUtil() {
	}

	/**
	 * 单例实例
	 */
	public static RestTemplate getInstance() {
		return SingletonRestTemplate.INSTANCE;
	}

	/**
	 * @description:
	 * 注意：@RequestBody 注解的接收参数的类型要与请求的HttpEntity<T>的 T的类型一致
	 * 一般用 String 或者 Map类型
	 * Map<String,Object> body = Maps.newHashMap(); // 请求body
	 * body.put("name", "xiao ming");
	 * body.put("gender", 1);
	 * HttpEntity<Map<String,Object>> requestEntity = new HttpEntity<Map<String,Object>>(body,headers);
	 * controller接收
	 * public Map<String, Object> testAccept(@RequestBody Map<String, Object> request){}
	 */

	/**
	 * 发送post请求
	 * @param url   请求URL地址
	 * @return      String
	 *
	 */
	public static Object post(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(header);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		return RestTemplateUtil.getInstance().postForObject(url, requestEntity, Object.class);
	}

	/**
	 * 发送post请求
	 * @param url   请求URL地址
	 * @param data  请求的 json数据
	 * @return      String
	 *
	 */
	public static Object post(String url, Object data) {
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(header);

		HttpEntity<Object> requestEntity = new HttpEntity<>(data,headers);
		return RestTemplateUtil.getInstance().postForObject(url, requestEntity, Object.class);
	}

	/**
	 * 发送post请求
	 * @param url    请求URL地址
	 * @param data   请求的 json数据
	 * @param id     RSA加密的 id
	 * @return       String
	 *
	 */

	public static Object post(String url, Object data, String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(header);
		headers.add(HEADER_NAME, id);

		HttpEntity<Object> requestEntity = new HttpEntity<>(data, headers);
		return RestTemplateUtil.getInstance().postForObject(url, requestEntity, Object.class);
	}

	/**
	 * 发送 get请求
	 * @param url 请求URL地址
	 * @return    String
	 *
	 */
	public static Object get(String url) {
		return RestTemplateUtil.getInstance().getForObject(url, Object.class);
	}

	/**
	 * 发送 get请求
	 * @param url 请求URL地址,url需要使用占位符
	 * @param map 地址栏请求的参数封装在 map 里
	 * @return    String
	 *
	 */
	public static Object get(String url,Map<String,Object> map) {
		return RestTemplateUtil.getInstance().getForObject(url, Object.class,map);
	}

	/**
	 * 发送get请求
	 * @param url    请求URL地址,url需要使用占位符
	 * @param id     RSA加密id
	 * @return       String
	 *
	 */
	public static  Object get(String url, String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(header);
		headers.add(HEADER_NAME, id);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Object> response = RestTemplateUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, Object.class);
		return response.getBody();
	}

	/**
	 * 发送get请求
	 * @param url           请求URL地址,url需要使用占位符
	 * @param uriVariables  请求参数顺序排列
	 * @param id            RSA加密的 id
	 * @return              String
	 *
	 * 1、参数在url路径上，如：  String url = "http://www.xxx.com/test/kid/{id}/{name}";
	 * 2、参数在url路径后面，如：String url = "http://www.xxx.com/test/kid?id={id}&name={name}";
	 * String result = restTemplate.getForObject(url, String.class,"123456","xiao ming");
	 *
	 */
	public static  Object get(String url, String id, Object... uriVariables) {
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(header);
		headers.add(HEADER_NAME, id);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Object> response = RestTemplateUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, Object.class,uriVariables);
		return response.getBody();
	}

	/**
	 * 发送get请求
	 * @param url    请求URL地址,url需要使用占位符
	 * @param map    地址栏请求的参数封装在 map 里
	 * @param id     RSA加密的 id
	 * @return       String
	 *
	 * 参数在url路径后面，如：String url = "http://www.xxx.com/test/kid?id={id}&name={name}";
	 * Map<String, Object> requestMap = Maps.newHashMap();
	 * requestMap.put("id", "123456");
	 * requestMap.put("name", "xiao ming");
	 * String result = restTemplate.getForObject(url, String.class,requestMap);
	 *
	 */
	public static  Object get(String url, Map<String,Object> map, String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(header);
		headers.add(HEADER_NAME, id);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Object> response = RestTemplateUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, Object.class,map);
		return response.getBody();
	}
}

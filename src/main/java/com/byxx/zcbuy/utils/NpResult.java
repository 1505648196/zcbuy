package com.byxx.zcbuy.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据返回格式
 */
@Data
public class NpResult implements Serializable {

  private String msg;
	private boolean result;
	private Long count;
	private Object data;
	private Object obj;

	public NpResult() {
	}

	public NpResult(String msg, boolean result, Long count, Object data, Object obj) {
		this.msg = msg;
		this.result = result;
		this.count = count;
		this.data = data;
		this.obj = obj;
	}

	public static NpResult success() {
		return new NpResult("操作成功",true,null,null,null);
	}
	public static NpResult success(Object data) {
		return new NpResult("操作成功",true,null,data,null);
	}
	//返回多个对象
	public static NpResult success(Object data,Object obj)
	{
		return new NpResult("操作成功",true,null,data,obj);
	}
	//layui分页返回
	public static NpResult success(Long count,Object data)
	{
		return new NpResult("操作成功",true,count,data,null);
	}
	public static NpResult success(Long count,Object data,Object obj) { return new NpResult("操作成功",true,count,data,obj); }

	public static NpResult error() {
		return new NpResult("操作失败",false,null,null,null);
	}
	public static NpResult error(String msg) {
		return new NpResult(msg,false,null,null,null);
	}
	public static NpResult error(String msg,Object data) { return new NpResult(msg,false,null,data,null); }

}

package com.byxx.zcbuy.utils;

import lombok.Data;

import java.util.Date;

/**
 * @author 聂鹏
 * @description:
 * @create 2019/07/16
 */
@Data
public class PageBy {

	private Integer id;
	private String strId;
	private Integer page;
	private Integer size;
	private String name;
	private Integer bigCat;
	private Integer smallCat;
	private Integer warehouseid;
	private String warehouse;
	private Long longTime;
	private String carNum;
	private String myGroup;
	private String dateStr;
	private Date date;
	private Integer inOut;// 0 :新增, 1:发料, 2: 退料
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer quarter;//季度 1,2,3,4




	public Integer getPage() {
		if(page==null||page<1){
			page=1;
		}
		return page;
	}

	public Integer getSize() {
		if(size==null||size<1){
			size=10;
		}
		return size;
	}
}

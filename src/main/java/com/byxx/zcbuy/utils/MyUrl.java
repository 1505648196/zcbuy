package com.byxx.zcbuy.utils;

/**
 * @author 聂鹏
 * @create 2019/08/13
 */
public class MyUrl {

	/**ip */
	private static final String PRE ="http://chunyin1992.vicp.io";
	/**用户注册 */
	public static final String REGISTER =PRE+"/api/user/register";
	/**检查用户名重复 */
	public static final String UNAME_DO_WEIGHT = PRE+"/api/user/checkLoginName";
	/**登录 */
	public static final String DO_LOGIN = PRE+"/api/user/login";
	/**个人更新用户信息 */
	public static final String UPDATE_MY_INFO = PRE+"/api/user/updateByUser";
	/**管理员更新用户信息 */
	public static final String UPDATE_USER_INFO = PRE+"/api/user/updateByAdmin";
	/**根据id获取用户 */
	public static final String GET_USER_BY_ID = PRE+"/api/user/getUserById";
	/**根据条件查询用户 */
	public static final String GET_USER_BY = PRE+"/api/user/selectUser";
	/**获取全部单位 */
	public static final String GET_ALL_UNIT = PRE+"/api/unit/getUnits";
	/**根据单位获取部门 */
	public static final String GET_DEPARTMENTS_BY = PRE+"/api/unit/getDepartments";
	/**获取所有角色 */
	public static final String GET_ALL_ROLE = PRE+"/api/role/getAllAuditRoles";
	/**删除用户 */
	public static final String DEL_USER = PRE+"/api/user/disabled";
	/** */
	public static final String X = PRE+"";
	/**
	 * 获取权限表全部权限
	 */
	public  static  final String GET_ALl_Perission = PRE+"/api/power";

	/**
	 *筛选获取供货商与单位关联信息
	 */
	public  static  final String  UNIT_MERCHANT= PRE+"/api/unitMerchant/getUnitMerchants";
	/**
	 * 删除供货商与单位关联信息
	 */
	public  static  final String   DELSUPPLYUNITRELATION= PRE+"/api/unitMerchant/deleteUnitMerchant";
	/**
	 *添加供货商与单位关联信息
	 */
	public  static  final String  ADDSUPPLYUNITRELATION = PRE+"/api/unitMerchant/addUnitMerchant";
	/**
	 *商品更新
	 */
	public  static  final String  UPDATEGOODS = PRE+"/api/goods/updateGoods";

	/**
	 * 添加商品
	 */
	public  static  final String   ADD_GOODS = PRE+"/api/goods/addGoods";

	/**
	 * 删除商品
	 */
	public  static  final String  DEL_GOODS= PRE+"/api/goods/delGoods";

	/**
	 * 更新供应商地区对象
	 */
	public  static  final String    UPDATE_AREAMER_CHANT= PRE+"/api/areaMerchant/updateAreaMerchant";


	/**
	 * 删除供应商地区对象
	 * delAreaMerchant
	 */
	public  static  final String    DEL_AREA_MERCHANT= PRE+"/api/areaMerchant/delAreaMerchant";
}

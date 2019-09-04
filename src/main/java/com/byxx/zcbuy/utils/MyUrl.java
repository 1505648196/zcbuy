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
	 *
	 */
	public  static  final String    DEL_AREA_MERCHANT= PRE+"/api/areaMerchant/delAreaMerchant";

	/**
	 * 增加供应商地区对象
	 *
	 */
	public  static  final String   ADD_AREA_MERCHANT= PRE+"/api/areaMerchant/addAreaMerchant";

	/**
	 * 获取全部地区对象
	 *
	 */
	public  static  final String  GET_AREA_ALL= PRE+"/api/area/getAll";

	/**
	 * 添加新单位
	 */
	public  static  final String  ADD_UNIT= PRE+"/api/unit/addUnit";

	/**
	 * 删除单位
	 */
	public  static  final String  DELETE_UNIT= PRE+"/api/unit/deleteUnit";

	/**
	 * 更新单位
	 * updateUnit
	 */
	public  static  final String  UPDATE_UNIT= PRE+"/api/unit/updateUnit";

	/**
	 * 新增采购申请
	 * addPurchaseRequisition
	 */
	public  static  final String ADD_PURCHASE_REQUISITION= PRE+"/api/purchaseRequisition/addPurchaseRequisition";


	/**
	 * 删除采购单位
	 * delPurchaseRequisition
	 */
	public  static  final String DEL_PURCHASE_REQUISITION= PRE+"/api/purchaseRequisition/addPurchaseRequisition";


	/**
	 * 根据流程定义key 查看组任务
	 * getTasksByCandidate
	 */
	public  static  final String GET_TASKS_BY_CANDIDATE= PRE+"/api/activiti/getTasksByCandidate";


	/**
	 * 拾取任务
	 * claimTaskByUserId
	 */
	public  static  final String CLAIM_TASK_BY_USERID= PRE+"/api/activiti/claimTaskByUserId";


	/**
	 * 根据流程定义key 查看自己的全部任务（userId）
	 * getTasksByUserId
	 */
	public  static  final String GET_TASKS_BY_USERID= PRE+"/api/activiti/getTasksByUserId";

	/**
	 * 将个人任务归还至组任务
	 * returnTask
	 */
	public  static  final String RETURN_TASK= PRE+"/api/activiti/returnTask";

}

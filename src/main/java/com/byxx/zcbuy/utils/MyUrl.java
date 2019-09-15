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
	 * 根据用户id获取该用户全部权限
	 * getUserPowers
	 */
	public  static  final String GET_USER_POWERS = PRE+"/api/power/getUserPowers";


	/**
	 * 用户权限更新
	 * updateUserPower
	 */
	public  static  final String UPDATE_USER_POWER = PRE+"/api/power/updateUserPower";

	/**
	 * 获取权限表全部权限
	 *getPowers
	 */
	public  static  final String GET_POWERS = PRE+"/api/power/getPowers";

	/**
	 *獲取全部角色對應權限
	 *	 getPowerByRoles
	 */
	public  static  final String GET_POWER_BY_ROLES = PRE+"/api/power/getPowerByRoles";

	/**
	 * 删除角色
	 * delRole
	 */
	public  static  final String DEL_ROLE = PRE+"/api/role/delRole";

	/**
	 *增加新角色
	 * addRole
	 */
	public  static  final String ADD_ROLE = PRE+"/api/role/addRole";

	/**
	 * 角色权限更新
	 * updateRolePower
	 */
	public  static  final String UPDATE_ROLE_POWER = PRE+"/api/power/updateRolePower";

	/**
	 *查询全部商家
	 * getMerchants
	 */
	public  static  final String GET_MERCHANTS = PRE+"/api/user/getMerchants";

	/**
	 * 更新地区
	 *	updateArea
	 */
	public  static  final String UPDATE_AREA = PRE+"/api/area/updateArea";

	/**
	 *多条件筛选地区 获取全部地区
	 * getAreas
	 */
	public  static  final String GET_AREAS = PRE+"/api/area/getAreas";

	/**
	 * 新增地区
	 * addArea
	 */
	public  static  final String ADD_AREA = PRE+"/api/area/addArea";

	/**
	 * 删除地区
	 * delArea
	 */
	public  static  final String DEL_AERA = PRE+"/api/area/delArea";

	/**
	 *筛选获取供货商与单位关联信息
	 * getUnitMerchants
	 */
	public  static  final String  UNIT_MERCHANT= PRE+"/api/unitMerchant/getUnitMerchants";
	/**
	 * 删除供货商与单位关联信息
	 * deleteUnitMerchant
	 */
	public  static  final String   DELSUPPLYUNITRELATION= PRE+"/api/unitMerchant/deleteUnitMerchant";
	/**
	 *添加供货商与单位关联信息
	 * addUnitMerchant
	 */
	public  static  final String  ADDSUPPLYUNITRELATION = PRE+"/api/unitMerchant/addUnitMerchant";

	/**
	 * 多条件获取商品列表 全部商品
	 * getGoods
	 */
	public  static  final String  GET_GOODS = PRE+"/api/goods/getGoods";



	/**
	 *商品更新
	 * updateGoods
	 */
	public  static  final String  UPDATEGOODS = PRE+"/api/goods/updateGoods";

	/**
	 * 添加商品
	 * addGoods
	 */
	public  static  final String   ADD_GOODS = PRE+"/api/goods/addGoods";

	/**
	 * 删除商品
	 * delGoods
	 */
	public  static  final String  DEL_GOODS= PRE+"/api/goods/delGoods";

	/**
	 * 更新供应商地区对象
	 * updateAreaMerchant
	 */
	public  static  final String    UPDATE_AREAMER_CHANT= PRE+"/api/areaMerchant/updateAreaMerchant";


	/**
	 * 删除供应商地区对象
	 *delAreaMerchant
	 */
	public  static  final String    DEL_AREA_MERCHANT= PRE+"/api/areaMerchant/delAreaMerchant";

	/**
	 * 增加供应商地区对象
	 *addAreaMerchant
	 */
	public  static  final String   ADD_AREA_MERCHANT= PRE+"/api/areaMerchant/addAreaMerchant";

	/**
	 * 获取全部地区对象
	 *getAll
	 */
	public  static  final String  GET_AREA_ALL= PRE+"/api/area/getAll";

	/**
	 * 添加新单位
	 * addUnit
	 */
	public  static  final String  ADD_UNIT= PRE+"/api/unit/addUnit";

	/**
	 * 删除单位
	 * deleteUnit
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
	 * 多条件查找采购类型
	 * getPurchaseTypes
	 */
	public  static  final String GET_PURCHASE_TYPES= PRE+"/api/purchaseType/getPurchaseTypes";

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

	/**
	 * 提交任务
	 * 注意参数 taskComplete
	 */
	public  static  final String TASK_COMPLETE= PRE+"/api/activiti/taskComplete";



	/**
	 * 多条件查询采购  申请
	 * getPurchaseRequisitions
	 */
	public  static  final String GET_PURCHASE_REQUISITIONS= PRE+"/api/purchaseRequisition/getPurchaseRequisitionsLists";


	/**
	 * 查询多个历史任务
	 * http://chunyin1992.vicp.io/api/activiti/
	 * getHistoryList
	 */
	public  static  final String GET_HISTORYLIST= PRE+"/api/activiti/getHistoryList";

	/**
	 * 获取任务ID 相关的业务实体类
	 * getBusiness
	 */
	public  static  final String GET_BUSINESS= PRE+"/api/activiti/getBusiness";

	/**
	 *获取全部劳保部单位
	 * getAllLaobaobu
	 */
	public  static  final String GET_ALL_LAOBAOBU= PRE+"/api/unit/getAllLaobaobu";

	/**
	 * 多条件查询 商家地区关联
	 * getAreaMerchants
	 */
	public  static  final String GET_AREA_MERCHANTS= PRE+"/api/areaMerchant/getAreaMerchants";

	/**
	 * 条件查询或获取全部部门
	 *getDepartments
	 */
	public  static  final String GET_DEPARTMENTS= PRE+"/api/unit/getDepartments";

	/**
	 * 获取全部单位(分页)
	 * getUnitsWithPage
	 */
	public  static  final String GET_UNITS_WITH_PAGE= PRE+"/api/unit/getUnitsWithPage";


	/**
	 * 查询所有和本单位合作的供应商名单
	 * selectMerchants
	 */
	public  static  final String SELECT_MERCHANTS= PRE+"/api/purchaseRequisition/selectMerchants";

	/**
	 * 更新采购申请
	 * updatePurchaseRequisition
	 */
	public  static  final String UPDATE_PURCHASE_REQUISITION= PRE+"/api/purchaseRequisition/updatePurchaseRequisition";


	/**
	 *查询候选人(审批候选人与任务发起人处于同一个单位)
	 * selectCandidates
	 */
	public  static  final String SELECT_CANDIDATES= PRE+"/api/purchaseRequisition/selectCandidates";

	/**
	 * 显示流程图
	 *getProcessPng
	 */
	public  static  final String GET_PROCESSPNG= PRE+"/api/activiti/getProcessPng";

	/**
	 * 根据登录用户的角色 显示 合作商 对应的全部商品（适用于电话下单）
	 * getAllGoodsByUserId
	 */
	public  static  final String GET_ALL_GOODS_BY_USERID= PRE+"/api/goods/getAllGoodsByUserId";

	/**
	 *获取单个商品（id）
	 * getById
	 */
	public  static  final String GET_BY_ID= PRE+"/api/goods/getById";

	/**
	 * 查看同一单位的全部用户
	 * getUserToOneUnit
	 */
	public  static  final String GET_USER_TO_ONE_UNIT= PRE+"/api/user/getUserToOneUnit";




}

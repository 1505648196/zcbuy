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
	/**根据条件查询用户 */
	public static final String GET_USER_BY = PRE+"/api/user/selectUser?";
	/**获取全部单位 */
	public static final String GET_ALL_UNIT = PRE+"/api/unit/getUnits";
	/**根据单位获取部门 */
	public static final String GET_DEPARTMENTS_BY = PRE+"/api/unit/getDepartments?";
	/** */
	public static final String X = PRE+"";

}

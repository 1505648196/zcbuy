package com.byxx.zcbuy.controller;



import com.byxx.zcbuy.config.LoginInterceptor;

import com.byxx.zcbuy.model.PurchaseRequisitionList;
import com.byxx.zcbuy.model.TaskComplateParamters;

import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 采购
 */
@Controller
public class procurementController {

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/buyApply")
    public String productInfo(){
        return "admin/procurement/buyApply";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/buyType")
    public String buyType(){
        return "admin/procurement/buyType";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/addBuyApply")
    public String addBuyApply(){
        return "admin/procurement/addBuyApply";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/groupTask")
    public String groupTask(){
        return "admin/procurement/groupTask";
    }

    /**
     * 跳转
     */
    @RequestMapping("/selfTask")
    public String selfTask(){
        return "admin/procurement/selfTask";
    }

    /**
     * 跳转
     */
    @RequestMapping("/addBuyType")
    public String addBuyType(){
        return "admin/procurement/addBuyType";
    }



    /**
     * 跳转
     */
    @RequestMapping("/providerChange")
    public String providerChange(){
        return "admin/procurement/providerChange";
    }


    /**
     * 跳转
     */
    @RequestMapping("/history")
    public String history(){
        return "admin/history/history";
    }


    /**
     *新增采购申请
     * @return
     */
    @ResponseBody
    @PostMapping("/addPurchaseRequisition")
    public Object addPurchaseRequisition(PurchaseRequisitionList purchaseRequisitionList) {
        return RestTemplateUtil.post(MyUrl.ADD_PURCHASE_REQUISITION, purchaseRequisitionList, LoginInterceptor.getId());
    }


    /**
     * 删除采购申请
     */
    @ResponseBody
    @GetMapping("/delPurchaseRequisition")
    public Object getAllRole(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.DEL_PURCHASE_REQUISITION+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     * 组任务查看
     */
    @ResponseBody
    @GetMapping("/getTasksByCandidate")
    public Object getTasksByCandidate(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_TASKS_BY_CANDIDATE+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *拾取任务
     */
    @ResponseBody
    @GetMapping("/claimTaskByUserId")
    public Object claimTaskByUserId(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.CLAIM_TASK_BY_USERID+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *根据流程定义key 查看自己的全部任务（userId）
     * 	 getTasksByUserId
     */
    @ResponseBody
    @GetMapping("/getTasksByUserId")
    public Object getTasksByUserId(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_TASKS_BY_USERID+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *将个人任务归还至组任务
     * 	 returnTask
     */
    @ResponseBody
    @GetMapping("/returnTask")
    public Object returnTask(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.RETURN_TASK+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     * 提交任务
     * 注意参数 taskComplete
     */
    @ResponseBody
    @PostMapping("/taskComplete")
    public Object taskComplete ( TaskComplateParamters taskComplateParamters) {
        return RestTemplateUtil.post(MyUrl.TASK_COMPLETE,taskComplateParamters, LoginInterceptor.getId());
    }

    /**
     * 多条件查找采购类型
     * getPurchaseTypes
     */
    @ResponseBody
    @GetMapping("/getPurchaseTypes")
    public Object getPurchaseTypes(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_PURCHASE_TYPES+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**\
     * 多条件查找采购申请
     * getPurchaseTypes
     */
    @ResponseBody
    @GetMapping("/getPurchaseRequisitions")
    public Object getPurchaseRequisitions(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_PURCHASE_REQUISITIONS+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     * 查询多个历史任务
     */
    @ResponseBody
    @GetMapping("/getHistoryList")
    public Object getHistoryList(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_HISTORYLIST+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *获取任务ID 相关的业务实体类
     * getBusiness
     */
    @ResponseBody
    @GetMapping("/getBusiness")
    public Object getBusiness(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_BUSINESS+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     *获取全部劳保部单位
     * getAllLaobaobu
     */
    @ResponseBody
    @GetMapping("/getAllLaobaobu")
    public Object getAllLaobaobu(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_ALL_LAOBAOBU+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 多条件查询 商家地区关联
     * getAreaMerchants
     */
    @ResponseBody
    @GetMapping("/getAreaMerchants")
    public Object getAreaMerchants(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_AREA_MERCHANTS+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 更新采购申请
     * updatePurchaseRequisition
     */
    @ResponseBody
    @PostMapping("/updatePurchaseRequisition")
    public Object updatePurchaseRequisition(PurchaseRequisitionList purchaseRequisitionList) {
        return RestTemplateUtil.post(MyUrl.UPDATE_PURCHASE_REQUISITION, purchaseRequisitionList, LoginInterceptor.getId());
    }

    /**
     *查询候选人(审批候选人与任务发起人处于同一个单位)
     * selectCandidates
     */
    @ResponseBody
    @GetMapping("/selectCandidates")
    public Object selectCandidates(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.SELECT_CANDIDATES+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 根据登录用户的角色 显示 合作商 对应的全部商品（适用于电话下单）
     * getAllGoodsByUserId
     */
    @ResponseBody
    @GetMapping("/getAllGoodsByUserId")
    public Object getAllGoodsByUserId(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_ALL_GOODS_BY_USERID+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 查看同一单位的全部用户
     * getUserToOneUnit
     */
    @ResponseBody
    @GetMapping("/getUserToOneUnit")
    public Object getUserToOneUnit(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_USER_TO_ONE_UNIT+request.getAttribute("p"), LoginInterceptor.getId());
    }


}

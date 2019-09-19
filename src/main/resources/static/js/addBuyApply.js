layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件


    show();
    function show(){
        var showId = $("#userId").val();
        console.log(showId)
        //可申请用用户  电话下单
        $.get("getUserToOneUnit",{"userId":showId},function (res) {
            console.log(res)
            var data=res.data;
            var htmltwo=" <option value='' >直接选择或搜索选择</option>";
            $.each(data,function (index,item) {
                htmltwo+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#applyuser").html(htmltwo);
            form.render();
        });
    }

    //劳保查询
    function getAllLaobaobu() {
        $.get("getAllLaobaobu",function (res) {
            var data=res.data;
            console.log(res);
            var html="<option value=''>请选择站车劳保部</option>";
            $.each(data,function (index,item) {
                html+="<option   value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#labour").html(html);
            form.render();
        })
    }

    getAllGoodsByUserId();

    //根据userId查询旗下的拥有的商品申请权限
    function getAllGoodsByUserId() {
        var userId = $("#userId").val();
        console.log(userId);
        $.get("getAllGoodsByUserId",{"userId":userId},function (res) {
            console.log(res.msg);
            var data=res.data;
            var html="<option value=''></option>";
            $.each(data,function (index,item) {
                html+="<option   value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#goods").html(html);
            form.render();
        })
    }
    var goodsId ;
    var purchaseTypeId ;
    form.on('select(goods)', function (data){
        var id= data.value;
        $.get("getById",{"id":id},function (res) {
            console.log(res);
            var data=res.data;
            goodsId = data.id;
            purchaseTypeId = data.purchaseType.id;
            var  price = data.price;
            var  statusName = data.statusName;
            var  goodsTypeName =  data.goodsType.name;
            var userName= data.user.name;
            var phone = data.user.phone;
            var email = data.user.email;
            var unitName = data.user.unit.name;
            var address = data.user.unit.address;
            var purchaseTypeName = data.purchaseType.name;
            console.log(purchaseTypeName);
            if (purchaseTypeName=="劳保用品") {
                var htmlLaoBao="<div class=\"layui-form-item\" style='color: red'>\n" +
                    "            <label class=\"layui-form-label\">站车劳保部:</label>\n" +
                    "            <div class=\"layui-input-block \">\n" +
                    "                <select id=\"labour\" name=\"labour\" class=\"layui-input\" lay-filter=\"labour\"  >\n" +
                    "                </select>\n" +
                    "            </div>\n" +
                    "        </div>";
                $("#laobao").html(htmlLaoBao);
                getAllLaobaobu()
            }
            console.log(userName,phone,email,unitName,address.purchaseType);
            $("#price").val(parseInt(price/100));
            $("#statusName").val(statusName);
            $("#goodsTypeName").val(goodsTypeName);
            $("#userName").val(userName);
            $("#phone").val(phone);
            $("#email").val(email);
            $("#unitName").val(unitName);
            $("#address").val(address);
    })

    });

    $("#sum").on("input",function(e){
        //获取input输入的值
        console.log(e.delegateTarget.value);
      var a =   parseInt($("#price").val());
      var c=  parseInt($("#quantity").val());
      var b =parseInt(a*c);
      $("#sum").val(b);
    });


    var context ="";
    form.on('submit(sub)',function (data) {
        var  happen =$("#happen").val();
        var purchaseRequisitionId =$("#id").val();
        var userId = $("#userId").val();
        console.log(userId)
        var goodsName = $("#goodsName").val();//已被删除
        var quantity = $("#quantity").val();
        var taskId =$("#taskId").val();
        var desc = $("#desc").val();
        console.log(taskId);
        var purchaseTypeName = $("#purchaseTypeName").val();//拿的是id 不是名字
        console.log(purchaseTypeName);
        var  arrplus = [];
        var labour =$("#labour").val();
        console.log(labour);
        console.log(purchaseTypeId);
        console.log(purchaseTypeId,goodsId,$("#applyuser").val(),quantity,desc)
        if (happen=="edit"){
            //同步
            $.ajaxSetup({
                async: false
            });

            $.get("selectCandidates",
                {"userId":userId},
                function (res) {
                datas = res.data;
                $.each(datas,function (index,item) {
                    context+=item.id+","
                })
                    return context
            })
            console.log(context);
            $.post("updatePurchaseRequisition",
                {"id":purchaseRequisitionId,"goodsName":goodsName,"quantity":quantity,"price":priceplus},
                function (res) {
                    if(res.result){
                        $.post("taskComplete",
                            //taskId  users  boo_candidate true  boo_delete false boo_pass true PurchaseRequisition
                          {"taskId":taskId,"str_users":context,"boo_candidate":true,"boo_delete":false,"boo_pass":true,"purchaseRequisitionId": purchaseRequisitionId} ,
                            function (res) {
                                if(res.result){
                                    console.log(res.msg);
                                    layer.msg(res.msg, {
                                        time: 1000
                                    },function () {
                                        //传到爹哪里去
                                        var index = parent.layer.getFrameIndex(window.name);
                                        parent.layer.close(index);
                                        parent.location.reload();
                                    });
                                }else {
                                    layer.msg(res.msg, {
                                        time: 2000
                                    },function () {
                                        console.log(res.msg)
                                    });
                                }
                            });
                    }else {
                        layer.msg(res.msg, {
                            time: 2000
                        },function () {
                            console.log(res.msg)
                        });
                    }
                });
        }else {
            $.post("addPurchaseRequisition",
                {
                    "purchaseTypeId":purchaseTypeId,
                    "goodsId":goodsId,
                    "userId":$("#applyuser").val(),
                    "quantity":quantity,
                    "totalPrice":"",
                    "comment":desc,
                    "unitId":labour,
                }
             ,
                function (res) {
                    if (res.result) {
                        console.log(res.msg);
                        layer.msg(res.msg, {
                            time: 2000
                        }, function () {
                            //传到爹哪里去
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    } else {
                        layer.msg(res.msg, {
                            time: 20000
                        });
                    }
                });
        }

        return false;
    });



});
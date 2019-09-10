layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

    getDepartment();

    function getDepartment() {
        $.get("getPurchaseTypes",function (res) {
            var data=res.data;
            var html="<option value=''>全部采购类型</option>";
            $.each(data,function (index,item) {
                html+="<option  value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#purchaseTypeName").html(html);
            getAllLaobaobu();
        })
    }


    function getAllLaobaobu() {
        $.get("getAllLaobaobu",function (res) {
            var data=res.data;
            var html="<option value=''>请选择站车劳保部</option>";
            $.each(data,function (index,item) {
                html+="<option   value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#labour").html(html);
            form.render();
        })
    }


    form.on('select(relation)', function (data){
        if (data.value=="2f00921bceb549b589dc0ee27818f104") {
            $("#bb").show();
            $("#kk").show();
        }
        if(data.value!="2f00921bceb549b589dc0ee27818f104") {
            $("#bb").hide();
            $("#kk").hide();
        }
    });
    var context ="";
    form.on('submit(sub)',function (data) {
        var purchaseRequisitionId =$("#id").val();
        var happen = $("#happen").val();
        var userId = $("#unit").val();
        console.log(userId)
        var goodsName = $("#goodsName").val();
        var quantity = $("#quantity").val();
        var price = $("#price").val();
        var priceplus=  parseInt(price)*100;
        var taskId =$("#taskId").val();
        console.log(taskId)
        console.log(priceplus)
        var purchaseTypeName = $("#purchaseTypeName").val();//拿的是id 不是名字
        console.log(purchaseTypeName);
        var  arrplus = [];
        var labour =$("#labour").val();

        if (happen=="update"){
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
                    "userId": userId,
                    "goodsName": goodsName,
                    "quantity": quantity,
                    "price": priceplus,
                    "purchaseTypeId": purchaseTypeName,
                    "unitId": labour
                },
                function (res) {
                    if (res.result) {
                        console.log(res.msg);
                        layer.msg(res.msg, {
                            time: 1000
                        }, function () {
                            //传到爹哪里去
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    } else {
                        layer.msg(res.msg, {
                            time: 2000
                        });
                    }
                });
        }

        return false;
    });



});
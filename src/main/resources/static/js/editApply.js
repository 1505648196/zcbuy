layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

    loadData();
    $.ajaxSetup({
        async: false
    });
    var userIds ="";

    function show(){
        var c=false;

        var showId = $("#userId").val();
        console.log(showId)
        //可申请用用户  电话下单
        $.get("getUserToOneUnit",{"userId":showId},function (res) {
            console.log(res)
            var data=res.data;
            var html=" <option value='' >直接选择或搜索选择</option>";
            $.each(data,function (index,item) {
                if (item.id== $("#user").val()){
                    c = true;
                }
                if(c){
                    html+="<option  value='"+item.id+"' selected>"+item.name+"</option>";
                    c=false;
                }else {
                    html+="<option  value='"+item.id+"'>"+item.name+"</option>";
                }
            });
            $("#applyuser").html(html);
            form.render();
        });
    }
        //商品
        function getAllGoodsByUserId() {
            var  b =false;
            var userId ="";
             $("#happen").val()=="supply" ? userId = $("#user").val(): userId = $("#userId").val()

            console.log(  $("#goodsId").val());
            $.get("getAllGoodsByUserId",{"userId":userId},function (res) {
                console.log(res.msg);
                var data=res.data;
                console.log(data);

                var html="<option value=''></option>";
                $.each(data,function (index,item) {
                    if (item.id== $("#goodsId").val()){
                        b = true;
                    }
                    if(b){
                        html+="<option  value='"+item.id+"' selected>"+item.name+"</option>";
                        b=false;
                    }else {
                        html+="<option  value='"+item.id+"'>"+item.name+"</option>";
                    }
                });
                $("#goods").html(html);
                form.render();
            })
        }





    //劳保
    function getAllLaobaobu() {
        $.get("getAllLaobaobu",function (res) {
            var data=res.data;
            console.log(res);
            var html="<option value=''>请选择站车劳保部</option>";
            $.each(data,function (index,item) {
                // if (item.id=1){
                //     b = true;
                // }
                // if(b){
                //     html+="<input lay-skin='primary' type='checkbox' checked name='"+item.id+"'  value='"+item.id+"' title='"+item.name+"'/>";
                //     b=false;
                // }else {
                //     html+="<input lay-skin='primary' type='checkbox' name='"+item.id+"' value='"+item.id+"' title='"+item.name+"'/>";
                // }
                html+="<option   value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#labour").html(html);
            form.render();
        })
    }
    function loadData() {
        var taskId = $("#taskId").val();
        console.log(taskId);
        $.get("getBusiness",{"taskId":taskId},function (res) {
            if(res.result){
                var data=  res.data;
               var goodsId  = data.goodsId;
               console.log(
                   goodsId
               )
                userIds = data.goods.userId;
                var purchaseRequisitionId = data.id;
                var totalPrice = data.totalPrice;
                var  comment = data.activitiComment;
                var  price = data.goods.price;
                var  statusName = data.goods.statusName;
                var  goodsTypeName =  data.goods.goodsType.name;
                var userName= data.goods.user.name;
                var phone = data.goods.user.phone;
                var email = data.goods.user.email;
                var unitName = data.goods.user.unit.name;
                var address = data.goods.user.unit.address;
                var purchaseTypeName = data.purchaseType.name;
                var quantity = data.quantity;
                var applyuser = data.user.name;
                var goods = data.goods.name;
                var user = data.userId;
                $("#purchaseRequisitionId").val(purchaseRequisitionId);
                $("#user").val(user);
                $("#goodsId").val(goodsId);
                $("#goods").val(goods);
                $("#applyuser").val(applyuser);
                $("#quantity").val(quantity);
                $("#totalPrice").val(totalPrice);
                $("#desc").val(comment);
                $("#price").val(price);
                $("#statusName").val(statusName);
                $("#goodsTypeName").val(goodsTypeName);
                $("#userName").val(userName);
                $("#phone").val(phone);
                $("#email").val(email);
                $("#unitName").val(unitName);
                $("#address").val(address);
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
                getAllGoodsByUserId();
                show();
            }else {
                layer.msg(res.msg, {
                    time: 2000
                },function () {
                    console.log(res.msg)
                });
            }
        });
    }
    //走
    function go() {
        var purchaseRequisitionId = $("#purchaseRequisitionId").val();
        var taskId = $("#taskId").val();
        var quantity = $("#quantity").val();
        var context ="";
    var userId  = $("#userId").val();
        $.ajaxSetup({
            async: false
        });
        $.get("selectDazongCandidates",
            {"userId":userId},
            function (res) {
                datas = res.data;
                $.each(datas,function (index,item) {
                    context+=item.id+","
                })
                return context
            })
        console.log(context);
        console.log(purchaseRequisitionId,taskId,context,userId,quantity);
        $.post("updatePurchaseRequisition",
            {"id":purchaseRequisitionId,"quantity":quantity},
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
    }

    function supply(){
        layer.confirm('确定供货吗', function(index){
            $.post("taskComplete",{"boo_assignee":true,"taskId":$("#taskId").val(),'boo_delete':false,"boo_pass":true,"str_users":userIds},
                function (res) {
                    if(res.result){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.location.reload();
                    }else {
                        layer.msg('操作失败！'+res.msg, {
                            time: 1000
                        });
                    }
                });
        })


    }


    form.on('submit(sub)',function (data) {
        console.log(data);
        var happen = $("#happen").val();
        switch(happen){
            case "supply":
                supply();
                break;
            case "":
                go()
                break;
            default:
               console.log("啥也没有")
       break;
        }
        return false;
    })






 });
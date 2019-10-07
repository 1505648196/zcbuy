layui.use(['layer', 'form', 'jquery', 'table','laytpl'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    var laytpl = layui.laytpl;
    $ = layui.jquery; //jquery控件

    $.ajaxSetup({
        async: false
    });

    //ES6语法
    function divideByHundred(str) {
        let floatVal = parseFloat(str);
        if (isNaN(floatVal )) {
            return false;
        }
        floatVal = Math.round(str * 100) / 10000;
        let strVal = floatVal .toString();
        let searchVal = strVal.indexOf('.');
        if (searchVal < 0) {
            searchVal = strVal.length;
            strVal += '.';
        }
        while (strVal.length <= searchVal + 2) {
            strVal += '0';
        }
        return strVal;
    }

    setTimeout(function () {
        loadData();
    },200);

    var taskId = $("#taskId").val();
    var userGoodsId = ""
    console.log(taskId);
    function loadData() {
        var taskId = $("#taskId").val();
        console.log(taskId);
        $.get("getBusiness",{"taskId":taskId},function (res) {
            if(res.result){
               var data=  res.data;
               userGoodsId = data.goods.userId;
               var totalPrice = data.totalPrice;
                var  comment = data.comment;
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
                var applyuser = data.user.name
                var goods = data.goods.name
                $("#goods").val(goods);
                $("#applyuser").val(applyuser);
                $("#quantity").val(quantity);
                $("#totalPrice").val(divideByHundred(totalPrice));
                $("#desc").val(comment);
                $("#price").val(divideByHundred(price));
                $("#statusName").val(statusName);
                $("#goodsTypeName").val(goodsTypeName);
                $("#userName").val(userName);
                $("#phone").val(phone);
                $("#email").val(email);
                $("#unitName").val(unitName);
                $("#address").val(address);
            }else {
                layer.msg(res.msg, {
                    time: 2000
                },function () {
                    console.log(res.msg)
                });
            }
        });
    }
    // show();
    //     //供应商下拉
    //     function show(){
    //     ids = $("#user").val();
    //     console.log(ids);
    //     var context =""
    //         //经开部门下拉框
    //         $.get("selectJKBCandidates",{"userId":ids},function (res) {
    //            var datas = res.data;
    //             $.each(datas,function (index,item) {
    //                 context+=item.id+","
    //             })
    //             return context
    //         });
    //     }
    function show(){
        var context ="";
        var ids = $("#userId").val();
        $.get("selectJKBCandidates",{"userId":ids},function (res) {
            var s = res.data;
            console.log(s);
            $.each(s,function (index,item) {
                context+=item.id+",";
            })
            return context
        });
    }

        form.on('submit(sub)',function (data){
            //同步
            $.ajaxSetup({
                async: false
            });

            // console.log(ids);
            var taskId = $("#taskId").val();
           var userId = $("#userId").val();
            var context ="";
            var ids = $("#userId").val();
            console.log( "*******************",$("#userId").val())
            console.log(userGoodsId);
            $.get("selectJKBCandidates",{"userId":$("#userId").val()},function (res) {
                var s = res.data;
                console.log(s);
                $.each(s,function (index,item) {
                    context+=item.id+",";
                })
                return context
            });
            if ($("#happen").val()=="劳保卧具") {
                // console.log(str_users);
                $.post("taskComplete", {
                        "boo_assignee": true,
                        "taskId": taskId,
                        'boo_delete': false,
                        "boo_pass": true,
                        "str_users":userGoodsId
                    },
                    function (res) {
                        if (res.result) {
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
                            }, function () {
                                console.log(res.msg)
                            });
                        }
                    });
            }else {
                // console.log(str_users);
                $.post("taskComplete", {
                        "boo_candidate": true,
                        "taskId": taskId,
                        'boo_delete': false,
                        "boo_pass": true,
                        "str_users": context
                    },
                    function (res) {
                        if (res.result) {
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
                            }, function () {
                                console.log(res.msg)
                            });
                        }
                    });
            }
            return false;
        });

    });
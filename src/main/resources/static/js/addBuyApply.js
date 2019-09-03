layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

    getDepartment();

    function getDepartment() {
        $.get("http://chunyin1992.vicp.io/api/purchaseType/getPurchaseTypes",function (res) {
            var data=res.data;
            var html="<option value=''>全部采购类型</option>";
            $.each(data,function (index,item) {
                html+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#purchaseTypeName").html(html);
            form.render();
        })
    }
    form.on('submit(sub)',function (data) {
        var userId = $("#unit").val();
        console.log(userId)
        var goodsName = $("#goodsName").val();
        var quantity = $("#quantity").val();
        var price = $("#price").val();
        var purchaseTypeName = $("#purchaseTypeName").val();
        $.post("addPurchaseRequisition",
            {"userId":userId,"goodsName":goodsName,"quantity":quantity,"price":price,"purchaseTypeId":purchaseTypeName},
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
                    });
                }
            });

        return false;
    });



});
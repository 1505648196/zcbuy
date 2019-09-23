layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件



        show();
        //商品状态下拉框
        function show(){
            var statusIds = $("#statusId").val()
            console.log(statusIds);
            //单位下拉框
            $.get("getGoodsStatus",function (res) {
                var data=res.data;
                var htmltwo=" <option value='' >直接选择或搜索选择</option>";
                $.each(data,function (index, item) {
                    console.log()
                    var b=false;
                    //判断是否含有这个权限id  有就true
                    if (item.id==statusIds){
                        b = true;
                    }
                    if(b){
                        htmltwo+="<option value='"+item.id+"' selected>"+item.name+"</option>";
                        b=false;
                    }else {
                        htmltwo+="<option value='"+item.id+"' >"+item.name+"</option>";
                    }
                });
                $("#status").html(htmltwo);
                form.render();
            });

        }

        getAllGoodsTypes()
        //全部商品小类下拉框
        function getAllGoodsTypes(){
            var goodsTypeIds =$("#goodsTypeIds").val();
            //获取全部商品小类
            $.get("getAllGoodsTypes",function (res) {
                var data=res.data;
                var htmltwo=" <option value='' >直接选择或搜索选择</option>";
                $.each(data,function (index, item) {
                    var b=false;
                    //判断是否含有这个权限id  有就true
                    if (item.id==goodsTypeIds){
                        b = true;
                    }
                    if(b){
                        htmltwo+="<option value='"+item.id+"' selected>"+item.name+"</option>";
                        b=false;
                    }else {
                        htmltwo+="<option value='"+item.id+"' >"+item.name+"</option>";
                    }
                });
                $("#goodsTypeId").html(htmltwo);
                form.render();
            });
        }



        form.on('submit(sub)',function (data) {
            var statusId = $("#statusId").val();
            var goodsTypeId = $("#goodsTypeId").val();
           var id = $("#id").val();
           var name = $("#name").val();
            var price = $("#price").val();
            console.log(price,name,statusId,goodsTypeId);
            $.post("updateGoods",
                //status状态没写  后台未定义好状态
                {"id":id,"name":name,"price":parseInt(price*100),"status":statusId,"goodsTypeId":goodsTypeId},//可以试试ES6 7 8 的语法
                function (res) {
                    if(res.result){
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

            return false;
        });

    });
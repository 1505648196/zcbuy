layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件

        getMerchants();
        getAllGoodsTypes();
        getMeasurements();
        function getMerchants(){
            //单位下拉框
            $.get("getMerchants",function (res) {
                var data=res.data;
                var htmltwo=" <option value='' >直接选择或搜索选择</option>";
                $.each(data,function (index,item) {
                    htmltwo+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                $("#userId").html(htmltwo);
                form.render();
            });
        }

        function getAllGoodsTypes(){
            //商品小类
            $.get("getAllGoodsTypes",function (res) {
                var data=res.data;
                var htmltwo=" <option value='' >直接选择或搜索选择</option>";
                $.each(data,function (index,item) {
                    htmltwo+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                $("#cmy").html(htmltwo);
                form.render();
            });
        }

        function getMeasurements(){
            $.get("getMeasurements",function (res) {
                var data=res.data;
                console.log(data);
                var dataPlus = new Array();
                for(var str of data){
                    dataPlus.push(str);
                }
                var htmltwo=" <option value='' >直接选择或搜索选择</option>";
                $.each(dataPlus,function (index,item) {
                    htmltwo+="<option value='"+item+"'>"+item+"</option>";
                });
                $("#sumUnit").html(htmltwo);
                form.render();
            });
        }



        form.on('submit(sub)',function (data) {
            var name = $("#name").val();
            var price = $("#price").val();
            var  userId = $("#userId").val();
            var cmy =$("#cmy").val();
            var sumUnit=  $("#sumUnit").val();
            console.log(userId) ;
            console.log(cmy,sumUnit);
            $.post("addGoods",
                {"name":name,"price":parseInt(price*100),"userId":userId,"goodsTypeId":cmy,"measurement":sumUnit},
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
layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件

        $.get("getAreaAll",function (res) {
            var data=res.data;
            var html=" <option value=\"00\" >直接选择或搜索选择</option>";
            $.each(data,function (index,item) {
                html+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#areaId").html(html);
            show();
        });

        function show(){
            //单位下拉框
            $.get("http://chunyin1992.vicp.io/api/user/getMerchants",function (res) {
                var data=res.data;
                var htmltwo=" <option value=\"00\" >直接选择或搜索选择</option>";
                $.each(data,function (index,item) {
                    htmltwo+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                $("#userId").html(htmltwo);
                form.render();
            });
        }

        form.on('submit(sub)',function (data) {
            userId = $("#userId").val();
            console.log(userId);
            areaId = $("#areaId").val();
            console.log(areaId);
            $.post("addAreaMerchant",
                {"areaId":areaId,"userId":userId},
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
layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件
        //单位下拉框
        $.get("getAllUnit",function (res) {
            var data=res.data;
            var html=" <option value=\"00\" >直接选择或搜索选择</option>";
            $.each(data,function (index,item) {
                    html+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#unitId").html(html);
            show();
        });
        //供应商下拉
        function show(){
            //单位下拉框
            $.get("getMerchants",function (res) {
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
            userIdplus = $("#userId").val();
            console.log(userIdplus) ;
            unitIdplus = $("#unitId").val();
            console.log(unitIdplus);
                $.post("addsupplyUnitRelation",
                    {"userId":userIdplus,"unitId":unitIdplus},
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
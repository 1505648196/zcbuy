layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件

        form.on('submit(sub)',function (data) {
            var name = $("#name").val();
            var address = $("#address").val();
            $.post("addUnits",
                {"name":name,"address":address},
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
layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件

        form.on('submit(sub)',function (data) {
           var id = $("#id").val();
            console.log(id) ;
           var name = $("#name").val();
            var price = $("#price").val();
            console.log(price,name);
            $.post("updateGoods",
                //status状态没写  后台未定义好状态
                {"id":id,"name":name,"price":price},//可以试试ES6 7 8 的语法
                function (res) {
                    if(res.result){

                        layer.msg(res.msg, {
                            time: 10000
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
layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件


    function show(name){
        $.post("updateArea",
            {id:ids,name:nameplus},function (res) {
                if (res.result){
                    layer.msg("成功");
                    data = res.data;
                    console.log(data)
                }
                else {
                    layer.msg("失败")
                }
            })

    }
    $(".go").click(function () {
        var ids= $("#ids").val();
        console.log(ids)
        var nameplus = $("#name").val();
        console.log(nameplus);
        $.ajax({
            url: "updateArea",
            data: JSON.stringify( {id:ids,name:nameplus}),
            type:"post",
            dataType: "json",
            contentType : "application/json",//否则报错类型不能少
            success:function (res) {
                console.log(res.result);
                if (res.result) {

                    layer.msg('成功！', {
                        time: 500
                    }, function () {

                       // 传到爹哪里去
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.location.reload();
                    });
                } else {
                    console.log(res.msg);
                    layer.msg('添加失败！' + res.msg, {
                        time: 1000
                    });
                }
            }
        });















    })






})

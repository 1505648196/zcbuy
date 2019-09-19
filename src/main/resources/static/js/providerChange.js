layui.use(['layer', 'form', 'jquery', 'table','laytpl'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    var laytpl = layui.laytpl;
    $ = layui.jquery; //jquery控件

    show();
    //供应商下拉
    function show(){
        ids = $("#user").val();
        //供应商下拉框
        $.get("selectMerchants",{"userId":ids},function (res) {
            var data=res.data;
            var htmltwo=" <option value='' >直接选择或搜索选择</option>";
            $.each(data,function (index,item) {
                htmltwo+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#userId").html(htmltwo);
            form.render();
        });
    }
    var taskId = $("#taskId").val();
    console.log(taskId);


    form.on('submit(sub)',function (data) {
        var taskId = $("#taskId").val();
        console.log(taskId);
        var userId = $("#userId").val();
        console.log(userId);

        var  ss = JSON.stringify(arr);
        $.post("taskComplete", {"boo_assignee":true,"taskId":taskId,'boo_delete':false,"boo_pass":true,"str_users":userId},
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
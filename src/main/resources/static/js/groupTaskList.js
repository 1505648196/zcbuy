layui.use(['layer', 'form', 'jquery', 'table','laytpl'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    var laytpl = layui.laytpl;
    $ = layui.jquery; //jquery控件
    var happen =  $("#happen").val();
    if (happen=="不同意"||happen=="取消任务") {
        $("#show").show();
    }
    form.on('submit(sub)',function (data) {
        var userId= $("#user").val();
        var taskId = $("#taskId").val();
        var desc = $("#desc").val();
        console.log(taskId)
        switch(happen) {
            case "不同意":
                layer.confirm('确定不同意吗', function(index){
                    $.post("taskComplete", {"taskId":taskId,'boo_delete':false,"boo_pass":false,"activitiComment":desc},
                        function (res) {
                            if(res.result){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.location.reload();
                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 10000
                                });
                            }
                        });
                });
                break;
            case "接受任务":
                layer.confirm('确定接受吗', function(index){
                    $.get("claimTaskByUserId", {"userId":userId,'taskId':taskId},
                        function (res) {
                            if(res.result){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.location.reload();

                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 1000
                                });
                            }
                        });
                });
                break;
            case "取消任务":
                layer.confirm('确定取消吗', function(index){
                    $.post("taskComplete", {"taskId":taskId,'boo_delete':true,"boo_pass":false,"activitiComment":desc},
                        function (res) {
                            if(res.result){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.location.reload();
                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 1000
                                });
                            }
                        });
                });
                break
            case "配送":
            layer.confirm('确定配送吗', function(index){
                $.post("taskComplete", {"taskId":taskId,'boo_delete':false,"boo_pass":true},
                    function (res) {
                        if(res.result){
                            layer.msg('配送成功！'+res.msg, {
                                time: 2000
                            });
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload();
                        }else {
                            layer.msg('操作失败！'+res.msg, {
                                time: 1000
                            });
                        }
                    });
            });
            default:
               console.log("啥都没有");
        }
     return false
    });

});
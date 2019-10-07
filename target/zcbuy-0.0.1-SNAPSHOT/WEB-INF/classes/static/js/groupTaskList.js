layui.use(['layer', 'form', 'jquery', 'table','laytpl','element'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    var laytpl = layui.laytpl;
    $ = layui.jquery; //jquery控件
    var happen =  $("#happen").val();
    if (happen=="不同意"||happen=="取消任务"||happen=="不能供货") {
        $("#show").show();
    }
    form.on('submit(sub)',function (data) {
        var userId= $("#user").val();
        var taskId = $("#taskId").val();
        var desc = $("#desc").val();
        console.log(desc);
        console.log(taskId);
        //又换了种风格，并且设定最长等待10秒
        switch(happen) {
            case "不同意":
                if (desc==""||desc==null){
                    layer.msg("请输入审批理由", {
                        time: 2500,
                        icon:2
                    });
                    break;
                }
                layer.confirm('确定不同意吗', function(index){
                    var index = layer.load(0, {time: 10*1000});
                    $.post("taskComplete", {"taskId":taskId,'boo_delete':false,"boo_pass":false,"activitiComment":desc},
                        function (res) {
                            if(res.result){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.location.reload();
                                layer.close(index);
                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 10000
                                });
                                layer.close(index);
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
                                layer.close(index);

                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 1000
                                });
                                layer.close(index);
                            }
                        });
                });
                break;
            case "取消任务":
                if (desc==""||desc==null){
                    layer.msg("请输入审批理由", {
                        time: 1000
                    });
                    break;
                }
                layer.confirm('确定取消吗', function(index){
                    var index = layer.load(0, {time: 10*1000});
                    $.post("taskComplete", {"taskId":taskId,'boo_delete':true,"boo_pass":false,"activitiComment":desc},
                        function (res) {
                            if(res.result){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.location.reload();
                                layer.close(index);
                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 1000
                                });
                                layer.close(index);
                            }
                        });
                });
                break;
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
                            layer.close(index);
                        }else {
                            layer.msg('操作失败！'+res.msg, {
                                time: 1000
                            });
                            layer.close(index);
                        }
                    });
            });
                break;
            case "不能供货":
                if (desc==""||desc==null){
                    layer.msg("请输入审批理由", {
                        time: 1000,
                        icon:2
                    });
                    break;
                }
                layer.confirm('确定不供货吗', function(index){
                    var index = layer.load(0, {time: 10*1000});
                    $.post("taskComplete", {"taskId":taskId,'boo_delete':false,"boo_pass":false,"activitiComment":desc},
                        function (res) {
                            if(res.result){
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.location.reload();
                                layer.close(index);
                            }else {
                                layer.msg('操作失败！'+res.msg, {
                                    time: 1000
                                });
                                layer.close(index);
                            }
                        });
                });
                break;
            default:
               console.log("啥都没有");
        }
     return false
    });

});
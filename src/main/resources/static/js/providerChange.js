layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            form = layui.form;
        $ = layui.jquery; //jquery控件

        show();
        //供应商下拉
        function show(){
        ids = $("#user").val();
            //单位下拉框
            $.get("http://chunyin1992.vicp.io/api/purchaseRequisition/selectMerchants?userId="+ids,function (res) {
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
            var taskId = $("#taskId").val();
            console.log(taskId);
           var userId = $("#userId").val();
           console.log(userId);
           var arr = new Array();
           arr.push(userId);
           var  ss = JSON.stringify(arr);
           console.log(ss);
            // $.ajax({
            //     url: "taskComplete",
            //     data:  {"boo_assignee":true,"taskId":taskId,'boo_delete':false,"boo_pass":true,"users":arr},
            //     type:"post",
            //     dataType: "json",
            //     contentType : "application/json",//否则报错类型不能少
            //     // jsonp: "selfNamedReplaceCallback",
            //     // jsonpCallback: "jsonpFn", // server side：req.query.callback = "jsonpFn"
            //     success:function (res) {
            //         console.log(taskId,"AJAX")
            //         if(res.result){
            //             layer.msg(res.msg, {
            //                 time: 1000
            //             },function () {
            //                 //传到爹哪里去
            //                 var index = parent.layer.getFrameIndex(window.name);
            //                 parent.layer.close(index);
            //                 parent.location.reload();
            //             });
            //         }else {
            //             layer.msg(res.msg, {
            //                 time: 2000
            //             },function () {
            //                 console.log(res.msg)
            //             });
            //         }
            //     }
            //     })
            $.post("taskComplete", {"boo_assignee":true,"taskId":taskId,'boo_delete':false,"boo_pass":true,"users":userId},
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
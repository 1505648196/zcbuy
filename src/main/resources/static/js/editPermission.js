
function child(obj){
    //把值转字符串传给隐藏域
    document.getElementById("rolePermission").value=JSON.stringify(obj);
}



layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            table = layui.table, //表格
            form = layui.form;
        $ = layui.jquery; //jquery控件

        //把隐藏域的值给拿出来转对象
        var rolePermission=JSON.parse($("#rolePermission").val());
        console.log(rolePermission);
        $("#name").val(rolePermission.role.name);
        $("#id").val(rolePermission.role.id);
        //传递过来原本有的权限集合
        var powersplus=rolePermission.fatherPowers_ids;
        var powersplus2 = rolePermission.children_ids
        //获取权限表全部权限
        $.get("getPowers",function (res) {
                var data=res.data;
                console.log(data);
                var html="";
                //循环管理者拥有的权限对象集合
                $.each(data,function (index, item) {
                    var b=false;
                    //判断是否含有这个权限id  有就true
                    if (powersplus.search(item.id)!=-1||powersplus2.search(item.id)!=-1){
                        b = true;
                    }
                    if(b){
                        html+="<input lay-skin='primary' type='checkbox' checked name='cc'  value='"+item.id+"' title='"+item.name+"'/>";
                        b=false;
                    }else {
                        html+="<input lay-skin='primary' type='checkbox' name='cc' value='"+item.id+"' title='"+item.name+"'/>";
                    }
                });
                $("#select").html(html);
                form.render();
            }
        );

        form.on('submit(sub)',function (data) {
            var id = data.field.id;

            var name = data.field.name;
            var err =  $("[name=cc]:checked");
            console.log($("[name=cc]:checked"));
            var array = new Array();
            for (var i =0 ;i<err.length;i++){
                var aa = array.value;
                console.log(aa);
                array.push(aa);
            }
            $.post("updateRolePower",
            {"id":id,"objs":array,"name":name} ,
                function (res) {
                    if(res.result){
                        console.log(res.result);
                        layer.msg('修改成功！', {
                            time: 1000
                        },function () {
                            //传到爹哪里去
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    }else {
                        console.log(res.msg);
                        layer.msg('修改失败！'+res.msg, {
                            time: 10000
                        });
                    }


            })
            // //修改
            // $.ajax({
            //     //角色权限更新
            //     url: "updateRolePower",
            //     data: JSON.stringify( {id:id,objs:array,name:name}),
            //     traditional:true,
            //     type:"post",
            //     dataType: "json",
            //     contentType : "application/json",//否则报错类型不能少
            //
            //     // jsonp: "selfNamedReplaceCallback",
            //     // jsonpCallback: "jsonpFn", // server side：req.query.callback = "jsonpFn"
            //     success:function (res) {
            //         if(res.result){
            //             console.log(res.result);
            //             layer.msg('修改成功！', {
            //                 time: 1000
            //             },function () {
            //                 //传到爹哪里去
            //                 var index = parent.layer.getFrameIndex(window.name);
            //                 parent.layer.close(index);
            //                 parent.location.reload();
            //             });
            //         }else {
            //             console.log(res.msg);
            //             layer.msg('修改失败！'+res.msg, {
            //                 time: 10000
            //             });
            //         }
            //     }
            // });
            return false;
        });
    });
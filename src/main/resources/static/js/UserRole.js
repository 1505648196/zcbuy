


layui.use(['layer', 'form', 'table', 'laydate'],
    function () {
        var laydate = layui.laydate, //日期
            layer = layui.layer, //弹层
            table = layui.table, //表格
            form = layui.form;
        $ = layui.jquery; //jquery控件

        var powersplus="";
        var powersplus2 = "";
        var b=false;;
        //把隐藏域的值给拿出来转对象

        //把隐藏域的值给拿出来转对象
        var userPermission=JSON.parse($("#userPermission").val());
        console.log(userPermission);

        $("#name").val(userPermission.name);
        console.log(userPermission.name);
        var ids = userPermission.id
        $("#id").val(userPermission.id);
        console.log(ids);

        
        //根据id获取用户权限
        $.get("http://chunyin1992.vicp.io/api/power/getUserPowers?userId="+ids,function (res) {
                if (res.result){
                    var   datapluss = res.data;
                    console.log(datapluss)
                    //传递过来原本有的权限集合
                    var powersplus=datapluss.fatherPowers_ids;
                    console.log(powersplus)
                    var powersplus2 = datapluss.children_ids;
                    console.log(powersplus2)
                    //获取全部管理者的权限
                    $.get("http://chunyin1992.vicp.io/api/power/getPowers",function (res) {

                            var data=res.data;
                            console.log(data);
                            var html="";
                            //循环管理者拥有的权限对象集合
                            $.each(data,function (index, item) {

                                //判断是否含有这个权限id  有就true
                                if (powersplus.search(item.id)!=-1||powersplus2.search(item.id)!=-1){
                                    b = true;
                                }
                                if(b){
                                    html+="<input lay-skin='primary' type='checkbox' checked name='cc'  value='"+item.id+"' title='"+item.name+"'/>";
                                    b=false;//记得清空
                                }else {
                                    html+="<input lay-skin='primary' type='checkbox' name='cc' value='"+item.id+"' title='"+item.name+"'/>";
                                }
                            });
                            $("#select").html(html);
                            form.render();
                        }
                    );

                }

            }
        );



        // $.ajax({
        //     url: "http://chunyin1992.vicp.io/api/power/getUserPowers?userId="+ids,
        //     type:"get",
        //     dataType: "json",
        //     contentType : "application/json",//否则报错   类型不能少
        //     success:function (res) {
        //         if (res.result){
        //          var   datapluss = res.data;
        //          console.log(datapluss)
        //             //传递过来原本有的权限集合
        //             var powersplus=datapluss.fatherPowers_ids;
        //             var powersplus2 = datapluss.children_ids;
        //         }
        //     }
        // });








        form.on('submit(sub)',function (data) {
            var id = data.field.id
            console.log(id)

            var err =  $("[name=cc]:checked");
            console.log($("[name=cc]:checked"));
            var errplus = new Array()
            for (var i =0 ;i<err.length;i++){
                var aa = err[i].value;
                errplus.push(aa);
            }
            console.log(errplus);
            //修改
            $.ajax({
                url: "http://chunyin1992.vicp.io/api/power/updateUserPower",
                data: JSON.stringify( {userId:id,objs:errplus}),
                type:"post",
                dataType: "json",
                contentType : "application/json",//否则报错类型不能少
                // jsonp: "selfNamedReplaceCallback",
                // jsonpCallback: "jsonpFn", // server side：req.query.callback = "jsonpFn"
                success:function (res) {
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
                            time: 1000
                        });
                    }
                }


            });
            // $.post("http://chunyin1992.vicp.io/api/power/updateRolePower",{"id":10,"objs":[1,2,3,4,5,6,7,8,9,10,11,12]},
            //     success:function (res) {
            //         if(res.result){
            //             layer.msg('修改成功！', {
            //                 time: 1000
            //             },function () {
            //                 var index = parent.layer.getFrameIndex(window.name);
            //                 parent.layer.close(index);
            //                 parent.location.reload();
            //             });
            //         }else {
            //             console.log(res.msg);
            //             layer.msg('修改失败！'+res.msg, {
            //                 time: 1000
            //             });
            //         }
            //     });
            return false;
        });
    });
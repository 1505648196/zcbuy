


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
        $.get("getUserPowers",{"userId":ids},function (res) {
                if (res.result){
                    var   datapluss = res.data;
                    console.log(datapluss)
                    //传递过来原本有的权限集合
                    var powersplus=datapluss.fatherPowers_ids;
                    console.log(powersplus)
                    var powersplus2 = datapluss.children_ids;
                    console.log(powersplus2)
                    //获取全部管理者的权限
                    $.get("getPowers",function (res) {

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
                                    html+="<input lay-skin='primary' type='checkbox' checked name='"+item.id+"'  value='"+item.id+"' title='"+item.name+"'/>";
                                    b=false;
                                }else {
                                    html+="<input lay-skin='primary' type='checkbox' name='"+item.id+"' value='"+item.id+"' title='"+item.name+"'/>";
                                }
                            });
                            $("#select").html(html);
                            form.render();
                        }
                    );

                }

            }
        );


        form.on('submit(sub)',function (data) {
          var  dataplus = data.field;
          console.log(dataplus)
          test(dataplus);
            return false;
        });

        function test(dataplus) {
            //修改
            $.post("updateUserPower",
                dataplus ,
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

        }
    });
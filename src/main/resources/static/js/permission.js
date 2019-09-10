layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

    //初始化
     getlist();
    //表头
    function getlist() {
        table.render({
            elem: '#shows',
             // toolbar: '#toolbarDemo',
            page: true,
            url: "getPowerByRoles",//獲取全部角色對應權限
            //    where: param,
            parseData://转换layui所需格式
                function (res) { //res 即为原始返回的数据
                    var code=0;
                    if (!res.result){
                        code=1;
                    }
                    return {
                        "code": code,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.data
                    };
                },
            //颜色换行
            done: function (res, curr, count) {
                $('th').css({'background-color': '#5792c6', 'color': '#fff', 'font-weight': '500', 'font-size': '14px'});
                var that = this.elem.next();
                res.data.forEach(function (item, index) {
                    if ((index + 1) % 2 == 0) {
                        var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color", "#DDEBF7");
                    }
                });
            },
            //数据字段填充
            cols: [
                [
                    {type: 'numbers'},
                    {field: 'name', title: '单位', align: 'center',templet:function (d) {
                            console.log(d.role)
                            return d.role.name;
                        }},
                    {field: 'power', title: '拥有', align: 'center',templet:function (d) {
                            var contextPower = "";
                            if (d.role.name=="超级管理员"){
                                return "拥有所有权限";
                                console.log("你好啊");
                                console.log(d.powers);
                            } else{
                                d.powers.forEach(function (item,index) {
                                    console.log(item.name);
                                    contextPower +=item.name;
                                } )
                                return contextPower

                            }
                        }},
                    {fixed: 'right', title: '操作', align: 'center',toolbar: '#barDemo'}
                ]
            ]
        });
    }



    //监听工具事件
    table.on('tool(shows)', function (obj) {
        var data = obj.data;
        console.log("来了")
        console.log(data.role.id);
        if (obj.event === 'edit') {
            layer.open({
                title: '修改',
                type: 2,
                maxmin: true, //开启最大化最小化按钮
                area: ['50%', '80%'],
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);//获得子窗口
                    var iframe = window['layui-layer-iframe' + index];//反正就是传递
                    iframe.child(data)//通过js传值：child()是子界面的Js方法
                },
                content:"editPermission"
            });
        }
        else if (obj.event === 'del') {
            layer.confirm('确定删除吗', function(index){
                //删除角色
                $.post("delRole", {'id':data.role.id},
                    function (res) {
                        if(res.result){
                            getlist();
                            layer.close(index);
                        }else {
                            layer.msg('操作失败！'+res.msg, {
                                time: 8000
                            });
                        }

                    });
            });
        }
    });

    $(".subAddPowertwo").click(function () {
        var name =  $("#name").val();
        console.log(name);
        if (name==""){
            layer.msg("请输入角色名",{
                icon:2,
                time:1500,
            })
            return false;
        }else {
            //增加
            $.ajax({
                url: "addRole",
                data: JSON.stringify( {name:name}),
                type:"post",
                dataType: "json",
                contentType : "application/json",//否则报错类型不能少
                // jsonp: "selfNamedReplaceCallback",
                // jsonpCallback: "jsonpFn", // server side：req.query.callback = "jsonpFn"
                success:function (res) {
                    console.log(res.result);
                    if (res.result) {

                        layer.msg('添加成功！', {
                            time: 500
                        }, function () {
                            location.href="permission"
                            //传到爹哪里去
                            // var index = parent.layer.getFrameIndex(window.name);
                            // parent.layer.close(index);
                            // parent.location.reload();
                        });
                    } else {
                        console.log(res.msg);
                        layer.msg('添加失败！' + res.msg, {
                            time: 1000
                        });
                    }
                }
            });
        }
    })

    form.on('submit(subAddPowertwo)', function (data) {



    });

});
layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

     getlist();
    //表头
    function getlist() {

        table.render({
            elem: '#show',
             // toolbar: '#toolbarDemo',
            page: true, //我把分页关了
            url: "http://chunyin1992.vicp.io/api/power/getPowerByRoles",
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
            done: function (res, curr, count) {
                $('th').css({'background-color': '#5792c6', 'color': '#fff', 'font-weight': '500', 'font-size': '14px'});
                var that = this.elem.next();
                res.data.forEach(function (item, index) {
                    if ((index + 1) % 2 == 0) {
                        var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color", "#DDEBF7");
                    }
                });
            },
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
                                    contextPower +=item.name+"//"+item.children[index].name;
                                } )
                                return contextPower

                            }
                        }},

                    // {field: 'department', title: '部门', align: 'center',templet:function (d) {
                    //         return d.department.name;
                    //     }},
                    // {field: 'name', title: '姓名', align: 'center'},
                    // {field: 'role', title: '角色22', align: 'center',templet:function (d) {
                    //         return d.role.name;
                    //     }},
                    // {field: 'phone', title: '手机', align: 'center'},
                    // {field: 'email', title: '邮箱', align: 'center'},
                    //
                    {fixed: 'right', title: '操作', align: 'center',toolbar: '#barDemo'}
                ]
            ]
        });
    }

    // //搜索
    // form.on('submit(sub)', function (data) {
    //     getlist();
    //     return false;
    // });
    // form.on('select(change)', function(data){
    //     getDepartment();
    // });

    //监听工具事件
    table.on('tool(show)', function (obj) {
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
                $.post("http://chunyin1992.vicp.io/api/role/delRole", {'id':data.role.id},
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
   // 头工具栏事件
   //  table.on('toolbar(show)', function (obj) {
   //      if (obj.event === 'add') {
   //          layer.open({
   //              title: '添加',
   //              type: 2,
   //              maxmin: true, //开启最大化最小化按钮
   //              area: ['50%', '80%'],
   //              content:"editUser"
   //          });
   //      }
   //  });

    form.on('submit(sub)', function (data) {
        console.log(data.field.name)
        if (data.field.name==""){
            layer.msg("请输入角色名",{
                icon:2,
                time:1500,
            })
            return false;
        }else {
            $.ajax({
                url: "http://chunyin1992.vicp.io/api/role/addRole",
                data: JSON.stringify( {name:data.field.name}),
                type:"post",
                dataType: "json",
                contentType : "application/json",//否则报错类型不能少
                // jsonp: "selfNamedReplaceCallback",
                // jsonpCallback: "jsonpFn", // server side：req.query.callback = "jsonpFn"
                success:function (res) {
                    location.href = "user";
                }
            })



        }


    });

});
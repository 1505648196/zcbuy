layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件


    //加载表格数据
    getlist();
    //表头
    function getlist() {
        var name = $("#name").val();
        var address =$("#address").val();
        var param = {'name':name,'address':address};
        table.render({
            elem: '#show',
            toolbar: '#toolbarDemo',
            page: true,
            url: "getUnitsWithPage",//新分页
            where: param,
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
            //隔行变色
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
                    {field: 'name', title: '单位', align: 'center'},
                    {field: 'address', title: '地址', align: 'center'},
                    {field: 'departments', title: '部门', align: 'center',templet:function (d) {
                        var text ="";
                       $.each(d.departments,function (index,item) {
                           text+=item.name;
                       })
                            return text;
                        }},
                    {fixed: 'right', title: '操作', align: 'center',toolbar: '#barDemo'}
                ]
            ]
        });
    }
    //搜索
    form.on('submit(sub)', function (data) {
        getlist();
        return false;
    });

    //监听工具事件
    table.on('tool(show)', function (obj) {
        var data = obj.data;
        //用户信息
        if (obj.event === 'edit') {
            layer.open({
                title: '编辑',
                type: 2,
                maxmin: true, //开启最大化最小化按钮
                area: ['50%', '80%'],
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.find('#id').val(data.id);
                    body.find('#address').val(data.address);
                    body.find('#name').val(data.name);
                    console.log(data.id,data.address,data.name)
                },
                content:"editUnit"
            });
        }
        else
        if (obj.event === 'del') {
            layer.confirm('确定删除吗', function(index){
                console.log(data)
                $.get("deleteUnit?id="+data.id,
                    function (res) {
                        if(res.result){
                            layer.msg('已删除！'+res.msg, {
                                time: 1000
                            });
                            getlist();
                            layer.close(index);
                        }else {
                            console.log(res.msg);
                            layer.msg('操作失败！'+res.msg, {
                                time: 1000
                            });
                        }

                    });
            });
        }
    });
    //头工具栏事件
    table.on('toolbar(show)', function (obj) {
        if (obj.event === 'add') {
            layer.open({
                title: '添加',
                type: 2,
                maxmin: true, //开启最大化最小化按钮
                area: ['50%', '80%'],
                content:"addUnit"
            });
        }
    });

});
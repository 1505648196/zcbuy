layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

    //加载表格数据
    getlist();
    //表头
    function getlist() {
        var goodsName = $("#goodsName").val();
        var purchaseTypeName = $("#purchaseTypeName").val();
        console.log(purchaseTypeName);
        var status = $("#status").val();
        var param = {'goodsName': goodsName, 'purchaseTypeId': purchaseTypeName,"status":status,};
        table.render({
            elem: '#show',
            toolbar: '#toolbarDemo',
            page: true,
            url: "getPurchaseTypes",
          //  where: param,
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
                    {field: 'name', title: '类型名称', align: 'center'},
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
    form.on('select(change)', function(data){
        getDepartment();
    });

    //监听工具事件
    table.on('tool(show)', function (obj) {
        var arr = new Array();
        var content ="";
        var data = obj.data;

        //用户信息
        if (obj.event === 'edit') {
            layer.open({
                title: '编辑',
                type: 2,
                maxmin: true, //开启最大化最小化按钮
                area: ['50%', '80%'],
                success: function (layero, index) {
                    // console.log(data.id)
                    var body = layer.getChildFrame('body', index);
                    body.find('#name').val(data.name);
                    body.find('#loginName').val(data.loginName);
                    body.find('#uid').val(data.unit.id);
                    body.find('#did').val(data.department.id);
                    body.find('#rid').val(data.role.id);
                    body.find('#phone').val(data.phone);
                    body.find('#email').val(data.email);
                },
                content:"addBuyType"
            });
        }
        else
        if (obj.event === 'del') {
            layer.confirm('确定删除吗', function(index){
                $.post("delPurchaseRequisition", {'id':data.id},
                    function (res) {
                        if(res.result){
                            getlist();
                            layer.close(index);
                        }else {
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
                area: ['60%', '80%'],
                content:"addBuyApply"
            });
        }
    });

});
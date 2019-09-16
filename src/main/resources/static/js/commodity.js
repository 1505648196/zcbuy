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
        var param = {'name':name};
        table.render({
            elem: '#show',
            toolbar: '#toolbarDemo',
            page: true,
            url: "getGoods",
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

                    {field: 'name', title: '商品名称', align: 'center'},

                    {field: 'price', title: '价格', align: 'center',},

                    {field: 'statusName', title: '状态', align: 'center',},

                    {field: 'createTime', title: '创建时间', align: 'center',},

                    {field: 'userName', title: '供应商', align: 'center',templet:function (d) {
                            return d.user.name;
                        }},
                    {field: 'goodsTypeName', title: '商品类型', align: 'center',templet:function (d) {
                            return d.goodsType.name;
                        }},
                    {field: 'purchaseTypeName', title: '采购类型', align: 'center',templet:function (d) {
                            return d.purchaseType.name;
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
                title: '编辑，红色的不可编辑',
                type: 2,
                maxmin: true, //开启最大化最小化按钮
                area: ['50%', '80%'],
                success: function (layero, index) {
                    var  statusName = data.statusName;
                    var  goodsTypeName =  data.goodsType.name;
                    var userName= data.user.name;
                    var phone = data.user.phone;
                    var email = data.user.email;
                    var unitName = data.user.unit.name;
                    var address = data.user.unit.address;
                    var ptNames = data.purchaseType.name;
                    console.log(statusName,goodsTypeName,userName,phone,email,unitName,address,ptNames);
                    var body = layer.getChildFrame('body', index);
                    body.find('#id').val(data.id);
                    body.find('#price').val(data.price);
                    body.find('#name').val(data.name);
                    body.find('#statusId').val(data.status);
                    body.find('#goodsTypeIds').val(data.goodsTypeId);
                    body.find('#userName').val(userName);
                    body.find('#phone').val(phone);
                    body.find('#unitName').val(unitName);
                    body.find('#address').val(address);





                },
                content:"editCommodity"
            });
        }
        else
        if (obj.event === 'del') {
            layer.confirm('确定删除吗', function(index){
                console.log(data)
                $.get("delGoods?id="+data.id,
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
                content:"addCommodity"
            });
        }
    });

});
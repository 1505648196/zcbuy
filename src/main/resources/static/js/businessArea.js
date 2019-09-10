layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件



    function show(){
        //单位下拉框
        $.get("getMerchants",function (res) {
            var data=res.data;
            var htmltwo=" <option value=''>供应商选择</option>";
            $.each(data,function (index,item) {
                htmltwo+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#userId").html(htmltwo);
            form.render();
        });
    }

    //加载表格数据
    getlist();
    //表头
    function getlist() {
        show();
        var areaName = $("#areaName").val()
        var starus = $("#starus").val();
        var userId = $("#userId").val();
        var param = {"areaName":areaName,'userId':userId};
        table.render({
            elem: '#show',
            toolbar: '#toolbarDemo',
            page: true,
            url: "getAreaMerchants",
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
                    {field: 'areaName', title: '地区', align: 'center'},
                    {field: 'name', title: '供应商', align: 'center',templet:function (d) {
                        return d.user.name;
                        }},

                    {field: 'status', title: '状态', align: 'center',templet:function (d) {
                            if (d.status==1){
                                return "可用";
                            }else{
                                return "未知"
                            }
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
                    console.log(data);
                    console.log(data.areaName);
                    var body = layer.getChildFrame('body', index);
                    body.find('#id').val(data.id);
                    body.find('#areaName').val(data.areaName);
                    console.log(data.user.name);
                    body.find('#name').val(data.user.name);
                    // body.find('#status').val(data.status);
                },
                content:"editBusinessArea"
            });
        }
        else
        if (obj.event === 'del') {
            layer.confirm('确定删除吗', function(index){
                console.log(data)
                $.get("delAreaMerchant?id="+data.id,
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
                content:"addBusinessArea"
            });
        }
    });

});
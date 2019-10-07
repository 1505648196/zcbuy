layui.use(['layer', 'form', 'jquery', 'table'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件

    getDepartment();

    function getDepartment() {
        $.get("getPurchaseTypes",function (res) {
            var data=res.data;
            var html ="";
            // var html="<option value=''>全部采购类型</option>";
            $.each(data,function (index,item) {
                html+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#purchaseTypeName").html(html);
            form.render();
        })
    }
    //加载表格数据
    getlist();
    //表头

    function getlist() {
        var userId= $("#userId").val();
        var purchaseTypeId = $("#purchaseTypeName").val();
        if (purchaseTypeId==""||purchaseTypeId==null) {
            purchaseTypeId = "10905a6a55664ec5a6f1287eda5b1ac2";
        }
        var param = {'userId': userId,'purchaseTypeId': purchaseTypeId,};
        table.render({
            elem: '#show',
            //toolbar: '#toolbarDemo',
            page: true,
            url: "getTasksByCandidate",
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
                    {field: 'name', title: '任务名', align: 'center'},
                    {field: 'detail', title: '详情', align: 'center',},
                    {field: 'startTime', title: '开始时间', align: 'center'},
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
        console.log(data)
        //用户信息
        if (obj.event === 'edit') {
            console.log(data.detail);
            layer.open({
                title: '请核对信息',
                type: 2,
                maxmin: true, //开启最大化最小化按钮
                area: ['50%', '50%'],
                success: function (layero, index) {
                    var userId= $("#userId").val();
                    var body = layer.getChildFrame('body', index);
                    body.find('#user').val(userId);
                    body.find('#taskId').val(data.taskId);
                    body.find('#detail').val(data.detail);
                    body.find('#name').val(data.name);
                    body.find('#purchaseTypeName').val(data.purchaseTypeName);
                    body.find('#startTime').val(data.startTime);
                    body.find('#happen').val("接受任务");
                },
                content:"groupTaskList"
            });

            // var userId= $("#userId").val();
            // layer.confirm('确定接受吗', function(index){
            //     $.get("claimTaskByUserId", {"userId":userId,'taskId':data.taskId},
            //         function (res) {
            //             if(res.result){
            //                 getlist();
            //                 layer.close(index);
            //                 location.reload();
            //
            //             }else {
            //                 layer.msg('操作失败！'+res.msg, {
            //                     time: 1000
            //                 });
            //             }
            //         });
            // });
        }
    });


});
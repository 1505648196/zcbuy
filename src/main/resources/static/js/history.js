layui.use(['layer', 'form', 'jquery', 'table','laydate'], function () {
    var form = layui.form,  //表单
        layer = layui.layer, //弹层
        table = layui.table; //表格
    $ = layui.jquery; //jquery控件
    var laydate = layui.laydate;
    var nowTime = new Date().valueOf();
    var max = null;


    var start_time
    var  end_time
    //开始时间
    var start = laydate.render({
        elem: '#start_time',
        max: nowTime,
        btns: ['clear', 'confirm'],
        done: function(value, date){
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month -1;
            console.log(value);
            start_time = value;
        }
    });

    //结束时间
    var end = laydate.render({
        elem: '#end_time',
        format: 'yyyy-MM-dd',
        max: nowTime,
        done: function(value, date) {
            if ($.trim(value) == '') {
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth() + 1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month - 1;
            console.log(value);
            end_time =value;
        }

        })


        $.get("getPurchaseTypes",function (res) {
            var data=res.data;
            var html="<option value=''>全部采购类型</option>";
            $.each(data,function (index,item) {
                html+="<option  value='"+item.id+"'>"+item.name+"</option>";
            });
            $("#purchaseTypeName").html(html);
            form.render();
        })


    //加载表格数据
    getlist();
    //表头
    function getlist() {
        console.log($('#start_time').val());
        userId  = $("userId").val();
        var param = {"userId":userId,"taskCreatedAfterTime":start_time,"taskCreatedBeforeTime":end_time};
        table.render({
            elem: '#show',
            //toolbar: '#toolbarDemo',
            page: true,
            url: "getHistoryList",//获取历史记录
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
                    {field: 'name', title: '任务名', align: 'center',},
                    {field: 'startTime', title: '开始时间', align: 'center',},
                    {field: 'endTime', title: '结束时间', align: 'center'},
                    {field: 'detail', title: '详情', align: 'center',},
                    {field: 'purchaseTypeName', title: '采购类型', align: 'center'},
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
    });


});
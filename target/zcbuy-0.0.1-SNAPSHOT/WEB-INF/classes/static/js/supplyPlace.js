layui.use(['layer', 'form', 'jquery', 'table'], function () {
  var form = layui.form,  //表单
    layer = layui.layer, //弹层
    table = layui.table; //表格
  $ = layui.jquery; //jquery控件
  //单位下拉框
  $.get("getAllUnit",function (res) {
    var data=res.data;
    var id=$("#unit").val();
    var html="<option value=''>全部单位</option>";
    $.each(data,function (index,item) {
      if(id==item.id){
        html+="<option value='"+item.id+"' selected>"+item.name+"</option>";
      }else {
        html+="<option value='"+item.id+"'>"+item.name+"</option>";
      }
    });
    $("#unitId").html(html);
    getDepartment();
  });

  function getDepartment() {
    var params={"unitId":$("#unitId").val()};
    $.get("getDepartment",params,function (res) {
      var data=res.data;
      var html="<option value=''>全部部门</option>";
      $.each(data,function (index,item) {
        html+="<option value='"+item.id+"'>"+item.name+"</option>";
      });
      $("#department").html(html);
      form.render();
    })
  }
  //单位下拉框
  $.get("getAllRole",function (res) {
    var data=res.data;
    var html="<option value=''>全部角色</option>";
    $.each(data,function (index,item) {
        html+="<option value='"+item.id+"'>"+item.name+"</option>";
    });
    $("#roleId").html(html);
  });
  //加载表格数据
  getlist();

  // 加载接口数据  表头
  function getlist() {
//  //  var departmentId = $("#department").val();
    var name = $("#name").val();
  //  var roleId = $("#roleId").val();
    var param = {'name':name};
    table.render({
      elem: '#show',
      //toolbar: '#toolbarDemo',
      page: true,
      url: "getAreas",
      where: param,//自动判断空值
      parseData://转换layui所需格式
        function (res) { //res 即为原始返回的数据
          var code=0;
          if (!res.result){
            code=1;
          }
          return {
            "code": code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.count, //解析数据长度
            "data": res.data //解析数据列表
          };
        },
      //变色
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
          {field: 'name', title: '地区', align: 'center',},
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

  //增加
  form.on('submit(supply)', function (data) {
    supplyName = data.field.supplyName;
    console.log(supplyName);
    //增加
    $.ajax({
      url: "addArea",
      data: JSON.stringify({name: supplyName}),
      type: "post",
      dataType: "json",
      contentType: "application/json",//否则报错类型不能少
      success:function (res) {
        console.log(res.result);
        if (res.result) {

          layer.msg('添加成功！', {
            time: 500
          }, function () {
            location.href="supplyPlace"
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

      })
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
          var body = layer.getChildFrame('body', index);
          body.find('#name').val(data.name);
          console.log(data);
          console.log(data.name);
          body.find('#ids').val(data.id)
          console.log(data.id);
        },
        content:" editSupplyPlace"
      });
    }
    else
    if (obj.event === 'del') {
      layer.confirm('确定删除吗', function(index){
        $.get("delArea", {'id':data.id},
          function (res) {
            if(res.result){
              layer.msg('成功！'+res.msg, {
                time: 1000
              });
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
        area: ['50%', '80%'],
        content:"editUser"
      });
    }
  });

});
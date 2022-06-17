﻿layui.use(['layer', 'form', 'jquery', 'table'], function () {
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
  //表头
  function getlist() {
    var unitId = $("#unitId").val();
    var departmentId = $("#department").val();
    var name = $("#name").val();
    var roleId = $("#roleId").val();
    var param = {'unitId': unitId, 'departmentId': departmentId,"roleId":roleId,'name':name};
    table.render({
      elem: '#show',
      //toolbar: '#toolbarDemo',
      page: true,
      url: "getUserBy",
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
          {field: 'unit', title: '单位', align: 'center',templet:function (d) {
              return d.unit.name;
            }},
          {field: 'department', title: '部门', align: 'center',templet:function (d) {
            if (d.department==null){
              return ""
            } else {  return d.department.name;}

            }},
          {field: 'name', title: '姓名', align: 'center'},
          {field: 'role', title: '角色', align: 'center',templet:function (d) {
              return d.role.name;
            }},
          {field: 'phone', title: '手机', align: 'center'},
          {field: 'email', title: '邮箱', align: 'center',},
           //{fixed: 'right', title: '操作', align: 'center',toolbar: '#barDemo'}

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
    var data = obj.data;
    // if (obj.event === 'edit') {
    //   layer.open({
    //     title: '编辑',
    //     type: 2,
    //     maxmin: true, //开启最大化最小化按钮
    //     area: ['50%', '80%'],
    //     success: function (layero, index) {
    //       var body = layer.getChildFrame('body', index);
    //       body.find('#id').val(data.id);
    //       body.find('#name').val(data.name);
    //       body.find('#loginName').val(data.loginName);
    //       body.find('#uid').val(data.unit.id);
    //       body.find('#did').val(data.department.id);
    //       body.find('#rid').val(data.role.id);
    //       body.find('#phone').val(data.phone);
    //       body.find('#email').val(data.email);
    //     },
    //     content:"editUser"
    //   });
    // }
    // else
    if (obj.event === 'del') {
      layer.confirm('确定删除吗', function(index){
        $.post("delRole", {'id':data.id},
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
        area: ['50%', '80%'],
        content:"editUser"
      });
    }
  });

});
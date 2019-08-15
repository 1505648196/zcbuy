layui.use(['layer', 'form', 'jquery', 'table', 'laydate', 'upload'], function () {
  var laydate = layui.laydate, //日期
    layer = layui.layer, //弹层
    table = layui.table, //表格
    upload = layui.upload, //表格
    form = layui.form;    //表单
  $ = layui.jquery; //jquery控件
  $.get("getAllUnit",function (res) {
    var data=res.data;
    console.log(JSON.stringify(data));
    var id=$("#unitid").val();
    var html="";
    $.each(data,function (index,item) {
      if(id==item.id){
        html+="<option value='"+item.id+"' selected>"+item.name+"</option>";
      }else {
        html+="<option value='"+item.id+"'>"+item.name+"</option>";
      }
    });
    $("#unit").html(html);
    form.render();
    getDepartment();
  });

  function getDepartment() {
    var params={"unitId":$("#unit").val()};
    $.get("getDepartment",params,function (res) {
      var data=res.data;
      console.log(JSON.stringify(data));
      var html="<option></option>";
      $.each(data,function (index,item) {
          html+="<option value='"+item.id+"'>"+item.name+"</option>";
      });
      $("#department").html(html);
      form.render();
    })
  }

  //加载表格数据
  getlist();
  //表头
  function getlist() {
    var unitId = $("#unit").val();
    var departmentId = $("#department").val();
    var name = $("#name").val();
    var param = {'unitId': unitId, 'departmentId': departmentId,'name':name};
    table.render({
      elem: '#show',
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
              return d.department.name;
            }},
          {field: 'name', title: '姓名', align: 'center'},
          {field: 'role', title: '角色', align: 'center',templet:function (d) {
              return d.role.name;
            }},
          {field: 'phone', title: '手机', align: 'center'},
          {field: 'email', title: '邮箱', align: 'center'},

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
    var data = obj.data;
    if (obj.event === 'edit')
    {
      layer.open({
        title: '编辑',
        type: 2,
        maxmin: true, //开启最大化最小化按钮
        area: ['50%', '80%'],
        success: function (layero, index) {
          var body = layer.getChildFrame('body', index);
          body.find('#cardNum').val(data.cardNum);
          body.find('#unit').val(data.unit);
          body.find('#cardNum').val(data.cardNum);
          body.find('#name').val(data.name);
          body.find('#phone').val(data.phone);
          body.find('#duties').val(data.duties);
          body.find('#politicalAppearance').val(data.politicalAppearance);
          body.find('#strTime').val(data.admissionyyyyMm);
          body.find('#diploma').val(data.diploma);
          body.find('#id').val(data.role.id);
        },
        content:urlpre+"/admin/edit/user/updatePage"
      });
    }
    else if (obj.event === 'del')
    {
      layer.confirm('确定删除吗', function(index){
        $.post(urlpre+"/doAdmin/deleteUser", {'cardNum':data.cardNum},
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
        type: 1,
        content: $('#add')
      });
    }
  });

});
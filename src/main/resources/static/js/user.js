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
    var arr = new Array();
    var content ="";
    //权限
    var data = obj.data;
    if (obj.event === 'editRole') {
      layer.open({
        title: '权限',
        type: 2,
        maxmin: true, //开启最大化最小化按钮
        area: ['50%', '80%'],
        success: function (layero, index) {
          //根据id获取用户权限
          $.ajax({
          url: "http://chunyin1992.vicp.io/api/power/getUserPowers?userId="+data.id,
          type:"get",
          dataType: "json",
          contentType : "application/json",//否则报错   类型不能少
          // jsonp: "selfNamedReplaceCallback",
          // jsonpCallback: "jsonpFn", // server side：req.query.callback = "jsonpFn"
          success:function (res) {
            console.log("来了老弟")
                if (res.result) {
                  var data = res.data
                  console.log(data);
                  $.get("http://chunyin1992.vicp.io/api/power/getPowers",function (resplus) {
                        var dataplus=resplus.data;
                        console.log(dataplus);
                        var html="";
                        //循环管理者拥有的权限对象集合
                        $.each(dataplus,function (index, item) {
                         var name = item.name;
                          content = name+","+content;
                        console.log(content);
                        });

                    $.each(data,function (index, items) {
                      console.log(items.power.name);
                     if (content.search(items.power.name)){
                       b=true;
                     }
                          if(b){
                            console.log("you")
                            // html+="<input lay-skin='primary' type='checkbox' checked name='cc'  value='"+items.power.children[index].id+"' title='"+items.power.children[index].name+"'/>";
                            html+="<input lay-skin='primary' type='checkbox' checked name='cc'  value='\"+items.power.children[index].id+\"' />";
                          }else {
                            html+="<input lay-skin='primary' type='checkbox' checked name='cc' />";
                            // html+="<input lay-skin='primary' type='checkbox' checked name='cc'  value='"+items.power.children[index].id+"' title='"+items.power.children[index].name+"'/>";
                          }
                    });
                        $("#select").html(html);
                        form.render();
                      }
                  );
              // layer.msg('添加成功！', {
              //   time: 500
              // }, function () {
              //   location.href="permission"
              //   //传到爹哪里去
              //   // var index = parent.layer.getFrameIndex(window.name);
              //   // parent.layer.close(index);
              //   // parent.location.reload();
              // });
            } else {
              console.log(res.msg);
              layer.msg('添加失败！' + res.msg, {
                time: 1000
              });
            }
          }
        });

        },
        content:"UserRole"
      });
    }
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
          body.find('#id').val(data.id);
          body.find('#name').val(data.name);
          body.find('#loginName').val(data.loginName);
          body.find('#uid').val(data.unit.id);
          body.find('#did').val(data.department.id);
          body.find('#rid').val(data.role.id);
          body.find('#phone').val(data.phone);
          body.find('#email').val(data.email);
        },
        content:"editUser"
      });
    }
    else
    if (obj.event === 'del') {
      layer.confirm('确定删除吗', function(index){
        $.post("delUser", {'id':data.id},
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
layui.use(['layer', 'form', 'table', 'laydate'],
  function () {
    var laydate = layui.laydate, //日期
      layer = layui.layer, //弹层
      form = layui.form;
    $ = layui.jquery; //jquery控件
    var id =$("#id").val();
    var rid =$("#rid").val();
    var uid =$("#uid").val();
    var did =$("#did").val();

    //单位下拉框
    $.get("getAllUnit",function (res) {
      var data=res.data;
      var html="";
      $.each(data,function (index,item) {
        if(uid==item.id){
          html+="<option value='"+item.id+"' selected>"+item.name+"</option>";
        }else {
          html+="<option value='"+item.id+"'>"+item.name+"</option>";
        }
      });
      $("#unitId").html(html);
      form.render();
      getDepartment();
    });

    form.on('select(change)', function(data){
      getDepartment();
    });

    function getDepartment() {
      var params={"unitId":$("#unitId").val()};
      $.get("getDepartment",params,function (res) {
        var data=res.data;
        var html="";
        $.each(data,function (index,item) {
          if(did==item.id){
            html+="<option value='"+item.id+"' selected>"+item.name+"</option>";
          }else {
            html+="<option value='"+item.id+"'>"+item.name+"</option>";
          }            });
        $("#departmentId").html(html);
        form.render();
      })
    }

    //角色下拉框
    $.get("getAllRole",function (res) {
      var data=res.data;
      var html="";
      $.each(data,function (index,item) {
        if(rid==item.id){
          html+="<option value='"+item.id+"' selected>"+item.name+"</option>";
        }else {
          html+="<option value='"+item.id+"'>"+item.name+"</option>";
        }
      });
      $("#roleId").html(html);
      form.render();
    });

    form.on('submit(sub)',function (data) {
      if(id){
        $.post("updateUserInfo", data.field,
          function (res) {
            if(res.result){
              layer.msg(res.msg, {
                time: 1000
              },function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.location.reload();
              });
            }else {
              layer.msg(res.msg, {
                time: 2000
              });
            }
          });
      }else {
        $.post("register", data.field,
          function (res) {
            if(res.result){
              layer.msg(res.msg, {
                time: 1000
              },function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.location.reload();
              });
            }else {
              layer.msg(res.msg, {
                time: 2000
              });
            }
          });
      }
      return false;
    });

  });